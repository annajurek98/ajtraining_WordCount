package com.ajtraining;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class WordCount {

    public static void main(String[] args) throws IOException, URISyntaxException {
        var resource = WordCount.class.getResource("/pantadeusz.txt");
        var fileName = Paths.get(resource.toURI());
        var actualFile = Files.readString(fileName).toLowerCase();
        var replace = actualFile.replaceAll("[^a-zA-Z¹êœæ³ó¿Ÿñ¥ÊŒÆ£Ó¯Ñ\\s\\r\\n]", "");
        var s = replace.replaceAll("[\\t\\n\\r]+", " ").split(" ");
        var words = Arrays.stream(s).filter((s1) -> !"".equals(s1)).collect(Collectors.toList());

        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            if (wordCount.containsKey(word)) {
                int quantity = wordCount.get(word);
                quantity++;
                wordCount.put(word, quantity);
            } else {
                wordCount.put(word, 1);
            }
        }
        int theLargestQuantity = 0;
        String searchedWord = null;
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            if (entry.getValue() > theLargestQuantity) {
                theLargestQuantity = entry.getValue();
                searchedWord = entry.getKey();
            }
        }
        System.out.println("searchedWord = " + searchedWord);
        System.out.println("theLargestQuantity = " + theLargestQuantity);
        System.out.println("Size of unique words = " + wordCount.keySet().size());
    }
}
