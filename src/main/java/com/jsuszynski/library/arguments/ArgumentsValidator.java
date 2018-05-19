package com.jsuszynski.library.arguments;

import java.util.Map;

public class ArgumentsValidator {

    public Boolean addingBooksParams(Map<String, String> args) {
        return !(containsIsbn(args) && containsTitle(args) && containsAuthor(args));
    }

    public Boolean deletingBooksParams(Map<String, String> args) {
        return !(args.size() == 1 && (containsTitle(args) || containsIsbn(args)));
    }

    public Boolean findingBooksParams(Map<String , String> args){
        return (!args.containsKey("-I") && !args.containsKey("-T") && !args.containsKey("-A"));
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
}
