package ru.netology.utils;

import net.datafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class GeneratorUtils {

    private static final String specialCharacters = "!@#$%^&*()_+-=[]{}|;':,.<>/?";

    public static final Faker faker = new Faker();

    public static String generateFirstName() {
        return faker.name().firstName();
    }

    public static String generateLastName() {
        return faker.name().lastName();
    }

    public static String generateGoodHolderName() {
        return generateFirstName() + " " + generateLastName();
    }

    public static String generateMonth(int diffMonths) {
        return LocalDate.now()
                .plusMonths(diffMonths)
                .format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateYear(int diffYears) {
        return LocalDate.now()
                .plusYears(diffYears)
                .format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateCyrillic() {
        Faker fakerRu = new Faker(new Locale("ru"));
        return fakerRu.name().firstName();
    }

    public static String generateString(int length) {
        return faker.lorem().characters(length, length);
    }

    public static String generateSpecialCharacters(int length) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = faker.random().nextInt(specialCharacters.length());
            str.append(specialCharacters.charAt(index));
        }
        return str.toString();
    }

}
