package com.practice.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class FileUtils {
    public static Stream<String> read(String filepath) {
        try {
            return Files.lines(Paths.get(filepath))
                    .map(String::strip)
                    .filter(not(String::isEmpty))
                    .map(String::toLowerCase)
                    .flatMap(String::lines);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read file: " + filepath, e);
        }
    }
}
