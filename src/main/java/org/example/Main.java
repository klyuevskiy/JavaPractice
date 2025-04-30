package org.example;

import deals.DealsStorage;
import input.ConsoleInput;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
Дан файл, в котором содержится информация о сделках в следующем формате:
Менеджер;покупатель;сумма сделки;дата обслуживания
1) Необходимо найти самого эффективного менеджера за последний месяц
2) Собрать статистику по доходу от каждого клиента
*/

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private static final int END_MENU_ITEM = 3;

    private static final String DATA_PATH = "src/main/resources/data.txt";

    public static void main(final String[] args) {
        logger.log(Level.INFO, "Начало работы");

        DealsStorage dealsStorage = new DealsStorage();
        dealsStorage.addFromFile(DATA_PATH);

        int selectedMenuItem;
        do {
            printMenu();
            selectedMenuItem = ConsoleInput.inputIntInRange("Выберите пункт", 1, END_MENU_ITEM);
            switch (selectedMenuItem) {
                case 1 -> System.out.println(dealsStorage.getMostEffectiveManagersStringForLastMonth());
                case 2 -> System.out.println(dealsStorage.getCustomersIncomeStatisticString());
            }
            waitUser();
        } while (selectedMenuItem != END_MENU_ITEM);

        logger.log(Level.OFF, "Конец работы");
    }

    public static void printMenu() {
        System.out.println("1: Найти самого эффективного менеджера за последний месяц");
        System.out.println("2: Собрать статистику по доходу от каждого клиента");
        System.out.println("3: Выход");
    }

    public static void waitUser() {
        System.out.println("Введите <ENTER>");
        try {
            System.in.read();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "wait press enter", e);
        }
    }
}