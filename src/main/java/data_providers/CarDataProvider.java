package data_providers;

import dto.Car;
import dto.User1;
import org.testng.annotations.DataProvider;
import utils.enums.Fuel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarDataProvider {
    @DataProvider
    public Iterator<Car> dataProviderForTypeLetTheCarWorkWrongYear() {
        List<Car> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader
                (new FileReader("src/test/resources/TypeLetTheCarWorkWrongYear.csv"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitLine = line.split(",");
                list.add(Car.builder()
                        .city(splitLine[0])
                        .manufacture(splitLine[1])
                        .model(splitLine[2])
                        .year(splitLine[3])
                        .fuel(Fuel.valueOf(splitLine[4].toUpperCase()))
                        .seats(Integer.parseInt(splitLine[5]))
                        .carClass(splitLine[6])
                        .serialNumber(splitLine[7])
                        .pricePerDay(Double.parseDouble(splitLine[8]))
                        .about(splitLine[9]).build());
                line = bufferedReader.readLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("created exception");
        }
        return list.listIterator();
    }
}