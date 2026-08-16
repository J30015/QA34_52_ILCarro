package utils;


import dto.User1;
import net.datafaker.Faker;

public class UserFactory {
    static Faker faker = new Faker();
    public static User1 positiveUser(){
        User1 user = User1.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .username(faker.internet().emailAddress())
                .password(PropertiesReader.getProperty
                        ("base.properties","password_for_registration"))
                .build();

        return user;
    }

}
