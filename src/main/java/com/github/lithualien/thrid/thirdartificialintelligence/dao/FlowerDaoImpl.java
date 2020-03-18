package com.github.lithualien.thrid.thirdartificialintelligence.dao;

import com.github.lithualien.thrid.thirdartificialintelligence.entity.Flower;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FlowerDaoImpl implements FlowerDao {

    private static final String FILE = "static/data/data.csv";
    private static final String COMMA_DELIMITER = ",";
    private List<Flower> flowers;
    private ResourceLoader resourceLoader;

    @Override
    public List<Flower> getFlowers() {
        readFile();
        return flowers;
    }

    @Override
    public void saveFlower(Flower flower) {
        File file = new File(getClass().getClassLoader().getResource(FILE).getFile());
        try {
            FileWriter fileWriter = new FileWriter(file,true);
            CSVWriter writer = new CSVWriter(fileWriter);
            writer.writeNext(convertToStringArray(flower), false);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile() {
        try {
            InputStream resource = new ClassPathResource(FILE).getInputStream();
            flowers = new BufferedReader(new InputStreamReader(resource))
                    .lines()
                    .skip(1)
                    .map(e -> createFlower(e.split(COMMA_DELIMITER)))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Flower createFlower(String[] flower) {
        return new Flower(
                Float.parseFloat(flower[0]),
                Float.parseFloat(flower[1]),
                Float.parseFloat(flower[2]),
                Float.parseFloat(flower[3]),
                flower[4]
        );
    }

    private String[] convertToStringArray(Flower flower) {
        return new String[] { String.valueOf(flower.getSepalLength()), String.valueOf(flower.getSepalWidth()),
                String.valueOf(flower.getPetalLength()), String.valueOf(flower.getPetalWidth()), flower.getSpecie()};
    }

    @Autowired
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
