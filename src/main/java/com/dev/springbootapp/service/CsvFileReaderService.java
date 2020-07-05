package com.dev.springbootapp.service;

import java.io.IOException;
import java.util.List;

public interface CsvFileReaderService {
    List<List<String>> read(String filePath) throws IOException;

    List<List<String>> read(String filePath, int amount) throws IOException;

    List<List<String>> read(String filePath, int startsFrom, int amount) throws IOException;

}
