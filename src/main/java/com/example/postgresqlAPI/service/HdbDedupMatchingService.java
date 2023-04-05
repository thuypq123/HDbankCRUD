package com.example.postgresqlAPI.service;

import com.example.postgresqlAPI.entity.HdbDedupMatchingEntity;

import java.util.List;

public interface HdbDedupMatchingService {
    List<HdbDedupMatchingEntity> getAllHdbDedupMatching();

    HdbDedupMatchingEntity getHdbDedupMatchingByFaceId(String FaceId);
    void saveHdbDedupMatching(HdbDedupMatchingEntity hdbDedupMatching);
    void updateHdbDedupMatching(HdbDedupMatchingEntity hdbDedupMatching, String FaceId);
    void deleteHdbDedupMatching(String FaceId);
}
