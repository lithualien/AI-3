package com.github.lithualien.thirdproject.dao;

import com.github.lithualien.thirdproject.domain.Flower;
import com.opencsv.CSVWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class FlowerDaoImpl implements FlowerDao {

    private static final String FILE = "static/data/data.csv";
    private static final String COMMA_DELIMITER = ",";

    @Override
    public List<Flower> getFlowers() {
        List<Flower> flowers = null;

        try (InputStream resource = new ClassPathResource(FILE).getInputStream()) {
            flowers = new BufferedReader(new InputStreamReader(resource))
                    .lines()
                    .skip(1)
                    .map(e -> createFlower(e.split(COMMA_DELIMITER)))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flowers;
    }

    @Override
    public void saveFlower(Flower flower) {
        try (FileWriter fileWriter = new FileWriter(getFile(),true);
             CSVWriter writer = new CSVWriter(fileWriter)) {
            writer.writeNext(convertToStringArray(flower), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // hard coded part, shouldn't be like this, will try to think about a solution.
    private Flower createFlower(String[] flower) {
        return new Flower (
                Float.parseFloat(flower[0]),
                Float.parseFloat(flower[1]),
                Float.parseFloat(flower[2]),
                Float.parseFloat(flower[3]),
                flower[4]
        );
    }

    private String[] convertToStringArray(Flower flower) {
        return new String[] { String.valueOf(flower.getSepalLength()), String.valueOf(flower.getSepalWidth()),
                String.valueOf(flower.getPetalLength()), String.valueOf(flower.getPetalWidth()), flower.getSpecie()
        };
    }

    private File getFile() {
        return new File(Objects.requireNonNull(getClass().getClassLoader().getResource(FILE)).getFile());
    }
}
