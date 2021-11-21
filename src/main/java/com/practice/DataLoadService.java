package com.practice;

import com.practice.model.Trie;
import com.practice.util.FileUtils;
import com.practice.util.TrieStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Lazy
@Service
public class DataLoadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataLoadService.class);
    public static final String DATA_FILE = "src/main/resources/reviews.csv";

    private CompletableFuture<List<String>> dataFuture;
    private CompletableFuture<Trie> trieFuture;

    private List<String> data;
    private Trie trie;

    @PostConstruct
    public void initialize() {
        dataFuture = CompletableFuture.supplyAsync(this::loadData);
        trieFuture = CompletableFuture.supplyAsync(this::parseDataToTrieStructure);
    }

    @Async
    private List<String> loadData() {
        LOGGER.info("Loading data started");
        final List<String> dataFetched = FileUtils.read(DATA_FILE).collect(Collectors.toList());
        LOGGER.info("Loading data completed");
        return dataFetched;
    }

    @Async
    private Trie parseDataToTrieStructure() {
        LOGGER.info("Parsing data to Trie structure started");
        Trie parsedTrie = TrieStructure.parseFile(DATA_FILE);
        LOGGER.info("Parsing data to Trie structure completed");
        return parsedTrie;
    }

    public List<String> getDataFuture() {
        try {
            data = data == null ? dataFuture.get() : data;
            return data;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public Trie getTrieFuture() {
        try {
            trie = trie == null ? trieFuture.get() : trie;
            return trie;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}