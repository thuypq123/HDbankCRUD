package com.example.postgresqlAPI.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "hdb_dedup_matching", schema = "public")
public class HdbDedupMatchingEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String FaceId;

    @Column
    private String MatchingFaceId;
    public HdbDedupMatchingEntity() {
    }

    public HdbDedupMatchingEntity(String faceId, String matchingFaceId) {
        FaceId = faceId;
        MatchingFaceId = matchingFaceId;
    }

    public String getFaceId() {
        return FaceId;
    }

    public void setFaceId(String faceId) {
        FaceId = faceId;
    }

    public String getMatchingFaceId() {
        return MatchingFaceId;
    }

    public void setMatchingFaceId(String matchingFaceId) {
        MatchingFaceId = matchingFaceId;
    }
}
