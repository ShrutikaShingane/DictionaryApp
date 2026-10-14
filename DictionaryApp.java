package dictionary;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class DictionaryApp {

    private static final String FILE_NAME = "dictionary.txt";

    private static final Map<String, String> dictionary =
            new HashMap<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        loadDictionary();

        while (true) {

            System.out.println("\n===== DICTIONARY APP =====");
            System.out.println("1. Add Entry");
            System.out.println("2. Search Word");
            System.out.println("3. Delete Entry");
            System.out.println("4. List All Words");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");

            String choice = sc.nextLine().trim();

            switch (choice) {

                case "1":
                    addEntry(sc);
                    break;

                case "2":
                    searchWord(sc);
                    break;

                case "3":
                    deleteEntry(sc);
                    break;

                case "4":
                    listAllWords();
                    break;

                case "5":
                    System.out.println(
                            "Dictionary saved. Goodbye!");

                    sc.close();
                    return;

                default:
                    System.out.println(
                            "Invalid choice. Please try again.");
            }
        }
    }

    private static void addEntry(Scanner sc) {

        System.out.print("Enter word: ");
        String word = sc.nextLine().trim();

        if (word.isEmpty()) {
            System.out.println("Word cannot be empty.");
            return;
        }

        System.out.print("Enter definition: ");
        String definition = sc.nextLine().trim();

        if (definition.isEmpty()) {
            System.out.println("Definition cannot be empty.");
            return;
        }

        String key = word.toLowerCase();

        dictionary.put(key, definition);

        saveDictionary();

        System.out.println("Word added successfully!");
    }

    private static void searchWord(Scanner sc) {

        System.out.print("Enter word to search: ");

        String word = sc.nextLine().trim().toLowerCase();

        if (dictionary.containsKey(word)) {

            System.out.println(
                    "Definition: " + dictionary.get(word));

        } else {

            System.out.println("Word not found.");
        }
    }

    private static void deleteEntry(Scanner sc) {

        System.out.print("Enter word to delete: ");

        String word = sc.nextLine().trim().toLowerCase();

        if (dictionary.containsKey(word)) {

            dictionary.remove(word);

            saveDictionary();

            System.out.println("Word deleted successfully!");

        } else {

            System.out.println("Word not found.");
        }
    }

    private static void listAllWords() {

        if (dictionary.isEmpty()) {

            System.out.println("Dictionary is empty.");
            return;
        }

        System.out.println("\n--- Dictionary Words ---");

        Map<String, String> sortedDictionary =
                new TreeMap<>(dictionary);

        for (Map.Entry<String, String> entry
                : sortedDictionary.entrySet()) {

            System.out.println(
                    entry.getKey() + " : " + entry.getValue());
        }
    }

    private static void saveDictionary() {

        Path path = Paths.get(FILE_NAME);

        try (BufferedWriter writer = Files.newBufferedWriter(
                path, StandardCharsets.UTF_8)) {

            for (Map.Entry<String, String> entry
                    : dictionary.entrySet()) {

                String word = encode(entry.getKey());
                String definition = encode(entry.getValue());

                writer.write(word + "\t" + definition);
                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Error saving dictionary: " + e.getMessage());
        }
    }

    private static void loadDictionary() {

        Path path = Paths.get(FILE_NAME);

        if (!Files.exists(path)) {
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(
                path, StandardCharsets.UTF_8)) {

            String line;

            while ((line = reader.readLine()) != null) {

                int separator = line.indexOf('\t');

                if (separator < 0) {
                    continue;
                }

                String word = decode(
                        line.substring(0, separator));

                String definition = decode(
                        line.substring(separator + 1));

                if (!word.isEmpty() && !definition.isEmpty()) {
                    dictionary.put(word, definition);
                }
            }

            System.out.println(
                    "Dictionary loaded successfully!");

        } catch (IOException e) {

            System.out.println(
                    "Error loading dictionary: " + e.getMessage());
        }
    }

    private static String encode(String value) {
        return value.replace("\\", "\\\\")
                    .replace("\t", "\\t")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r");
    }

    private static String decode(String value) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < value.length(); i++) {

            char ch = value.charAt(i);

            if (ch == '\\' && i + 1 < value.length()) {

                char next = value.charAt(++i);

                switch (next) {

                    case 't':
                        result.append('\t');
                        break;

                    case 'n':
                        result.append('\n');
                        break;

                    case 'r':
                        result.append('\r');
                        break;

                    case '\\':
                        result.append('\\');
                        break;

                    default:
                        result.append('\\').append(next);
                }

            } else {

                result.append(ch);
            }
        }

        return result.toString();
    }
}