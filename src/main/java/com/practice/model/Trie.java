package com.practice.model;

import java.util.Optional;
import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.function.Predicate.not;
import static java.util.stream.Stream.of;

public class Trie {

    private final TrieNode root;

    public Trie() {
        root = new TrieNode(' ', "-1");
    }

    public void insert(String line) {
        String rowNumber = getRowNumber(line);
        String[] words = extractWords(line);
        for (int i = 0; i < words.length; i++) {
            insert(rowNumber, words[i]);
        }
    }

    private String[] extractWords(String string) {
        return of(string.split(","))
                .flatMap(line -> of(line.split(" ")))
                .map(String::strip)
                .filter(not(String::isEmpty))
                .toArray(String[]::new);
    }

    private String getRowNumber(String line) {
        return line.split(",")[0];
    }

    public void insert(String rowNumber, String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            Optional<TrieNode> child = current.getChild(ch);
            if (child.isPresent()) {
                TrieNode existingNode = child.get();
                current = existingNode.addRowNumber(rowNumber, existingNode);
            } else {
                TrieNode newTrieNode = current.addChild(new TrieNode(ch, rowNumber));
                current = newTrieNode;
            }
        }
    }

    public Set<String> search(String hello) {
        TrieNode nodeToSearchIn = root;
        for (int i = 0; i < hello.length(); i++) {
            char charToSearch = hello.charAt(i);
            Optional<TrieNode> child = nodeToSearchIn.getChild(charToSearch);
            if (child.isEmpty())
                return emptySet();
            if (i + 1 == hello.length())
                return child.get().getReferences();
            else
                nodeToSearchIn = child.get();
        }
        return emptySet();
    }
}