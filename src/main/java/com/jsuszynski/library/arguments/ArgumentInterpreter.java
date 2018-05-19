package com.jsuszynski.library.arguments;

import com.jsuszynski.library.books.IsbnValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ArgumentInterpreter {

    private final Scanner scanner = new Scanner(System.in);
    private final IsbnValidator isbnValidator = new IsbnValidator();

    public Map<String, String> parseArguments() {

        String[] arguments = askForArguments().split(" ");
        Map<String, String> args = new HashMap<>();
        for (String argument : arguments) {
            if (argument.startsWith("-I"))
                isbnValidator.isValid(argument.substring(2));
            args.put(argument.substring(0, 2), argument.substring(2));
        }

        return args;

    }

    private String askForArguments() {
        return scanner.nextLine();
    }
}
