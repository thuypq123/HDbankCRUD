package com.example.postgresqlAPI.service.impl;

import com.example.postgresqlAPI.entity.HdbDedupFaceCheckEntity;
import com.example.postgresqlAPI.repository.HdbDedupFaceCheckRepository;
import com.example.postgresqlAPI.service.HdbDedupFaceCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class HdbDedupFaceCheckImpl implements HdbDedupFaceCheckService {
    @Autowired
    private HdbDedupFaceCheckRepository hdbDedupFaceCheckRepository;

    @Override
    public List<HdbDedupFaceCheckEntity> getAllHdbDedupFaceCheck() {
        return hdbDedupFaceCheckRepository.findAll();
    }

    @Override
    public HdbDedupFaceCheckEntity getHdbDedupFaceCheckByFaceId(String FaceId) {
        Optional<HdbDedupFaceCheckEntity> hdbDedupFaceCheckOtp = hdbDedupFaceCheckRepository.findById(FaceId);
        if (hdbDedupFaceCheckOtp.isPresent()){
            return hdbDedupFaceCheckOtp.get();
        }else {
            throw new RuntimeException("user not found.");
        }
    }

    @Override
    public void saveHdbDedupFaceCheck(HdbDedupFaceCheckEntity hdbDedupFaceCheckEntity) {
        HdbDedupFaceCheckEntity hdbDedupFaceCheckDetail = hdbDedupFaceCheckRepository.save(hdbDedupFaceCheckEntity);
    }

    @Override
    public void updateHdbDedupFaceCheck(HdbDedupFaceCheckEntity hdbDedupFaceCheckEntity, String FaceId) {

    }

    @Override
    public void deleteHdbDedupFaceCheck(String FaceId) {

    }
}
