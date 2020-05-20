package com.github.lithualien.thirdproject.service;

import com.github.lithualien.thirdproject.domain.Flower;
import com.github.lithualien.thirdproject.domain.Type;

import java.util.List;

public interface FlowerService {

    List<Flower> getFlowers();
    Type findFlowerType(Flower flower);
    void saveFlower(Flower flower);
}
