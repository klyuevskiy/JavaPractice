import org.example.Deal;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DealTest {

    private static final String managerName = "Ilya";
    private static final String customerName = "Mark";
    private static final int amount = 10;
    private static final LocalDate date = LocalDate.parse("2022-10-10");

    private static final Deal deal = new Deal(
            managerName,
            customerName,
            amount,
            date);

    private static final String csvLikeDeal = String.format(
            "%s;%s;%d;%s",
            managerName,
            customerName,
            amount,
            date);

    private static final Deal dealEqual = new Deal(
            managerName,
            customerName,
            amount,
            date);

    private static final int amountAnother = 20;

    private static final Deal dealNotEqual = new Deal(
            managerName,
            customerName,
            amountAnother,
            date);

    @Test
    void getManagerNameTest() {
        assertEquals(managerName, deal.getManagerName());
    }

    @Test
    void getCustomerNameTest() {
        assertEquals(customerName, deal.getCustomerName());
    }

    @Test
    void getAmountNameTest() {
        assertEquals(amount, deal.getAmount());
    }

    @Test
    void getDateTest() {
        assertEquals(date, deal.getDate());
    }

    @Test
    void toStingTest() {
        String expected = String.format(
                "Менеджер: %s. Покупатель: %s. Сумма сделки: %d. Дата: %s",
                managerName,
                customerName,
                amount,
                date);
        assertEquals(
                expected,
                deal.toString());
    }

    @Test
    void equalsTest() {
        assertEquals(deal, dealEqual);
    }

    @Test
    void notEqualsTest() {
        assertNotEquals(deal, dealNotEqual);
    }

    @Test
    void equalsThisTest() {
        assertEquals(deal, deal);
    }

    @Test
    void equalsAnotherTypeTest() {
        assertNotEquals(deal, "another");
    }

    @Test
    void equalsNullTest() {
        assertNotEquals(deal, null);
    }

    @Test
    void equalsHashCodeTest() {
        assertEquals(deal.hashCode(), dealEqual.hashCode());
    }

    @Test
    void notEqualsHashCodeTest() {
        assertNotEquals(deal.hashCode(), dealNotEqual.hashCode());
    }

    @Test
    void parseTest() {
        Deal deal = Deal.parse(csvLikeDeal);
        assertEquals(managerName, deal.getManagerName());
        assertEquals(customerName, deal.getCustomerName());
        assertEquals(amount, deal.getAmount());
        assertEquals(date, deal.getDate());
    }

    @Test
    void parseNotEnoughDataErrorTest() {
        String csvLikeString = String.format("%s;%s", managerName, customerName);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Deal.parse(csvLikeString));
    }

    @Test
    void parseAmountErrorTest() {
        String csvLikeString = String.format(
          "%s;%s;%s;%s",
          managerName,
          customerName,
          "invalidAmount",
          date
        );

        assertThrows(NumberFormatException.class, () -> Deal.parse(csvLikeString));
    }

    @Test
    void parseDateErrorTest() {
        String csvLikeString = String.format(
                "%s;%s;%d;%s",
                managerName,
                customerName,
                amount,
                "invalidDate"
        );

        assertThrows(DateTimeParseException.class, () -> Deal.parse(csvLikeString));
    }
}
