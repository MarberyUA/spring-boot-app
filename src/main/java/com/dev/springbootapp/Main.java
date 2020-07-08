package com.dev.springbootapp;

import com.dev.springbootapp.service.CsvFileReaderService;
import com.dev.springbootapp.service.FileParsingService;
import com.dev.springbootapp.service.impl.CsvFileReaderServiceImpl;
import com.dev.springbootapp.service.impl.FileParsingServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        FileParsingService fileParsingService = new FileParsingServiceImpl();
        CsvFileReaderService csvFileReaderService = new CsvFileReaderServiceImpl();
        Map<String, Map<String, String>> map = fileParsingService.parseData(csvFileReaderService.read("src/test/java/com/dev/springbootapp/filetest/test1.csv"));
        System.out.println(map.get("1").size());
    }
}
