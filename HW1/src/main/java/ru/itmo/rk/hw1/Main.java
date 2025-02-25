package ru.itmo.rk.hw1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Input sentence > ");
        LanguageValidator.validate(new Scanner(System.in).nextLine()).prettyPrint();
    }
}