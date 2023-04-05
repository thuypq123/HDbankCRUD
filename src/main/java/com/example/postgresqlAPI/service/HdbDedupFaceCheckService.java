package com.example.postgresqlAPI.service;

import com.example.postgresqlAPI.entity.HdbDedupFaceCheckEntity;

import java.util.List;

public interface HdbDedupFaceCheckService {
    List<HdbDedupFaceCheckEntity> getAllHdbDedupFaceCheck();

    HdbDedupFaceCheckEntity getHdbDedupFaceCheckByFaceId(String FaceId);
    void saveHdbDedupFaceCheck(HdbDedupFaceCheckEntity hdbDedupFaceCheckEntity);
    void updateHdbDedupFaceCheck(HdbDedupFaceCheckEntity hdbDedupFaceCheckEntity, String FaceId);
    void deleteHdbDedupFaceCheck(String FaceId);
}
