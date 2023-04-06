package com.example.postgresqlAPI.service.impl;

import com.example.postgresqlAPI.entity.HdbDedupFaceCheckEntity;
import com.example.postgresqlAPI.service.HdbDedupFaceCheckService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HdbDedupFaceCheckImpl implements HdbDedupFaceCheckService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /***
     * Function to get all HdbDedupFaceCheck records from database
     * 
     * @return A list of HdbDedupFaceCheckEntity objects containing all records.
     ***/
    @Override
    public List<HdbDedupFaceCheckEntity> getAllHdbDedupFaceCheck() {
        String sql = "SELECT * FROM hdb_dedup_face_check";
        RowMapper<HdbDedupFaceCheckEntity> rowMapper = new BeanPropertyRowMapper<>(HdbDedupFaceCheckEntity.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    /***
     * Function to get a specific HdbDedupFaceCheck record from database by face_id
     * 
     * @param {String} FaceId - The face_id of the record to retrieve.
     * @return The HdbDedupFaceCheckEntity object that corresponds to the given
     *         face_id.
     ***/
    @Override
    public HdbDedupFaceCheckEntity getHdbDedupFaceCheckByFaceId(String FaceId) {
        String sql = "SELECT * FROM hdb_dedup_face_check WHERE face_id = ?";
        Object[] args = { FaceId };
        RowMapper<HdbDedupFaceCheckEntity> rowMapper = new BeanPropertyRowMapper<>(HdbDedupFaceCheckEntity.class);
        List<HdbDedupFaceCheckEntity> results = jdbcTemplate.query(sql, args, rowMapper);
        if (!results.isEmpty()) {
            return results.get(0);
        } else {
            throw new RuntimeException("user not found.");
        }
    }

    /***
     * Function to save a new HdbDedupFaceCheckEntity record to the database
     * 
     * @param {HdbDedupFaceCheckEntity} hdbDedupFaceCheckEntity - The
     *                                  HdbDedupFaceCheckEntity object to save to
     *                                  the database.
     * @return A ResponseEntity with HTTP status code indicating success or failure
     *         of the save operation.
     ***/
    @Override
    public ResponseEntity<String> saveHdbDedupFaceCheck(HdbDedupFaceCheckEntity hdbDedupFaceCheckEntity) {
        String sql = "INSERT INTO hdb_dedup_face_check (face_id, is_checked) VALUES (?,?)";
        String shortId = RandomStringUtils.randomAlphanumeric(50);
        Object[] args = { shortId, hdbDedupFaceCheckEntity.getIsChecked() };
        jdbcTemplate.update(sql, args);

        return ResponseEntity.status(HttpStatus.OK).body("Save Successful");
    }

    /***
     * Function to update a specific HdbDedupFaceCheckEntity record in the database
     * 
     * @param {HdbDedupFaceCheckEntity} hdbDedupFaceCheckEntity - The updated
     *                                  HdbDedupFaceCheckEntity object to save to
     *                                  the database.
     * @param {String}                  FaceId - The face_id of the record to
     *                                  update.
     * @return A ResponseEntity with HTTP status code indicating success or failure
     *         of the update operation.
     ***/
    @Override
    public ResponseEntity<String> updateHdbDedupFaceCheck(HdbDedupFaceCheckEntity hdbDedupFaceCheck, String FaceId) {
        try {
            String sql = "UPDATE hdb_dedup_face_check SET face_id = ?, is_checked = ? WHERE face_id = ?";
            Object[] args = { hdbDedupFaceCheck.getFaceId(),hdbDedupFaceCheck.getIsChecked(), FaceId };
            System.out.println("==="+hdbDedupFaceCheck.getFaceId()+"==="+hdbDedupFaceCheck.getIsChecked()+"==="+ FaceId);
            int row = jdbcTemplate.update(sql, args);
            if (row == 0) {
                return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("duplicate faceId or faceId is not exits");
            }
            return ResponseEntity.status(HttpStatus.OK).body("Updated SuccessFul");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating.");
        }
    }

    /***
     * Function to delete a specific HdbDedupFaceCheck record from the database
     * 
     * @param {String} FaceId - The face_id of the record to delete.
     * @return A ResponseEntity with HTTP status code indicating success or failure
     *         of the delete operation.
     ***/
    @Override
    public ResponseEntity<String> deleteHdbDedupFaceCheck(String FaceId) {
        String sql = "DELETE FROM hdb_dedup_face_check WHERE face_id = ?";
        Object[] args = { FaceId };
        int rowsAffected = jdbcTemplate.update(sql, args);
        if (rowsAffected == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Deleted SuccessFul");
        }
    }

}
