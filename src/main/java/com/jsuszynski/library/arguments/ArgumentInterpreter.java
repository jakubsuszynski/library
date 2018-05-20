package com.jsuszynski.library.arguments;

import com.jsuszynski.library.books.IsbnValidator;
import com.jsuszynski.library.console.UserInputReader;

import java.util.HashMap;
import java.util.Map;

public class ArgumentInterpreter {

    private static final String TOO_SHORT = "Podany parametr jest zbyt krótki.";
    public static final String WRONG_PARAMS = "Podano nieistniejące parametry.";

    private final IsbnValidator isbnValidator = new IsbnValidator();
    private final UserInputReader userInputReader = new UserInputReader();
    private final ArgumentsValidator argumentsValidator = new ArgumentsValidator();
    private final Map<Params, String> args = new HashMap<>();

    public Map<Params, String> parseArguments() {

        String[] arguments = userInputReader.getUserInput().split(" ");


        for (String argument : arguments) {

            if (argument.startsWith("-I"))
                isbnValidator.isValid(argument.substring(2));

            else if ((argumentsValidator.isLongEnough(argument))) {
                throw new RuntimeException(TOO_SHORT);
            }
            getEntrySet(argument);
        }


        return args;

    }

    private void getEntrySet(String argument) {
        try {
            args.put(getKey(argument), argument.substring(2));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(WRONG_PARAMS);
        }
    }

    private Params getKey(String argument) {
        return Params.valueOf(String.valueOf(argument.charAt(1)));
    }

}
