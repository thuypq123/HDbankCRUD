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

    @Override
    public List<HdbDedupFaceCheckEntity> getAllHdbDedupFaceCheck() {
        String sql = "SELECT * FROM hdb_dedup_face_check";
        RowMapper<HdbDedupFaceCheckEntity> rowMapper = new BeanPropertyRowMapper<>(HdbDedupFaceCheckEntity.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

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

    @Override
    public ResponseEntity<String> saveHdbDedupFaceCheck(HdbDedupFaceCheckEntity hdbDedupFaceCheckEntity) {
        String sql = "INSERT INTO hdb_dedup_face_check (face_id, is_checked) VALUES (?,?)";
        String shortId = RandomStringUtils.randomAlphanumeric(50);
        Object[] args = { shortId, hdbDedupFaceCheckEntity.getIsChecked() };
        jdbcTemplate.update(sql, args);

        return ResponseEntity.status(HttpStatus.OK).body("Save Successful");
    }

    @Override
    public ResponseEntity<String> updateHdbDedupFaceCheck(HdbDedupFaceCheckEntity hdbDedupFaceCheck, String FaceId) {
        String sql = "UPDATE hdb_dedup_face_check SET face_id = ? WHERE face_id = ?";
        Object[] args = { hdbDedupFaceCheck.getFaceId(), FaceId };
        int row = jdbcTemplate.update(sql, args);
        if (row == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("Updated SuccessFul");
        }

        return  ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Cannot Update");
    }

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
