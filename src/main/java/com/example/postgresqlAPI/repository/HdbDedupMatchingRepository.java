package com.example.postgresqlAPI.repository;

import com.example.postgresqlAPI.entity.HdbDedupMatchingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HdbDedupMatchingRepository extends JpaRepository<HdbDedupMatchingEntity, String> {
}
