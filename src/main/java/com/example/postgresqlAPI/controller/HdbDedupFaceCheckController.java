package com.example.postgresqlAPI.controller;

import com.example.postgresqlAPI.entity.HdbDedupFaceCheckEntity;
import com.example.postgresqlAPI.service.HdbDedupFaceCheckService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public HdbDedupFaceCheckEntity getHdbDeupFaceCheck(@PathVariable String FaceId){
        HdbDedupFaceCheckEntity hdbDedupFaceCheckEntity = hdbDedupFaceCheckService.getHdbDedupFaceCheckByFaceId(FaceId);
        return hdbDedupFaceCheckEntity;
    }
    @PostMapping
    public String saveHdbDeupFaceCheck(@RequestBody HdbDedupFaceCheckEntity hdbDedupFaceCheckEntity){
        hdbDedupFaceCheckService.saveHdbDedupFaceCheck(hdbDedupFaceCheckEntity);
        return "save successfully";
    }
    @PutMapping
    public String updateDedupFaceCheck(@RequestBody HdbDedupFaceCheckEntity hdbDedupFaceCheckEntity, String FaceId){
        hdbDedupFaceCheckService.updateHdbDedupFaceCheck(hdbDedupFaceCheckEntity, FaceId);
        return "Update successfully";
    }
    @DeleteMapping("/{FaceId}")
    public String deleteDedupFaceCheck(@PathVariable String FaceId){
        hdbDedupFaceCheckService.deleteHdbDedupFaceCheck(FaceId);
        return "Delete SuccessFul";
    }
}
