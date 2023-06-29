package com.epam.rd.autotasks;


import java.util.*;

public class Words {
    public String countWords(List<String> lines) {
        HashMap<String, Integer> wordCountMap = new HashMap<>();

        for (String line : lines) {
            String[] words = line.split("[ .,‘’(“—/:?!”;*)'\"-]|\\s+]");

            for (String word : words) {
                String cleanedWord = word.toLowerCase();
                if (cleanedWord.length() >= 4) {
                    wordCountMap.put(cleanedWord, wordCountMap.getOrDefault(cleanedWord, 0) + 1);
                }
            }
        }

        Iterator<Map.Entry<String, Integer>> iterator = wordCountMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (entry.getValue() < 10) {
                iterator.remove();
            }
        }

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(wordCountMap.entrySet());
        sortedEntries.sort(Map.Entry.<String, Integer>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()));

        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            result.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
        }

        return result.toString().trim();
    }
}
