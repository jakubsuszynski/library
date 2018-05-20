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


    public Map<Params, String> parseArguments() {

        String[] arguments = userInputReader.getUserInput().split(" ");
        Map<Params, String> args = new HashMap<>();

        for (String argument : arguments) {

            if (argument.startsWith("-I"))
                isbnValidator.isValid(argument.substring(2));

            else if ((argumentsValidator.isLongEnough(argument))) {
                throw new RuntimeException(TOO_SHORT);
            }

            if (argument.startsWith("-I")) {
                args.put(Params.I, argument.substring(2));
            } else if (argument.startsWith("-T")) {
                args.put(Params.T, argument.substring(2));
            } else if (argument.startsWith("-W")) {
                args.put(Params.W, argument.substring(2));
            } else
                args.put(Params.A, argument.substring(2));
        }


        return args;

    }

}
