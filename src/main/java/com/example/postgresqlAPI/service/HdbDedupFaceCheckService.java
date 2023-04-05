package com.example.postgresqlAPI.service;

import com.example.postgresqlAPI.entity.HdbDedupFaceCheckEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HdbDedupFaceCheckService {
    List<HdbDedupFaceCheckEntity> getAllHdbDedupFaceCheck();

    HdbDedupFaceCheckEntity getHdbDedupFaceCheckByFaceId(String FaceId);
    ResponseEntity<String> saveHdbDedupFaceCheck(HdbDedupFaceCheckEntity hdbDedupFaceCheckEntity);
    ResponseEntity<String> updateHdbDedupFaceCheck(HdbDedupFaceCheckEntity hdbDedupFaceCheckEntity, String FaceId);
    ResponseEntity<String> deleteHdbDedupFaceCheck(String FaceId);
}
