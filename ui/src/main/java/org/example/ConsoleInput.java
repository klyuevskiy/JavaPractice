package org.example;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleInput {

    private static final Logger logger = LogManager.getLogger(ConsoleInput.class);

    private ConsoleInput() {

    }

    public static int inputIntInRange(String message, int leftBound, int rightBound) {
        System.out.println(message);

        int number = 0;
        boolean isCorrectInput = false;
        Scanner scanner = new Scanner(System.in);

        while (!isCorrectInput) {
            try {
                System.out.printf("Введите число от %d до %d\n", leftBound, rightBound);
                number = scanner.nextInt();
                if (number >= leftBound && number <= rightBound) {
                    isCorrectInput = true;
                } else {
                    logger.warn(String.format("Число не в диапазоне [%d; %d]", leftBound, rightBound));
                }
            } catch (Exception e) {
                scanner.nextLine();
                logger.error("Ошибка ввода", e);
            }
        }

        return number;
    }
}
