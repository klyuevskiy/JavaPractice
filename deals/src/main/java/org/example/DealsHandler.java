package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class DealsHandler {
    private final DealsStorage storage;

    public DealsHandler(DealsStorage storage) {
        this.storage = storage;
    }

    private List<Deal> getDealsAfterDate(LocalDate date) {
        return storage.getDeals().stream()
                .filter(deal -> !deal.getDate().isBefore(date))
                .collect(Collectors.toList());
    }

    private String formatMapEntry(Map.Entry<String, Integer> entry) {
        return entry.getKey() + " : " + entry.getValue() + "\n";
    }

    private String getMaxIncomeValueString(Map<String, Integer> incomesStatistic) {
        int maxIncome = incomesStatistic.values().stream()
                .max(Integer::compare)
                .orElse(-1);

        return incomesStatistic.entrySet().stream()
                .filter(entry -> entry.getValue() == maxIncome)
                .map(this::formatMapEntry)
                .collect(Collectors.joining());
    }

    public String getCustomersIncomeStatisticString() {
        if (storage.getDeals().isEmpty()) {
            return "Пока ещё не было совершено ни одной сделки";
        }

        Map<String, Integer> customersIncomeStatistic = storage.getDeals().stream()
                .collect(Collectors.toMap(
                        Deal::getCustomerName,
                        Deal::getAmount,
                        Integer::sum,
                        HashMap::new
                ));

        return customersIncomeStatistic.entrySet().stream()
                .map(this::formatMapEntry)
                .collect(Collectors.joining());
    }

    public String getMostEffectiveManagersStringForLastMonth(LocalDate dateNow) {
        List<Deal> lastMonthDeals = getDealsAfterDate(dateNow.minusMonths(1));

        if (lastMonthDeals.isEmpty()) {
            return "За прошлый месяц не было совершено ни одной сделки";
        }

        Map<String, Integer> managersIncomes = lastMonthDeals.stream()
                .collect(Collectors.toMap(
                        Deal::getManagerName,
                        Deal::getAmount,
                        Integer::sum,
                        HashMap::new
                ));

        return getMaxIncomeValueString(managersIncomes);
    }

    public String getMostProfitableMonthsStringForLastYear(LocalDate dateNow) {
        List<Deal> lastYearDeals = getDealsAfterDate(dateNow.minusYears(1));

        if (lastYearDeals.isEmpty()) {
            return "За прошлый год не было совершено ни одной сделки";
        }

        Map<String, Integer> monthsIncomes = lastYearDeals.stream()
                .collect(Collectors.toMap(
                        deal -> deal.getDate().getMonth().toString(),
                        Deal::getAmount,
                        Integer::sum,
                        HashMap::new
                ));

        return getMaxIncomeValueString(monthsIncomes);
    }
}