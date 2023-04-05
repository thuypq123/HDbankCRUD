package com.example.postgresqlAPI.controller;

import com.example.postgresqlAPI.entity.HdbDedupFaceCheckEntity;
import com.example.postgresqlAPI.service.HdbDedupFaceCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HdbDedupFaceCheck")
@CrossOrigin(origins = "*")
public class HdbDedupFaceCheckController {
    @Autowired
    private HdbDedupFaceCheckService hdbDedupFaceCheckService;

    @GetMapping("/")
    public List<HdbDedupFaceCheckEntity> getAllHdbDeupFaceCheck() {
        List<HdbDedupFaceCheckEntity> hdbDedupFaceCheckEntities = hdbDedupFaceCheckService.getAllHdbDedupFaceCheck();
        return hdbDedupFaceCheckEntities;
    }

    @GetMapping("/{FaceId}")
    public HdbDedupFaceCheckEntity getHdbDeupFaceCheck(@PathVariable String FaceId) {
        HdbDedupFaceCheckEntity hdbDedupFaceCheckEntity = hdbDedupFaceCheckService.getHdbDedupFaceCheckByFaceId(FaceId);
        return hdbDedupFaceCheckEntity;
    }

    @PostMapping
    public ResponseEntity<String> saveHdbDeupFaceCheck(@RequestBody HdbDedupFaceCheckEntity hdbDedupFaceCheckEntity) {
        return hdbDedupFaceCheckService.saveHdbDedupFaceCheck(hdbDedupFaceCheckEntity);
    }

    @PutMapping
    public ResponseEntity<String> updateDedupFaceCheck(@RequestBody HdbDedupFaceCheckEntity hdbDedupFaceCheckEntity,
            String FaceId) {
        return hdbDedupFaceCheckService.updateHdbDedupFaceCheck(hdbDedupFaceCheckEntity, FaceId);
    }

    @DeleteMapping("/{FaceId}")
    public ResponseEntity<String> deleteDedupFaceCheck(@PathVariable String FaceId) {
        return hdbDedupFaceCheckService.deleteHdbDedupFaceCheck(FaceId);
    }
}
