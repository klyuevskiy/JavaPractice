package org.example;

import java.io.IOException;
import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleMenu {
    private static final Logger logger = LogManager.getLogger(ConsoleMenu.class);
    private static final int END_MENU_ITEM = 4;

    private final DealsHandler dealsHandler;

    ConsoleMenu(DealsHandler dealsHandler) {
        this.dealsHandler = dealsHandler;
    }

    public void run() {
        int selectedMenuItem;
        do {
            printMenu();
            selectedMenuItem = ConsoleInput.inputIntInRange("Выберите пункт", 1, END_MENU_ITEM);
            switch (selectedMenuItem) {
                case 1 -> System.out.println(dealsHandler.getMostEffectiveManagersStringForLastMonth(LocalDate.now()));
                case 2 -> System.out.println(dealsHandler.getCustomersIncomeStatisticString());
                case 3 -> System.out.println(dealsHandler.getMostProfitableMonthsStringForLastYear(LocalDate.now()));
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
