package com.practice.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileUtilsTest {

    @Test
    void test_FileRead_ErrorPath() {
        final String nonExistingFileName = "nonExistingFile.txt";
        assertThatThrownBy(() -> FileUtils.read(nonExistingFileName))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Couldn't read file: " + nonExistingFileName);
    }
}