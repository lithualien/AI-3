package com.github.lithualien.thirdproject.domain;

public class Description {

    float size;
    private String specie;

    public Description() {
    }

    public Description(float size, String specie) {
        this.size = size;
        this.specie = specie;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    @Override
    public String toString() {
        return "Description{" +
                "size=" + size +
                ", specie='" + specie + '\'' +
                '}';
    }
}
