package com.jsuszynski.library.arguments;

import com.jsuszynski.library.books.IsbnValidator;
import com.jsuszynski.library.console.UserInputReader;

import java.util.HashMap;
import java.util.Map;

public class ArgumentInterpreter {

    private static final String TOO_SHORT = "Podany parametr jest zbyt krótki";

    private final IsbnValidator isbnValidator = new IsbnValidator();
    private final UserInputReader userInputReader = new UserInputReader();
    private final ArgumentsValidator argumentsValidator = new ArgumentsValidator();


    public Map<String, String> parseArguments() {

        String[] arguments = userInputReader.getUserInput().split(" ");
        Map<String, String> args = new HashMap<>();

        for (String argument : arguments) {

            if (argument.startsWith("-I"))
                isbnValidator.isValid(argument.substring(2));

            else if ((argumentsValidator.isLongEnough(argument))) {
                throw new RuntimeException(TOO_SHORT);
            }
            args.put(argument.substring(0, 2), argument.substring(2));
        }

        return args;

    }

}
