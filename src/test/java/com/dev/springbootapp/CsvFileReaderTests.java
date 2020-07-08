package com.dev.springbootapp;

import com.dev.springbootapp.service.CsvFileReaderService;
import com.dev.springbootapp.service.impl.CsvFileReaderServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;;


public class CsvFileReaderTests {
    private final String filePath = "src/test/java/com/dev/springbootapp/filetest/";
    private final CsvFileReaderService csvFileReaderService;
    private final List<List<String>> emptyList;

    public CsvFileReaderTests() {
        this.csvFileReaderService = new CsvFileReaderServiceImpl();
        this.emptyList = new ArrayList<>();
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
    public void isMethodReadNotReturnEmptyListIfFileIsNotEmpty() {
        List<List<String>> rows = null;
        try {
            rows = csvFileReaderService
                    .read( filePath + "test1.csv");
        } catch (IOException e) {
            e.getMessage();
        }
        Assertions.assertNotEquals(emptyList, rows);
    }

    @Test
    public void isMethodReadWithSpecialAmountNotReturnEmptyListIfFileIsNotEmpty() {
        List<List<String>> rows = null;
        try {
            rows = csvFileReaderService.read(filePath + "test2.csv",
                    5);
        } catch (IOException e) {
            e.getMessage();
        }
        Assertions.assertNotEquals(emptyList, rows);
    }

    @Test
    public void isMethodReadWithSpecialStartingNotReturnEmptyListIfFileIsNotEmpty() {
        List<List<String>> rows = null;
        try {
            rows = csvFileReaderService.read(
                     filePath + "test3.csv",
                    10, 10);
        } catch (IOException e) {
            e.getMessage();
        }
        Assertions.assertNotEquals(emptyList, rows);
    }
}
