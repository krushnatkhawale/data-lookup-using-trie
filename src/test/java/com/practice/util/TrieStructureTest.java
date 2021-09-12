package com.practice.util;

import com.practice.model.Trie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class TrieStructureTest {

    @Test
    public void test_TrieStructureReadsFileAndReturnsTrieRoot() {

        Trie trie = TrieStructure.readData("src/test/resources/singleLetterCheckInMultipleRecords.csv");

        assertThat(trie).isNotNull();
    }

    @Test
    public void test_ReturnsRecords_For_SingleLetterWordInMultipleRecords() {

        Trie trie = TrieStructure.readData("src/test/resources/singleLetterCheckInMultipleRecords.csv");

        Set<String> records = trie.search("h");

        assertThat(records).isNotNull();
        assertThat(records).isNotEmpty();
    }

    @Test
    public void test_For_MultiLetterWordInMultipleRecords_ReturnsRecords_ReferencingLastLetter() {

        Trie trie = TrieStructure.readData("src/test/resources/multiLetterCheckInMultipleRecords.csv");

        Set<String> records = trie.search("hi");

        assertThat(records).isNotNull();
        assertThat(records).hasSize(2);
        assertThat(records).contains("1", "3");
    }

    @Test
    public void test_searchingMultiLetterWordInSingleRecordFile_Returns_OnlyOneRecordNumber_ReferencingLastLetter() {

        Trie trie = TrieStructure.readData("src/test/resources/singleRecordFileWithMultipleMatchingWords.csv");

        Set<String> records = trie.search("hi");

        assertThat(records).isNotNull();
        assertThat(records).hasSize(1);
        assertThat(records).contains("1");
    }

    @ParameterizedTest
    @CsvSource({"java,7", "javac,1", "javadoc,1", "c,2", "cpp,1", "unknownWord,0"})
    public void test_searchingMultiLetterWordInMultiRecordFile_Returns_MultipleRecordNumbers_ReferencingLastLetter(
            String name, int size
    ) {

        Trie trie = TrieStructure.readData("src/test/resources/multipleRecordsFileWithMultipleMatchingWords.csv");

        Set<String> records = trie.search(name);

        assertThat(records).isNotNull();
        assertThat(records).hasSize(size);
    }
}