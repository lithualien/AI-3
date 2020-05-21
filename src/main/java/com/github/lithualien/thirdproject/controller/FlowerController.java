package com.github.lithualien.thirdproject.controller;

import com.github.lithualien.thirdproject.domain.Flower;
import com.github.lithualien.thirdproject.domain.Type;
import com.github.lithualien.thirdproject.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class FlowerController {

    private final FlowerService flowerService;

    public FlowerController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @GetMapping("/flowers")
    public ResponseEntity<List<Flower>> all() {
        if(flowerService.getFlowers() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flowerService.getFlowers());
    }

    @PostMapping("/flowers/check")
    public ResponseEntity<Type> getFlowerType(@RequestBody Flower flower) {
        if(flower.getPetalLength() == 0f || flower.getPetalWidth() == 0f || flower.getSepalLength() == 0f || flower.getSepalWidth() == 0f) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flowerService.findFlowerType(flower));
    }

    @PostMapping("/flowers")
    public ResponseEntity<HttpStatus> save(@RequestBody Flower flower) {
        if(flower.getPetalLength() == 0f || flower.getPetalWidth() == 0f || flower.getSepalLength() == 0f || flower.getSepalWidth() == 0f || flower.getSpecie().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        flowerService.saveFlower(flower);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

}