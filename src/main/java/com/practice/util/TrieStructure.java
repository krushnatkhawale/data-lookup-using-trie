package com.practice.util;

import com.practice.model.Trie;

import java.util.stream.Stream;

public class TrieStructure {
    public static Trie parseFile(String filepath) {
        Stream<String> contents = FileUtils.read(filepath);
        Trie trie = new Trie();
        contents.forEach(trie::insert);
        return trie;
    }
}