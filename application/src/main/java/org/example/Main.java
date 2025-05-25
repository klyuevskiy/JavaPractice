package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
Дан файл, в котором содержится информация о сделках в следующем формате:
Менеджер;покупатель;сумма сделки;дата обслуживания
1) Необходимо найти самого эффективного менеджера за последний месяц
2) Собрать статистику по доходу от каждого клиента
3) Найти самый прибыльный месяц за последний год
*/

public class Main {

    private static final String DATA_PATH = "application/src/main/resources/data.txt";

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(final String[] args) {
        logger.info("Начало работы");

        try {
            DealsStorage storage = new DealsStorage();
            storage.addFromFile(DATA_PATH);
            DealsHandler handler = new DealsHandler(storage);
            ConsoleMenu menu = new ConsoleMenu(handler);
            menu.run();
        } catch (Exception e) {
            logger.error("Необработанная ошибка", e);
        }

        logger.info("Конец работы");
    }
}
