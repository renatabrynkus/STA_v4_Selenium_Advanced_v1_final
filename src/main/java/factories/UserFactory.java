package factories;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import models.User;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class UserFactory {

    public User getRandomUser() {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("pl-PL"), new RandomService());
        Faker faker = new Faker();
        return User.builder()
                .socialTitle("Mr.")
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(fakeValuesService.bothify("??????##@gmail.com"))
                .password(fakeValuesService.bothify("??????###"))
                .birthDate(getFakeBirthDate(faker))
                .offersFlag(faker.random().nextBoolean())
                .dataPrivacyFlag(true)
                .newsletterFlag(faker.random().nextBoolean())
                .privacyPolicyFlag(true)
                .build();
    }

    private static String getFakeBirthDate(Faker faker) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return simpleDateFormat.format(faker.date().birthday());
    }
}
