import org.example.Deal;
import org.example.DealsStorage;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class DealsStorageTest {

    private static final Deal deal1 = Deal.parse("Иванов;Петров;1000;2025-01-12");
    private static final Deal deal2 = Deal.parse("Иванов;Ленин;3000;2025-01-12");

    DealsStorage storage = new DealsStorage();

    @Test
    void readFileTest() {
        assertDoesNotThrow(() -> storage.addFromFile("src/test/resources/test-data.txt"));

        assertEquals(2, storage.getDeals().size());
        assertEquals(deal1, storage.getDeals().get(0));
        assertEquals(deal2, storage.getDeals().get(1));
    }

    @Test
    void notFoundFileErrorTest() {
        assertThrows(FileNotFoundException.class, () -> storage.addFromFile("notExistsFile.txt"));
    }

    @Test
    void readErrorTest() {
        assertThrows(Exception.class, () -> storage.addFromFile("src/test/resources/invalid-test-data.txt"));

        assertEquals(1, storage.getDeals().size());
        assertEquals(deal1, storage.getDeals().get(0));
    }
}
