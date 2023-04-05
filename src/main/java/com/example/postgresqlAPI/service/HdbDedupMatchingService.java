package com.example.postgresqlAPI.service;

import com.example.postgresqlAPI.entity.HdbDedupMatchingEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HdbDedupMatchingService {
    List<HdbDedupMatchingEntity> getAllHdbDedupMatching();

    HdbDedupMatchingEntity getHdbDedupMatchingByFaceId(String FaceId);
    ResponseEntity<String> saveHdbDedupMatching(HdbDedupMatchingEntity hdbDedupMatching);
    ResponseEntity<String> updateHdbDedupMatching(HdbDedupMatchingEntity hdbDedupMatching, String FaceId);
    ResponseEntity<String> deleteHdbDedupMatching(String FaceId);
}
