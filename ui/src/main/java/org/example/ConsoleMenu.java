package org.example;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleMenu {
    final String dataFilePath;

    private static final Logger logger = LogManager.getLogger(ConsoleMenu.class);
    private static final int END_MENU_ITEM = 4;


    public ConsoleMenu(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    public void run() {
        DealsStorage dealsStorage = new DealsStorage();
        dealsStorage.addFromFile(dataFilePath);

        int selectedMenuItem;
        do {
            printMenu();
            selectedMenuItem = ConsoleInput.inputIntInRange("Выберите пункт", 1, END_MENU_ITEM);
            switch (selectedMenuItem) {
                case 1 -> System.out.println(dealsStorage.getMostEffectiveManagersStringForLastMonth());
                case 2 -> System.out.println(dealsStorage.getCustomersIncomeStatisticString());
                case 3 -> System.out.println(dealsStorage.getMostProfitableMonthsStringForLastYear());
            }
            waitUser();
        } while (selectedMenuItem != END_MENU_ITEM);
    }

    private void printMenu() {
        System.out.println("1: Найти самого эффективного менеджера за последний месяц");
        System.out.println("2: Собрать статистику по доходу от каждого клиента");
        System.out.println("3: Найти самый прибыльный месяц за последний год");
        System.out.println("4: Выход");
    }

    private void waitUser() {
        System.out.println("Введите <ENTER>");
        try {
            System.in.read();
        } catch (IOException e) {
            logger.error("Ошибка ожидания ввода пользователя", e);
        }
    }
}