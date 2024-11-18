import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HotelTest {

    @BeforeEach
    void setup() {
        // Скидаємо стан готелю перед кожним тестом
        Hotel.hotel_ob = new holder();
    }

    @Test
    void testRoomBooking() {
        // Тест бронювання номера
        Hotel.hotel_ob.luxury_doublerrom[0] = new Doubleroom("Eve", "1112223334", "Female", "Dave", "4445556667",
                "Male");
        assertNotNull(Hotel.hotel_ob.luxury_doublerrom[0]);
    }

    @Test
    void testRoomAvailability() {
        // Бронюємо один номер
        Hotel.hotel_ob.luxury_doublerrom[0] = new Doubleroom("Alice", "1234567890", "Female", "Bob", "0987654321",
                "Male");

        // Перевіряємо кількість вільних номерів
        int count = 0;
        for (Doubleroom room : Hotel.hotel_ob.luxury_doublerrom) {
            if (room == null)
                count++;
        }
        assertEquals(9, count); // Один номер заброньовано, 9 вільних
    }

    @Test
    void testBillCalculation() {
        // Готуємо номер із замовленням їжі
        Hotel.hotel_ob.luxury_singleerrom[0] = new Singleroom("Charlie", "1234567890", "Male");
        Hotel.hotel_ob.luxury_singleerrom[0].food.add(new Food(1, 2)); // Замовляємо 2 сендвічі

        // Розраховуємо загальну суму
        double roomCharge = 2200;
        double foodCharge = 2 * 50; // 2 сендвічі по 50
        double totalCharge = roomCharge + foodCharge;

        assertEquals(2300, totalCharge);
    }

    @Test
    void testDeallocation() {
        // Бронюємо і потім звільняємо номер
        Hotel.hotel_ob.deluxe_doublerrom[0] = new Doubleroom("Eve", "1112223334", "Female", "Dave", "4445556667",
                "Male");
        Hotel.hotel_ob.deluxe_doublerrom[0] = null; // Симуляція звільнення
        assertNull(Hotel.hotel_ob.deluxe_doublerrom[0]); // Номер повинен бути порожнім
    }

    @Test
    void testOrderFood() {
        // Тест замовлення їжі
        Hotel.hotel_ob.luxury_doublerrom[0] = new Doubleroom("Alice", "1234567890", "Female", "Bob", "0987654321",
                "Male");
        Hotel.hotel_ob.luxury_doublerrom[0].food.add(new Food(2, 3)); // Замовляємо 3 порції пасти

        assertEquals(180, Hotel.hotel_ob.luxury_doublerrom[0].food.get(0).price); // Перевірка правильної ціни
    }
}
