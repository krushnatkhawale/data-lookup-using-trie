package com.practice;

import com.practice.model.Trie;
import com.practice.util.FileUtils;
import com.practice.util.TrieStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataLoadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataLoadService.class);
    public static final String DATA_FILE = "src/main/resources/sales-records.csv";

    private List<String> data;
    private Trie trie;

    @PostConstruct
    public void initialize() {
        loadData();
        parseDataToTrieStructure();
    }

    private void loadData() {
        LOGGER.info("Loading data started");
        data = FileUtils.read(DATA_FILE)
                .collect(Collectors.toList());
        LOGGER.info("Loading data completed");
    }

    private void parseDataToTrieStructure() {
        LOGGER.info("Parsing data to Trie structure started");
        trie = TrieStructure.parseFile(DATA_FILE);
        LOGGER.info("Parsing data to Trie structure completed");
    }

    public List<String> getData() {
        return data;
    }

    public Trie getTrie() {
        return trie;
    }
}