package com.github.lithualien.thirdproject.service;

import com.github.lithualien.thirdproject.dao.FlowerDao;
import com.github.lithualien.thirdproject.domain.Description;
import com.github.lithualien.thirdproject.domain.Flower;
import com.github.lithualien.thirdproject.domain.Range;
import com.github.lithualien.thirdproject.domain.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FlowerServiceImpl implements FlowerService {

    private FlowerDao flowerDao;

    @Autowired
    public void setFlowerDao(FlowerDao flowerDao) {
        this.flowerDao = flowerDao;
    }

    @Override
    public List<Flower> getFlowers() {
        return flowerDao.getFlowers();
    }

    @Override
    public Type findFlowerType(Flower flower) {
        List<Flower> flowers = flowerDao.getFlowers();
        List<Description> sepalLength = new ArrayList<>();
        List<Description> sepalWidth = new ArrayList<>();
        List<Description> petalLength = new ArrayList<>();
        List<Description> petalWidth = new ArrayList<>();
        List<Range> sepalLengthRange = new ArrayList<>();
        List<Range> sepalWidthRange = new ArrayList<>();
        List<Range> petalLengthRange = new ArrayList<>();
        List<Range> petalWidthRange = new ArrayList<>();
        List<String> types = new ArrayList<>();

        copyDataToList(flowers, sepalLength, sepalWidth, petalLength, petalWidth);
        sortArray(sepalLength);
        sortArray(sepalWidth);
        sortArray(petalLength);
        sortArray(petalWidth);

        setFlowerRanges(sepalLength, sepalLengthRange);
        setFlowerRanges(sepalWidth, sepalWidthRange);
        setFlowerRanges(petalLength, petalLengthRange);
        setFlowerRanges(petalWidth, petalWidthRange);

        getGivenRangeFlowerTypes(types, flower.getSepalLength(), sepalLengthRange);
        getGivenRangeFlowerTypes(types, flower.getSepalWidth(), sepalWidthRange);
        getGivenRangeFlowerTypes(types, flower.getPetalLength(), petalLengthRange);
        getGivenRangeFlowerTypes(types, flower.getPetalWidth(), petalWidthRange);

        if(isOne(types)) {
            return new Type(findFlowerTypeIfOne(types));
        }

        types.clear();
        findFlowerTypeIfMultiple(types, sepalLengthRange, flower.getSepalLength());
        findFlowerTypeIfMultiple(types, sepalWidthRange, flower.getSepalWidth());
        findFlowerTypeIfMultiple(types, petalLengthRange, flower.getPetalLength());
        findFlowerTypeIfMultiple(types, petalWidthRange, flower.getPetalWidth());
        return new Type(findFlowerTypeIfOne(types));
    }

    @Override
    public void saveFlower(Flower flower) {
        flowerDao.saveFlower(flower);
    }

    private void copyDataToList(List<Flower> flowers, List<Description> sepalLength, List<Description> sepalWidth, List<Description> petalLength, List<Description> petalWidth) {
        flowers
                .forEach(e -> {
                    sepalLength.add(new Description(e.getSepalLength(), e.getSpecie()));
                    sepalWidth.add(new Description(e.getSepalWidth(), e.getSpecie()));
                    petalLength.add(new Description(e.getPetalLength(), e.getSpecie()));
                    petalWidth.add(new Description(e.getPetalWidth(), e.getSpecie()));
                });
    }

    private void sortArray(List<Description> descriptions) {
        descriptions.sort(Comparator.comparing(Description::getSize));
    }

    private void setFlowerRanges(List<Description> descriptions, List<Range> ranges) {
        boolean start = false;
        int repeatingCounter = 0;
        float startRange = 0;
        for(int i = 0; i < descriptions.size(); i++) {
            if(!start) {
                start = true;
                startRange = descriptions.get(i).getSize();
            }

            if(i == descriptions.size() - 1) {
                ranges.add(new Range(startRange, descriptions.get(i).getSize(), repeatingCounter + 1, descriptions.get(i).getSpecie()));
                break;
            }

            if(!descriptions.get(i).getSpecie().equals(descriptions.get(i+1).getSpecie())) {
                ranges.add(new Range(startRange, descriptions.get(i+1).getSize(), repeatingCounter + 1, descriptions.get(i).getSpecie()));
                start = false;
                repeatingCounter = 0;
            }
            repeatingCounter++;
        }
    }

    private void getGivenRangeFlowerTypes(List<String> types, float range, List<Range> ranges) {
        for(int i = 0; i < ranges.size(); i++) {
            if(i == 0 && ranges.get(i).getRangeStartingPoint() >= range) {
                types.add(ranges.get(i).getSpecie());
            }

            if(i == ranges.size() - 1 && ranges.get(i).getRangeEndingPoint() < range) {
                types.add(ranges.get(i).getSpecie());
            }

            if(range == ranges.get(i).getRangeStartingPoint() && range == ranges.get(i).getRangeEndingPoint()) {
                types.add(ranges.get(i).getSpecie());
            }

            if(range > ranges.get(i).getRangeStartingPoint() && range <= ranges.get(i).getRangeEndingPoint()) {
                types.add(ranges.get(i).getSpecie());
            }
        }
    }

    private boolean isOne(List<String> types) {
        int setosa = findRepeatingFlowerTypes(types, "Iris-setosa");
        int versicolor = findRepeatingFlowerTypes(types, "Iris-versicolor");
        int virginica = findRepeatingFlowerTypes(types, "Iris-virginica");

        if(setosa == versicolor) {
            return false;
        }

        if(setosa == virginica) {
            return false;
        }

        if(versicolor == virginica) {
            return false;
        }

        return true;
    }

    private int findRepeatingFlowerTypes(List<String> types, String  condition) {
        int typeCounter = 0;
        for(String type : types) {
            if(type.equals(condition)) {
                typeCounter++;
            }
        }
        return typeCounter;
    }

    private String findFlowerTypeIfOne(List<String> types) {
        int setosa = findRepeatingFlowerTypes(types, "Iris-setosa");
        int versicolor = findRepeatingFlowerTypes(types, "Iris-versicolor");
        int virginica = findRepeatingFlowerTypes(types, "Iris-virginica");

        if(setosa > versicolor && setosa > virginica) {
            return "Iris-setosa";
        }

        if(versicolor > setosa && versicolor > virginica) {
            return "Iris-versicolor";
        }

        return "Iris-virginica";
    }

    private void findFlowerTypeIfMultiple(List<String> types, List<Range> ranges, float range) {
        List<Range> rangeList;
        int startIndex = 0;
        int endIndex = 0;

        for(int i = 0; i < ranges.size(); i++) {
            if(i == ranges.size() - 1 && ranges.get(i).getRangeEndingPoint() < range) {
                startIndex = i;
                endIndex = i;
                break;
            }

            if(range == ranges.get(i).getRangeStartingPoint() || range == ranges.get(i).getRangeEndingPoint()) {
                startIndex = i;
                endIndex = i;
                break;
            }

            if(range > ranges.get(i).getRangeStartingPoint() && range <= ranges.get(i).getRangeEndingPoint()) {
                startIndex = i;
                endIndex = i;
                break;
            }
        }

        if(startIndex != ranges.size() - 1) {
            for(int i = startIndex + 1; i < ranges.size(); i++) {
                if(ranges.get(i).getRangeStartingPoint() > range) {
                    endIndex = i - 1;
                    break;
                }
            }
        }
        rangeList = ranges.subList(startIndex, endIndex+1);

        Range maxValue = rangeList
                .stream()
                .max(Comparator.comparing(Range::getRepeat))
                .get();

        types.add(maxValue.getSpecie());
    }
}
