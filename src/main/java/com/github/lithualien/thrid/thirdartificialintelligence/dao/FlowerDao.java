package com.github.lithualien.thrid.thirdartificialintelligence.dao;

import com.github.lithualien.thrid.thirdartificialintelligence.entity.Flower;

import java.util.List;

public interface FlowerDao {

    List<Flower> getFlowers();
    void saveFlower(Flower flower);
}
