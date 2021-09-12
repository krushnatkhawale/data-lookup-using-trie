package com.practice;

import com.practice.util.TrieStructure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        TrieStructure.readData("testFile.txt");
    }
}