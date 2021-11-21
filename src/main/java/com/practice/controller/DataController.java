package com.practice.controller;

import com.practice.DataLoadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class DataController {

    private final DataLoadService dataLoadService;

    public DataController(DataLoadService dataLoadService) {
        this.dataLoadService = dataLoadService;
    }

    @GetMapping("/data")
    public List<String> getData() {
        return dataLoadService.getDataFuture();
    }

    @GetMapping("/search")
    public Set<String> getTrieSearch(@RequestParam String filterWord) {
        return dataLoadService.getTrieFuture().search(filterWord);
    }
}