package net.miracle.community;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleDictionaryController {

    private final Scanner scanner;
    private final Map<String, Map<String, String>> dictionary;

    private Map<String, String> translation;

    public ConsoleDictionaryController() {
        this.scanner = new Scanner(System.in);
        dictionary = new HashMap<>();
        // translation = new HashMap<>();
    }
    // заполняем словарь значениями
    public void initDictionary() {
        // car
        translation = new HashMap<>();
        translation.put("en", "car");
        translation.put("fr", "auto");
        translation.put("de", "wagen");
        dictionary.put("машина", translation);
        // house
        translation = new HashMap<>();
        translation.put("en", "house");
        translation.put("fr", "loger");
        translation.put("de", "haus");
        dictionary.put("дом", translation);
        // planet
        translation = new HashMap<>();
        translation.put("en", "planet");
        translation.put("fr", "planète");
        translation.put("de", "planet");
        dictionary.put("планета", translation);
    }

    // добавляет слово в словарь
    public Boolean addWord(String word) {
        if (dictionary.containsKey(word)) { // если слово существует
            System.out.printf("слово %s уже существует\n", word);
            return false;
        } else { // если слово не существует
            dictionary.put(word, new HashMap<>());
            return true;
        }
    }

    // изменяет слово в словарь
    public Boolean changeWord(String oldWord, String newWord) {
        if (dictionary.containsKey(oldWord)) { // если слово существует
             Map<String, String> temporary =  dictionary.get(oldWord);
             dictionary.remove(oldWord);
             dictionary.put(newWord, temporary);
            return true;
        } else { // если слово не существует
            System.out.printf("слово %s нет в словаре\n", oldWord);
            return false;
        }
    }

    // удаляет слово из словаря
    public Boolean deleteWord(String word) {
        if (dictionary.containsKey(word)) { // если слово существует
            dictionary.remove(word);
            return true;
        } else { // если слово не существует
            System.out.printf("слово %s нет в словаре\n", word);
            return false;
        }
    }


    // добавляет перевод слова, при условии, что оно есть в словаре и локаль не занята
    public Boolean addTranslate(String word, String locale, String translate) {
        if (dictionary.containsKey(word)) { // если слово существует
            Map<String, String> item = dictionary.get(word);
            if (item.containsKey(locale)) { // если локаль существует
                System.out.printf("перевод для %s уже существует.\n", locale);
                return false;
            } else { // если локали нет
                item.put(locale, translate); // вставляем перевод для новой локали
                return true;
            }
        } else { // если слово не существует
            System.out.printf("слово %s не найдено в словаре.\n", word);
            return false;
        }
    }
    // изменяет перевод слова при условии, что оно есть в словаре и локаль существует
    public Boolean changeTranslate(String word, String locale, String translate) {
        if (dictionary.containsKey(word)) { // если слово существует
            Map<String, String> item = dictionary.get(word);
            if (item.containsKey(locale)) { // если локаль существует
                item.replace(locale, translate);
                return true;
            } else { // если локали нет
                System.out.printf("локаль %s не найдена.\n", locale);
                // item.put(locale, translate); // вставляем перевод для новой локали
                return false;
            }
        } else { // если слово не существует
            System.out.printf("слово %s не найдено в словаре.\n", word);
            return false;
        }
    }
    // удаляет перевод слова, при условии, что оно есть в словаре и локаль существует
    public Boolean deleteTranslate(String word, String locale) {
        if (dictionary.containsKey(word)) { // если слово существует
            Map<String, String> item = dictionary.get(word);
            if (item.containsKey(locale)) { // если локаль существует
                item.remove(locale);
                return true;
            } else { // если локали нет
                System.out.printf("локаль %s не найдена.\n", locale);
                // item.put(locale, translate); // вставляем перевод для новой локали
                return false;
            }
        } else { // если слово не существует
            System.out.printf("слово %s не найдено в словаре.\n", word);
            return false;
        }
    }


    // добавить слово TODO: метод не гибкий, в дальнейшем удалить.
    public Boolean AddWordConsole() {
        System.out.println("Введите русское слово:");
        String russianWord = scanner.nextLine();
        System.out.println("Введите перевод на англ.:");
        String englishWord = scanner.nextLine();
        System.out.println("Введите перевод на французкий:");
        String frenchWord = scanner.nextLine();
        System.out.println("Введите перевод на немецкий:");
        String germanWord = scanner.nextLine();

        translation = new HashMap<>();

        try {
            translation.put("en", englishWord);
            translation.put("fr", frenchWord);
            translation.put("de", germanWord);
            dictionary.put(russianWord, translation);
        } catch (NullPointerException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
        return true;
    }

    // распечатать одно слово
    public void print(String word) {
        System.out.printf("слово: [%s] = англ. - %s | франц. - %s | нем. - %s",word, dictionary.get(word).get("en"), dictionary.get(word).get("fr"), dictionary.get(word).get("de"));
    }
    // распечатать весь словарь
    public void printAll() {
        for (Map.Entry<String, Map<String, String>> item : dictionary.entrySet()) {
            System.out.println("\nслово: [" + item.getKey() + "]\nперевод:");
            if (!item.getValue().entrySet().isEmpty()) {
                for (Map.Entry<String, String> i : item.getValue().entrySet()) {
                    System.out.printf("\t%s - %s\n", i.getKey(), i.getValue());
                }
            } else {
                System.out.println("\tперевода нет");
            }
        }
    }
}
