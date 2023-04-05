package com.example.postgresqlAPI.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "hdb_dedup_face_check", schema = "public")
public class HdbDedupFaceCheckEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String FaceId;
    @Column
    private boolean IsChecked;
    public HdbDedupFaceCheckEntity() {
    }
    public HdbDedupFaceCheckEntity(String faceId, boolean isChecked) {
        FaceId = faceId;
        IsChecked = isChecked;
    }

    public String getFaceId() {
        return FaceId;
    }

    public void setFaceId(String faceId) {
        FaceId = faceId;
    }

    public boolean isChecked() {
        return IsChecked;
    }

    public void setChecked(boolean checked) {
        IsChecked = checked;
    }
}
