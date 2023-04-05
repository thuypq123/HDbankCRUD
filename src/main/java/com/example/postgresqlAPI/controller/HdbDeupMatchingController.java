package com.example.postgresqlAPI.controller;

import com.example.postgresqlAPI.entity.HdbDedupMatchingEntity;
import com.example.postgresqlAPI.service.HdbDedupMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HdbDeupMatching")
@CrossOrigin(origins = "*")
public class HdbDeupMatchingController {
    @Autowired
    private HdbDedupMatchingService hdbDeupMatchingService;

    @GetMapping("/")
    public List<HdbDedupMatchingEntity> getAllHdbDeupMatching() {
        List<HdbDedupMatchingEntity> hdbDedupMatchingEntities = hdbDeupMatchingService.getAllHdbDedupMatching();
        return hdbDedupMatchingEntities;
    }
    @GetMapping("/{FaceId}")
    public HdbDedupMatchingEntity getHdbDeupMatchingByFaceId(@PathVariable String FaceId){
        HdbDedupMatchingEntity hdbDedupMatching = hdbDeupMatchingService.getHdbDedupMatchingByFaceId(FaceId);
        return hdbDedupMatching;
    }
    @PostMapping
    public String saveHdbDedupMatching(@RequestBody HdbDedupMatchingEntity hdbDedupMatching){
        hdbDeupMatchingService.saveHdbDedupMatching(hdbDedupMatching);
        return "Save Sucessfully";
    }
    @PutMapping("/{FaceId}")
    public String updateHdbDedupMatching(@RequestBody HdbDedupMatchingEntity hdbDedupMatching, @PathVariable String FaceId){
        hdbDeupMatchingService.updateHdbDedupMatching(hdbDedupMatching, FaceId);
        return "update successfully";
    }
    @DeleteMapping("/{FaceId}")
    public String deleteHdbDedupMatching(@PathVariable String FaceId){
        hdbDeupMatchingService.deleteHdbDedupMatching(FaceId);
        return "deleted";
    }
}
