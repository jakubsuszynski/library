package com.jsuszynski.library.file;

import java.io.File;
import java.io.IOException;

class FileFinder {

    private static final String FILE_ERROR = "Problem z dostępem do pliku";
    private final File file = new File(FilePath.getFilePath());


    public File findFile() {

        try {
            initRepository();
        } catch (IOException e) {
            System.out.println(FILE_ERROR);
        }
        return file;
    }

    private void initRepository() throws IOException {

        file.createNewFile();
    }

}
