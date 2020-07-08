package com.dev.springbootapp.service;

import java.util.List;

public interface CsvFileReaderService {
    List<List<String>> read(String filePath);

    List<List<String>> read(String filePath, int amount);

    List<List<String>> read(String filePath, int startsFrom, int amount);

}
