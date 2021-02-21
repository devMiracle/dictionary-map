package net.miracle.community;

import java.security.KeyStore;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        ConsoleDictionaryController controller = new ConsoleDictionaryController();
        controller.initDictionary();
        controller.printAll();
        //System.out.println(controller.addTranslate("планета", "ua", "планета(укр)"));
        //System.out.println(controller.changeTranslate("планета", "fr", "planète2"));
        //System.out.println(controller.deleteTranslate("планета", "de"));
        //System.out.println(controller.addWord("стол"));
        System.out.println(controller.changeWord("планета", "планета2"));
        System.out.println(controller.deleteWord("планета2"));
        controller.printAll();


//        controller.AddWord();
//        String text = "ввод";
//        controller.print(text);












//        map.put("car", "машина");



//        map.put(firstInputText, secondInputText);
//
//        for(Map.Entry<String, String> item : map.entrySet()) {
//            System.out.printf("слово - %4s : перевод - %4s\n", item.getKey(), item.getValue());
//        }







    }
}
