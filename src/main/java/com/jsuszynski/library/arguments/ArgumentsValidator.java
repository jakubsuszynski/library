package com.jsuszynski.library.arguments;

import java.util.Map;

public class ArgumentsValidator {

    public Boolean addingBooksParams(Map<Params, String> args) {
        return (containsIsbn(args) && containsTitle(args) && containsAuthor(args));
    }

    public Boolean deletingBooksParams(Map<Params, String> args) {
        return (hasOneParam(args) && (containsTitle(args) || containsIsbn(args)));
    }

    public Boolean findingBooksParams(Map<Params, String> args) {
        return (!containsIsbn(args) && !containsTitle(args) && !containsAuthor(args));
    }

    public Boolean lendingBooksParams(Map<Params, String> args) {
        return (containsReader(args) && (containsTitle(args) || containsIsbn(args)));
    }

    public Boolean returnBookParams(Map<Params, String> args) {
        return hasOneParam(args) && (containsIsbn(args) || containsTitle(args));
    }

    public Boolean isLongEnough(String arg) {
        return argumentShorterThanTwoSigns(arg);
    }

    private boolean argumentShorterThanTwoSigns(String arg) {
        return arg.length() < 2;
    }

    private boolean hasOneParam(Map<Params, String> args) {
        return args.size() == 1;
    }

    private boolean containsIsbn(Map<Params, String> args) {
        return args.containsKey(Params.I);
    }

    private boolean containsAuthor(Map<Params, String> args) {
        return args.containsKey(Params.A);
    }

    private boolean containsTitle(Map<Params, String> args) {
        return args.containsKey(Params.T);
    }

    private boolean containsReader(Map<Params, String> args) {
        return args.containsKey(Params.W);
    }
}
