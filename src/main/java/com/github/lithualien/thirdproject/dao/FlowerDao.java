package com.github.lithualien.thirdproject.dao;

import com.github.lithualien.thirdproject.domain.Flower;

import java.util.List;

public interface FlowerDao {

    List<Flower> getFlowers();
    void saveFlower(Flower flower);
}
