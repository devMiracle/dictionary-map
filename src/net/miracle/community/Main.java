package net.miracle.community;


public class Main {

    public static void main(String[] args) {
        ConsoleDictionaryController controller = new ConsoleDictionaryController();
        controller.initDictionary();
        controller.printAll();

        System.out.println(controller.addTranslate("планета", "ua", "планета(укр)"));
        System.out.println(controller.changeTranslate("планета", "fr", "planète2"));
        System.out.println(controller.deleteTranslate("планета", "de"));
        System.out.println(controller.addWord("стол"));
        System.out.println(controller.changeWord("планета", "планета2"));
        System.out.println(controller.deleteWord("планета2"));

        controller.printAll();
        controller.print("машина");
        controller.print("дом");
        controller.print("дом");
        controller.print("стол");
        controller.print("стол");

        controller.printTopOne();
        controller.printTopLast();

    }
}
