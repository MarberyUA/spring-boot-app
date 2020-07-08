package com.dev.springbootapp.service.impl;

import com.dev.springbootapp.service.CsvFileReaderService;
import com.dev.springbootapp.service.FileParsingService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileParsingServiceImpl implements FileParsingService {
    @Autowired
    private CsvFileReaderService csvFileReaderService;

    @Override
    public Map<String, Map<String, String>> parseData(String filePath) throws IOException {
        List<List<String>> rows = csvFileReaderService.read(filePath);
        Map<String, Map<String, String>> parsedData = new TreeMap<>();
        convertData(parsedData, rows);
        return parsedData;
    }

    @Override
    public Map<String, Map<String, String>> parseData(List<List<String>> list) throws IOException {
        Map<String, Map<String, String>> parsedData = new TreeMap<>();
        convertData(parsedData, list);
        return parsedData;
    }

    private void convertData(Map<String, Map<String, String>> map, List<List<String>> list) {
        for (List<String> underList : list) {
            if (underList.size() > 10) {
                underList = convertListToCorrectView(underList);
            }
            Map<String, String> rowMap = new HashMap<>();
            for (int i = 1; i < 10; i++) {
                rowMap.put(DataColumns.values()[i].name(), underList.get(i));
            }
            map.put(underList.get(0), rowMap);
        }
    }

    private List<String> convertListToCorrectView(List<String> row) {
        StringBuilder correctRow = new StringBuilder();
        for (int i = 9; i < row.size(); i++) {
                correctRow.append(row.get(i));
        }
        List<String> resultList = new ArrayList<>();
        resultList.addAll(row.subList(0, 9));
        resultList.add(correctRow.toString());
        return resultList;
    }

    private enum DataColumns {
        Id,ProductId,UserId,ProfileName,HelpfulnessNumerator,
        HelpfulnessDenominator,Score,Time,Summary,Text
    }
}
