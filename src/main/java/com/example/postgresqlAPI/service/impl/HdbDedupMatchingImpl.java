package com.example.postgresqlAPI.service.impl;

import com.example.postgresqlAPI.entity.HdbDedupMatchingEntity;
import com.example.postgresqlAPI.repository.HdbDedupMatchingRepository;
import com.example.postgresqlAPI.service.HdbDedupMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HdbDedupMatchingImpl implements HdbDedupMatchingService {
    @Autowired
    private HdbDedupMatchingRepository hdbDedupMatchingRepository;
    @Override
    public List<HdbDedupMatchingEntity> getAllHdbDedupMatching() {
        return hdbDedupMatchingRepository.findAll();
    }

    @Override
    public HdbDedupMatchingEntity getHdbDedupMatchingByFaceId(String FaceId) {
        Optional<HdbDedupMatchingEntity> hdbDeupMatchingOtp = hdbDedupMatchingRepository.findById(FaceId);
        if (hdbDeupMatchingOtp.isPresent()){
            return hdbDeupMatchingOtp.get();
        }else {
            throw new RuntimeException("user not found.");
        }
    }

    @Override
    public void saveHdbDedupMatching(HdbDedupMatchingEntity hdbDeupMatching) {
        HdbDedupMatchingEntity hdbDeupMatchingDetail = hdbDedupMatchingRepository.save(hdbDeupMatching);
    }
    @Override
    public void updateHdbDedupMatching(HdbDedupMatchingEntity hdbDedupMatching, String FaceId) {
        Optional<HdbDedupMatchingEntity> hdbDeupMatchingDetailOtp = hdbDedupMatchingRepository.findById(FaceId);
        if (hdbDeupMatchingDetailOtp.isPresent()){
            HdbDedupMatchingEntity hdbDeupMatchingDetail = hdbDeupMatchingDetailOtp.get();
            if (hdbDedupMatching.getFaceId() != null || hdbDedupMatching.getFaceId().isEmpty()){
                hdbDeupMatchingDetail.setFaceId(hdbDedupMatching.getFaceId());
            }
            if (hdbDedupMatching.getMatchingFaceId() != null || hdbDedupMatching.getMatchingFaceId().isEmpty()){
                hdbDeupMatchingDetail.setMatchingFaceId(hdbDedupMatching.getMatchingFaceId());
            }
            hdbDedupMatchingRepository.save(hdbDeupMatchingDetail);
        }else {
            throw new RuntimeException("Cannot update HdbDeupMatching");
        }
    }

    @Override
    public void deleteHdbDedupMatching(String FaceId) {
        Optional<HdbDedupMatchingEntity> hdbDedupMatching = hdbDedupMatchingRepository.findById(FaceId);
        if(hdbDedupMatching.isPresent()){
            hdbDedupMatchingRepository.deleteById(FaceId);
        }else {
            throw new RuntimeException("Cannot delete this HdbDeupMatching");
        }
    }
}
