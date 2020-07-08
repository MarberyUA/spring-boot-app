package com.dev.springbootapp.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileParsingService {
    Map<String, Map<String, String>> parseData(String filePath) throws IOException;

    Map<String, Map<String, String>> parseData(List<List<String>> list) throws IOException;
}
