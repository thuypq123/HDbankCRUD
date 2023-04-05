package com.example.postgresqlAPI.controller;

import com.example.postgresqlAPI.entity.HdbDedupMatchingEntity;
import com.example.postgresqlAPI.service.HdbDedupMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> saveHdbDedupMatching(@RequestBody HdbDedupMatchingEntity hdbDedupMatching){
        return hdbDeupMatchingService.saveHdbDedupMatching(hdbDedupMatching);
    }
    @PutMapping("/{FaceId}")
    public ResponseEntity<String> updateHdbDedupMatching(@RequestBody HdbDedupMatchingEntity hdbDedupMatching, @PathVariable String FaceId){
        return hdbDeupMatchingService.updateHdbDedupMatching(hdbDedupMatching, FaceId);
    }
    @RequestMapping(value = "/{FaceId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteHdbDedupMatching(@PathVariable String FaceId){
        return hdbDeupMatchingService.deleteHdbDedupMatching(FaceId);
    }
}
