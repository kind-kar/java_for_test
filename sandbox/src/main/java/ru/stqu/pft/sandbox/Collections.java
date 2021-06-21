package ru.stqu.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
    public static void main(String[] args) {
        String[] langs = {"Java", "Python", "PHP"};

        for (String l: langs) {
            System.out.println(l);
        }

        List<String> languages = Arrays.asList("Java", "Python", "PHP");


        for (String l: languages) {
            System.out.println(l);
        }
    }
}
