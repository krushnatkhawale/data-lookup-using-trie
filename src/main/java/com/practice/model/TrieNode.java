package com.practice.model;

import java.util.*;

public class TrieNode {
    final private char content;
    final private Set<String> references = new HashSet<>();
    final private List<TrieNode> childNodes = new ArrayList<>();

    public TrieNode(char ch, String rowNumber) {
        content = ch;
        references.add(rowNumber);
    }

    public Optional<TrieNode> getChild(char content) {
        return childNodes.stream()
                .filter(child -> child.content == content)
                .findFirst();
    }

    public TrieNode addChild(TrieNode trieNode) {
        childNodes.add(trieNode);
        return trieNode;
    }

    public Set<String> getReferences() {
        return references;
    }

    public TrieNode addRowNumber(String rowNumber, TrieNode existingNode) {
        existingNode.references.add(rowNumber);
        return existingNode;
    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrieNode trieNode = (TrieNode) o;
        return content == trieNode.content;
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }*/
}