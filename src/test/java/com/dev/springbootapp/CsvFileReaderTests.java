package com.dev.springbootapp;

import com.dev.springbootapp.exception.FileNotFoundException;
import com.dev.springbootapp.service.CsvFileReaderService;
import com.dev.springbootapp.service.impl.CsvFileReaderServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;;


public class CsvFileReaderTests {
    private final String filePath = "src/test/java/com/dev/springbootapp/filetest/";
    private final CsvFileReaderService csvFileReaderService;

    public CsvFileReaderTests() {
        this.csvFileReaderService = new CsvFileReaderServiceImpl();
    }

    @Test
    public void checkFailedReadFromNotExistingFile() {
        Assertions.assertThrows(FileNotFoundException.class, () ->
                csvFileReaderService.read("dsadsadsa.csv"));
    }

    @Test
    public void checkFailedReadWithSpecialAmountFromNotExistingFile() {
        Assertions.assertThrows(FileNotFoundException.class, () ->
                csvFileReaderService.read("dsadsadsa.csv", 10));
    }

    @Test
    public void checkFailedReadWithSpecialStartAndAmountFromNotExistingFile() {
        Assertions.assertThrows(FileNotFoundException.class, () ->
                csvFileReaderService.read("dsadsadsa.csv", 10, 10));
    }

    @Test
    public void checkSuccessReadFromFile() {
        List<List<String>> rows = null;
        rows = csvFileReaderService.read( filePath + "test1.csv");
        Assertions.assertEquals(48, rows.size());
    }

    @Test
    public void checkSuccessReadWithSpecialAmountFromFile() {
        List<List<String>> rows = null;
        rows = csvFileReaderService.read(filePath + "test2.csv", 5);
        Assertions.assertEquals(5, rows.size());
    }

    @Test
    public void checkSuccessReadWithSpecialStartAndAmountFromFile() {
        List<List<String>> rows = null;
        rows = csvFileReaderService.read(filePath + "test3.csv", 10, 10);
        Assertions.assertEquals(10, rows.size());
    }
}
