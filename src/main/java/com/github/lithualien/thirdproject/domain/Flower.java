package com.github.lithualien.thirdproject.domain;

public class Flower {

    float sepalLength;
    float sepalWidth;
    float petalLength;
    float petalWidth;
    String specie;

    public Flower() {
    }

    public Flower(float sepalLength, float sepalWidth, float petalLength, float petalWidth, String specie) {
        this.sepalLength = sepalLength;
        this.sepalWidth = sepalWidth;
        this.petalLength = petalLength;
        this.petalWidth = petalWidth;
        this.specie = specie;
    }

    public float getSepalLength() {
        return sepalLength;
    }

    public void setSepalLength(float sepalLength) {
        this.sepalLength = sepalLength;
    }

    public float getSepalWidth() {
        return sepalWidth;
    }

    public void setSepalWidth(float sepalWidth) {
        this.sepalWidth = sepalWidth;
    }

    public float getPetalLength() {
        return petalLength;
    }

    public void setPetalLength(float petalLength) {
        this.petalLength = petalLength;
    }

    public float getPetalWidth() {
        return petalWidth;
    }

    public void setPetalWidth(float petalWidth) {
        this.petalWidth = petalWidth;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "sepalLength=" + sepalLength +
                ", sepalWidth=" + sepalWidth +
                ", petalLength=" + petalLength +
                ", petalWidth=" + petalWidth +
                ", specie='" + specie + '\'' +
                '}';
    }
}
