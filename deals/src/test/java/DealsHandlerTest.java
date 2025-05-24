import static org.mockito.Mockito.*;

import org.example.Deal;
import org.example.DealsStorage;
import org.example.DealsHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DealsHandlerTest {
    private static final List<Deal> deals = Arrays.asList(
            Deal.parse("Maxim;Mark;10;2022-10-10"),
            Deal.parse("Ilya;Igor;20;2022-12-11"),
            Deal.parse("Ilya;Igor;40;2022-09-11")
    );

    private DealsStorage mockStorage;
    private DealsStorage mockEmptyStorage;

    @BeforeEach
    void setUpStorage() {
        mockStorage = mock(DealsStorage.class);
        mockEmptyStorage = mock(DealsStorage.class);

        when(mockStorage.getDeals()).thenReturn(deals);
        when(mockEmptyStorage.getDeals()).thenReturn(new ArrayList());
    }

    @Test
    void getMostEffectiveManagersStringForLastMonthTest() {
        DealsHandler dealsHandler = new DealsHandler(mockStorage);
        LocalDate dateNow = LocalDate.parse("2022-11-11");
        String res = dealsHandler.getMostEffectiveManagersStringForLastMonth(dateNow);
        assertEquals("Ilya : 20\n", res);
    }

    @Test
    void getMostEffectiveManagersStringForLastMonthForEmptyTest() {
        DealsHandler dealsHandler = new DealsHandler(mockEmptyStorage);
        LocalDate dateNow = LocalDate.parse("2022-11-11");
        String res = dealsHandler.getMostEffectiveManagersStringForLastMonth(dateNow);
        assertEquals("За прошлый месяц не было совершено ни одной сделки", res);
    }

    @Test
    void getCustomersIncomeStatisticStringTest()
    {
        DealsHandler dealsHandler = new DealsHandler(mockStorage);
        String res = dealsHandler.getCustomersIncomeStatisticString();
        assertEquals("Igor : 60\nMark : 10\n", res);
    }

    @Test
    void getCustomersIncomeStatisticStringEmptyTest() {
        DealsHandler dealsHandler = new DealsHandler(mockEmptyStorage);
        String res = dealsHandler.getCustomersIncomeStatisticString();
        assertEquals("Пока ещё не было совершено ни одной сделки", res);
    }

    @Test
    void getMostProfitableMonthsStringForLastYearTest() {
        DealsHandler dealsHandler = new DealsHandler(mockStorage);
        LocalDate dateNow = LocalDate.parse("2023-11-11");
        String res = dealsHandler.getMostProfitableMonthsStringForLastYear(dateNow);
        assertEquals("DECEMBER : 20\n", res);
    }

    @Test
    void getMostProfitableMonthsStringForLastYearEmptyTest() {
        DealsHandler dealsHandler = new DealsHandler(mockEmptyStorage);
        LocalDate dateNow = LocalDate.parse("2023-11-11");
        String res = dealsHandler.getMostProfitableMonthsStringForLastYear(dateNow);
        assertEquals("За прошлый год не было совершено ни одной сделки", res);
    }
}
