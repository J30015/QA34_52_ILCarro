package data_providers;


import dto.User1;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class UserDataProvider {
    @DataProvider
    public Iterator<User1> dataProviderForRegistrationWrongPasswordOrEmail() {
        List<User1> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader
                (new FileReader("src/test/resources/wrong_email_or_password.csv"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitLine = line.split(",");
                list.add(User1.builder()
                        .firstName(splitLine[0])
                        .lastName(splitLine[1])
                        .username(splitLine[2])
                        .password(splitLine[3]).build());
                line = bufferedReader.readLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("created exception");
        }
        return list.listIterator();


    }
}
