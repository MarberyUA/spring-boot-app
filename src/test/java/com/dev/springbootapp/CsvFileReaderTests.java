package com.dev.springbootapp;

import com.dev.springbootapp.service.CsvFileReaderService;
import com.dev.springbootapp.service.impl.CsvFileReaderServiceImpl;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;;


public class CsvFileReaderTests {
    private final CsvFileReaderService csvFileReaderService;

    public CsvFileReaderTests() {
        this.csvFileReaderService = new CsvFileReaderServiceImpl();
    }

    @Test
    public void isThrowAnErrorIfFileNotFoundInMethodRead() {
        Assertions.assertThrows(IOException.class, () ->
                csvFileReaderService.read("dsadsadsa.csv"));
    }

    @Test
    public void isThrowAnErrorIfFileNotFoundInMethodReadSpecificAmount() {
        Assertions.assertThrows(IOException.class, () ->
                csvFileReaderService.read("dsadsadsa.csv",
                        10));
    }

    @Test
    public void isThrowAnErrorIfFileNotFoundInMethodReadSpecificAmountStartsWith() {
        Assertions.assertThrows(IOException.class, () ->
                csvFileReaderService.read("dsadsadsa.csv",
                        10, 10));
    }

    @Test
    public void isListHasCorrectLengthInMethodRead() {
        List<List<String>> row = null;
        int randomNum = getRandomRow(
                "src/test/java/com/dev/springbootapp/filetest/test1.csv");
        try {
            row = csvFileReaderService
                    .read("src/test/java/com/dev/springbootapp/filetest/test1.csv");
        } catch (IOException e) {
            e.getMessage();
        }
        Assertions.assertEquals(10, row.get(randomNum).size());

    }

    @Test
    public void isListHasCorrectLengthInMethodReadWithAmount() {
        List<List<String>> rows = null;
        try {
            rows = csvFileReaderService.read(
                    "src/test/java/com/dev/springbootapp/filetest/test2.csv",
                    10);
        } catch (IOException e) {
            e.getMessage();
        }
        Assertions.assertEquals(10, rows.get(getRandomRow(rows)).size());
    }

    @Test
    public void isListHasCorrectLengthInMethodReadWithSpecialStarting() {
        List<List<String>> rows = null;
        try {
            rows = csvFileReaderService.read(
                    "src/test/java/com/dev/springbootapp/filetest/test3.csv",
                    10, 10);
        } catch (IOException e) {
            e.getMessage();
        }
        Assertions.assertEquals(10, rows.get(getRandomRow(rows)).size());
    }

    private int getRandomRow(List<?> list) {
        return (int) (Math.random()
                * (list.size()));
    }

    private int getRandomRow(String filePath) {
        try {
            return  (int) (Math.random() * (csvFileReaderService
                    .read(filePath)
                    .size()));
        } catch (IOException e) {
            e.getMessage();
        }
        return 0;
    }
}
