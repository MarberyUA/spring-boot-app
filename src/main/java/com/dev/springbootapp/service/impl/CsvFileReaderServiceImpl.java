package com.dev.springbootapp.service.impl;

import com.dev.springbootapp.service.CsvFileReaderService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CsvFileReaderServiceImpl implements CsvFileReaderService {

    @Override
    public List<List<String>> read(String filePath) throws IOException {
        List<List<String>> list = new ArrayList<>();
        try (BufferedReader csvReader = new BufferedReader(new FileReader(filePath));) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                if (row.split(",").length > 10) {
                    list.add(checkIfRowLengthMoreThenTen(row));
                    continue;
                }
                list.add(List.of(row.split(",")));
            }
        } catch (IOException e) {
            throw e;
        }
        return list;
    }

    @Override
    public List<List<String>> read(String filePath, int amount) throws IOException {
        List<List<String>> list = new ArrayList<>();
        try (BufferedReader csvReader = new BufferedReader(new FileReader(filePath));) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                if (list.size() == amount) {
                    break;
                }
                if (row.split(",").length > 10) {
                    list.add(checkIfRowLengthMoreThenTen(row));
                    continue;
                }
                list.add(List.of(row.split(",")));
            }
        } catch (IOException e) {
            throw e;
        }
        return list;
    }

    @Override
    public List<List<String>> read(String filePath, int startsFrom, int amount) throws IOException {
        List<List<String>> list = new ArrayList<>();
        try (BufferedReader csvReader = new BufferedReader(new FileReader(filePath));) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                if (list.size() >= amount) {
                    break;
                }
                startsFrom--;
                if (startsFrom == 0) {
                    if (row.split(",").length > 10) {
                        list.add(checkIfRowLengthMoreThenTen(row));
                        continue;
                    }
                    list.add(List.of(row.split(",")));
                }
            }
        } catch (IOException e) {
            throw e;
        }
        return list;
    }

    private List<String> checkIfRowLengthMoreThenTen(String row) {
        String[] splitRow = row.split(",");
        List<String> listRow = new ArrayList<>();
        StringBuilder correctRow = new StringBuilder();
        for (int i = 0; i < splitRow.length; i++) {
            if (i >= 9) {
                correctRow.append(splitRow[i]);
                continue;
            }
            listRow.add(splitRow[i]);
        }
        listRow.add(correctRow.toString());
        return listRow;
    }
}
