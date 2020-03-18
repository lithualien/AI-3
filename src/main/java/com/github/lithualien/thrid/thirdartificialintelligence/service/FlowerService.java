package com.github.lithualien.thrid.thirdartificialintelligence.service;

import com.github.lithualien.thrid.thirdartificialintelligence.entity.Flower;
import com.github.lithualien.thrid.thirdartificialintelligence.entity.Type;

import java.util.List;

public interface FlowerService {

    List<Flower> getFlowers();
    Type findFlowerType(Flower flower);
    void saveFlower(Flower flower);
}
