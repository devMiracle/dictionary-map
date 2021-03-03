package net.miracle.community;

import net.miracle.community.model.ValueDictionaryModel;

import java.util.*;

public class ConsoleDictionaryController {

    private final Scanner scanner;
    private final Map<String, ValueDictionaryModel> dictionary;

    public ConsoleDictionaryController() {
        this.scanner = new Scanner(System.in);
        dictionary = new HashMap<>();
        // translation = new HashMap<>();
    }
    // заполняем словарь значениями
    public void initDictionary() {
        // car
//        translation = new HashMap<>();
//        translation.put("en", "car");
//        translation.put("fr", "auto");
//        translation.put("de", "wagen");
//        dictionary.put("машина", translation);
        Map<String, String> translation = new HashMap<>();
        translation.put("en", "car");
        translation.put("fr", "auto");
        translation.put("de", "wagen");
        ValueDictionaryModel item = new ValueDictionaryModel();
        item.setPopularityIndex(0);
        item.setMap(translation);
        dictionary.put("машина", item);
        // house
//        translation = new HashMap<>();
//        translation.put("en", "house");
//        translation.put("fr", "loger");
//        translation.put("de", "haus");
//        dictionary.put("дом", translation);
        translation = new HashMap<>();
        translation.put("en", "house");
        translation.put("fr", "loger");
        translation.put("de", "haus");
        item = new ValueDictionaryModel();
        item.setPopularityIndex(0);
        item.setMap(translation);
        dictionary.put("дом", item);
        // planet
//        translation = new HashMap<>();
//        translation.put("en", "planet");
//        translation.put("fr", "planète");
//        translation.put("de", "planet");
//        dictionary.put("планета", translation);
        translation = new HashMap<>();
        translation.put("en", "planet");
        translation.put("fr", "planète");
        translation.put("de", "planet");
        item = new ValueDictionaryModel();
        item.setPopularityIndex(0);
        item.setMap(translation);
        dictionary.put("планета", item);
    }

    // добавляет слово в словарь
    public Boolean addWord(String word) {
        if (dictionary.containsKey(word)) { // если слово существует
            System.out.printf("слово %s уже существует\n", word);
            return false;
        } else { // если слово не существует
            dictionary.put(word, new ValueDictionaryModel());
            return true;
        }
    }

    // изменяет слово в словаре
    public Boolean changeWord(String oldWord, String newWord) {
        if (dictionary.containsKey(oldWord)) { // если слово существует
             ValueDictionaryModel temporary =  dictionary.get(oldWord);
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
            ValueDictionaryModel item = dictionary.get(word);
            if (item.getMap().containsKey(locale)) { // если локаль существует
                System.out.printf("перевод для %s уже существует.\n", locale);
                return false;
            } else { // если локали нет
                item.getMap().put(locale, translate); // вставляем перевод для новой локали
                item.setPopularityIndex(item.getPopularityIndex() + 1);
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
            ValueDictionaryModel item = dictionary.get(word);
            if (item.getMap().containsKey(locale)) { // если локаль существует
                item.getMap().replace(locale, translate);
                item.setPopularityIndex(item.getPopularityIndex() + 1);
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
            ValueDictionaryModel item = dictionary.get(word);
            if (item.getMap().containsKey(locale)) { // если локаль существует
                item.getMap().remove(locale);
                item.setPopularityIndex(item.getPopularityIndex() + 1);
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

        ValueDictionaryModel translation = new ValueDictionaryModel();

        try {
            translation.getMap().put("en", englishWord);
            translation.getMap().put("fr", frenchWord);
            translation.getMap().put("de", germanWord);
            dictionary.put(russianWord, translation);
        } catch (NullPointerException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
        return true;
    }

    // выводит топ1
    public void printTopOne() {
        Map.Entry<String, ValueDictionaryModel> word = dictionary.entrySet().stream().max((o1, o2) -> o1.getValue().getPopularityIndex().compareTo(o2.getValue().getPopularityIndex())).get();
        if (dictionary.keySet().contains(word.getKey())) {
            System.out.print("top1 ");
            System.out.printf("слово: [%s] = англ. - %s | франц. - %s | нем. - %s\n",
                    word.getKey(),
                    (word.getValue().getMap().containsKey("en") ? word.getValue().getMap().get("en") : "нет перевода"),
                    (word.getValue().getMap().containsKey("fr") ? word.getValue().getMap().get("fr") : "нет перевода"),
                    (word.getValue().getMap().containsKey("de") ? word.getValue().getMap().get("de") : "нет перевода"));
        } else {
            System.out.println("error");
        }
    }


    // выводит топ1 с конца
    public void printTopLast() {
        Map.Entry<String, ValueDictionaryModel> word = dictionary.entrySet().stream().min((o1, o2) -> o1.getValue().getPopularityIndex().compareTo(o2.getValue().getPopularityIndex())).get();
        if (dictionary.keySet().contains(word.getKey())) {
            System.out.print("top last ");
            System.out.printf("слово: [%s] = англ. - %s | франц. - %s | нем. - %s\n",
                    word.getKey(),
                    (word.getValue().getMap().containsKey("en") ? word.getValue().getMap().get("en") : "нет перевода"),
                    (word.getValue().getMap().containsKey("fr") ? word.getValue().getMap().get("fr") : "нет перевода"),
                    (word.getValue().getMap().containsKey("de") ? word.getValue().getMap().get("de") : "нет перевода"));
        } else {
            System.out.println("error");
        }
    }



    // распечатать одно слово
    public void print(String word) {
        if (dictionary.containsKey(word)) {
            System.out.printf("слово: [%s] = англ. - %s | франц. - %s | нем. - %s\n", word, dictionary.get(word).getMap().get("en"), dictionary.get(word).getMap().get("fr"), dictionary.get(word).getMap().get("de"));
            dictionary.get(word).setPopularityIndex(dictionary.get(word).getPopularityIndex() + 1);
        } else {
            System.out.printf("слово %s не найдено", word);
        }
    }
    // распечатать весь словарь
    public void printAll() {
        for (Map.Entry<String, ValueDictionaryModel> item : dictionary.entrySet()) {
            System.out.println("\nслово: " + item.getKey() + "\nперевод:");
            item.getValue().setPopularityIndex(item.getValue().getPopularityIndex() + 1);
            if (!item.getValue().getMap().entrySet().isEmpty()) {
                for (Map.Entry<String, String> i : item.getValue().getMap().entrySet()) {
                    System.out.printf("\t%s - %s\n", i.getKey(), i.getValue());
                }
            } else {
                System.out.println("\tперевода нет");
            }
        }
    }
}
