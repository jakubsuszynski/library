package com.jsuszynski.library.console;

import java.util.Scanner;

public class UserInputReader {

    private static final String USER_INPUT_INDICATOR = "> ";

    public String getUserInput() {
        System.out.print(USER_INPUT_INDICATOR);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
