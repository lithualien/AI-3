package com.github.lithualien.thirdproject.domain;

public class Range {
    float rangeStartingPoint;
    float rangeEndingPoint;
    int repeat;
    String specie;

    public Range() {
    }

    public Range(float rangeStartingPoint, float rangeEndingPoint, int repeat, String specie) {
        this.rangeStartingPoint = rangeStartingPoint;
        this.rangeEndingPoint = rangeEndingPoint;
        this.repeat = repeat;
        this.specie = specie;
    }

    public float getRangeStartingPoint() {
        return rangeStartingPoint;
    }

    public void setRangeStartingPoint(float rangeStartingPoint) {
        this.rangeStartingPoint = rangeStartingPoint;
    }

    public float getRangeEndingPoint() {
        return rangeEndingPoint;
    }

    public void setRangeEndingPoint(float rangeEndingPoint) {
        this.rangeEndingPoint = rangeEndingPoint;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    @Override
    public String toString() {
        return "Range{" +
                "rangeStartingPoint=" + rangeStartingPoint +
                ", rangeEndingPoint=" + rangeEndingPoint +
                ", repeat=" + repeat +
                ", specie='" + specie + '\'' +
                '}';
    }
}
