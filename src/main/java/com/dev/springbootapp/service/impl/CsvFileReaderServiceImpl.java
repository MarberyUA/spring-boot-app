package com.dev.springbootapp.service.impl;

import com.dev.springbootapp.exception.FileNotFoundException;
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
    public List<List<String>> read(String filePath) {
        List<List<String>> list = new ArrayList<>();
        try (BufferedReader csvReader = new BufferedReader(new FileReader(filePath));) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                list.add(List.of(row.split(",")));
            }
        } catch (IOException e) {
            throw new FileNotFoundException("File not found,"
                    + " please check the pass to the file!", e);
        }
        return list;
    }

    @Override
    public List<List<String>> read(String filePath, int amount) {
        List<List<String>> list = new ArrayList<>();
        try (BufferedReader csvReader = new BufferedReader(new FileReader(filePath));) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                if (list.size() == amount) {
                    break;
                }
                list.add(List.of(row.split(",")));
            }
        } catch (IOException e) {
            throw new FileNotFoundException("File not found,"
                    + " please check the pass to the file!", e);
        }
        return list;
    }

    @Override
    public List<List<String>> read(String filePath, int startsFrom, int amount) {
        List<List<String>> list = new ArrayList<>();
        try (BufferedReader csvReader = new BufferedReader(new FileReader(filePath));) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                if (list.size() >= amount) {
                    break;
                }
                startsFrom--;
                if (startsFrom < 0) {
                    list.add(List.of(row.split(",")));
                }
            }
        } catch (IOException e) {
            throw new FileNotFoundException("File not found,"
                    + " please check the pass to the file!", e);
        }
        return list;
    }
}
