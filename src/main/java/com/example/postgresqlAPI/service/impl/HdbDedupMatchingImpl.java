package com.example.postgresqlAPI.service.impl;

import com.example.postgresqlAPI.entity.HdbDedupMatchingEntity;
import com.example.postgresqlAPI.service.HdbDedupMatchingService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HdbDedupMatchingImpl implements HdbDedupMatchingService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<HdbDedupMatchingEntity> getAllHdbDedupMatching() {
        String sql = "SELECT * FROM hdb_dedup_matching";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            HdbDedupMatchingEntity hdbDedupMatchingEntity = new HdbDedupMatchingEntity();
            hdbDedupMatchingEntity.setFaceId(rs.getString("face_id"));
            hdbDedupMatchingEntity.setMatchingFaceId(rs.getString("matching_face_id"));
            return hdbDedupMatchingEntity;
        });
    }

    @Override
    public HdbDedupMatchingEntity getHdbDedupMatchingByFaceId(String faceId) {
        String sql = "SELECT * FROM hdb_dedup_matching WHERE face_id = ?";
        Object[] args = { faceId };
        return jdbcTemplate.queryForObject(sql, args, (rs, rowNum) -> {
            HdbDedupMatchingEntity hdbDedupMatchingEntity = new HdbDedupMatchingEntity();
            hdbDedupMatchingEntity.setFaceId(rs.getString("face_id"));
            hdbDedupMatchingEntity.setMatchingFaceId(rs.getString("matching_face_id"));
            return hdbDedupMatchingEntity;
        });
    }

    @Override
    public ResponseEntity<String> saveHdbDedupMatching(HdbDedupMatchingEntity hdbDeupMatching) {
        String sql = "INSERT INTO hdb_dedup_matching (face_id, matching_face_id) VALUES (?, ?)";
        String shortId = RandomStringUtils.randomAlphanumeric(50);
        Object[] args = { shortId, hdbDeupMatching.getMatchingFaceId() };
        int rowsInserted = jdbcTemplate.update(sql, args);
        if (rowsInserted == 0) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Insert failed");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Inserted Successfully: " + hdbDeupMatching.toString());
        }
    }

    @Override
    public ResponseEntity<String> updateHdbDedupMatching(HdbDedupMatchingEntity hdbDedupMatching, String faceId) {
        String sql = "UPDATE hdb_dedup_matching SET face_id = ?, matching_face_id = ? WHERE face_id = ?";
        Object[] args = { hdbDedupMatching.getFaceId(), hdbDedupMatching.getMatchingFaceId(), faceId };
        int rowsUpdated = jdbcTemplate.update(sql, args);
        if (rowsUpdated == 0) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Update operation failed ");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Updated Successfully ");
        }
    }

    @Override
    public ResponseEntity<String> deleteHdbDedupMatching(String faceId) {
        String sql = "DELETE FROM hdb_dedup_matching WHERE face_id = ?";
        Object[] args = { faceId };
        int rowsDeleted = jdbcTemplate.update(sql, args);
        if (rowsDeleted == 0) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Deleted failed");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
        }
    }
}
