package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DealsStorage {
    private static final Logger logger = LogManager.getLogger(DealsStorage.class);
    private final List<Deal> deals = new ArrayList<>();

    public List<Deal> getDeals() {
        return deals;
    }

    public void addFromFile(String filePath) throws IOException {
        logger.info("Начало чтения файла");

        try (var bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Deal deal = Deal.parse(line);
                deals.add(deal);
            }
        } catch (FileNotFoundException e) {
            logger.error("Файл не найден", e);
            throw e;
        } catch (IOException e) {
            logger.error("Ошибка чтения файла", e);
            throw e;
        } catch (Exception e) {
            logger.error("Ошибка парсинга сделки. Конец чтения файла");
            throw e;
        }

        logger.info("Конец чтения файла");
    }
}
