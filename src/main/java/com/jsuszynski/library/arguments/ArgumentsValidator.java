package com.jsuszynski.library.arguments;

import java.util.Map;

public class ArgumentsValidator {

    public Boolean addingBooksParams(Map<String, String> args) {
        return (containsIsbn(args) && containsTitle(args) && containsAuthor(args));
    }

    public Boolean deletingBooksParams(Map<String, String> args) {
        return (hasOneParam(args) && (containsTitle(args) || containsIsbn(args)));
    }

    public Boolean findingBooksParams(Map<String, String> args) {
        return (!containsIsbn(args) && !containsTitle(args) && !containsAuthor(args));
    }

    public Boolean lendingBooksParams(Map<String, String> args) {
        return (containsReader(args) && (containsTitle(args) || containsIsbn(args)));
    }

    public Boolean returnBookParams(Map<String, String> args) {
        return hasOneParam(args) && (containsIsbn(args) || containsTitle(args));
    }

    public Boolean isLongEnough(String arg) {
        return argumentShorterThanTwoSigns(arg);
    }

    private boolean argumentShorterThanTwoSigns(String arg) {
        return arg.length() < 2;
    }

    private boolean hasOneParam(Map<String, String> args) {
        return args.size() == 1;
    }

    private boolean containsIsbn(Map<String, String> args) {
        return args.containsKey("-I");
    }

    private boolean containsAuthor(Map<String, String> args) {
        return args.containsKey("-A");
    }

    private boolean containsTitle(Map<String, String> args) {
        return args.containsKey("-T");
    }

    private boolean containsReader(Map<String, String> args) {
        return args.containsKey("-W");
    }
}
