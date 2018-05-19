package com.jsuszynski.library;

public class IsbnValidator {

    private static final int ISBN_LENGTH_OLD = 10;
    private static final int ISBN_LENGTH_NEW = 13;

    public Boolean isValid(String isbn) {
        if (hasOnlyNumbers(isbn) && isLongEnouth(isbn))
            return true;
        else
            throw new RuntimeException("ISBN musi składać się z 10 lub 13 cyfr");
    }

    private Boolean hasOnlyNumbers(String isbn) {
        return isbn.matches("\\d+");
    }

    private Boolean isLongEnouth(String isbn) {
        return isbn.length() == ISBN_LENGTH_NEW || isbn.length() == ISBN_LENGTH_OLD;
    }
}
