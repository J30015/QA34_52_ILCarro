package utils;


import dto.User1;
import net.datafaker.Faker;

public class UserFactory {
    static Faker faker = new Faker();
    public static User1 positiveUser(){
        User1 user = User1.builder()
                .username(faker.internet().emailAddress())
                .password("4tzNnQrGn96S!!4")
                .build();

        return user;
    }

}
