package com.example.postgresqlAPI.repository;

import com.example.postgresqlAPI.entity.HdbDedupFaceCheckEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HdbDedupFaceCheckRepository extends JpaRepository<HdbDedupFaceCheckEntity, String> {
}
