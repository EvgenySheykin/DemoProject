import org.example.DeliveryCalculator;
import org.example.DeliveryLoad;
import org.example.DeliveryRequest;
import org.example.DeliveryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryServiceTests {

    @Test
    @Tag("positive")
    @DisplayName("Тест корректной инициализации полей DeliveryRequest")
    void testDeliveryRequestFieldsAreSetCorrectly() {
        DeliveryRequest request = new DeliveryRequest(5, true, false, DeliveryLoad.NORMAL);
        assertAll(
                () -> assertEquals(5, request.getDistance()),
                () -> assertTrue(request.isLarge()),
                () -> assertFalse(request.isFragile()),
                () -> assertEquals(DeliveryLoad.NORMAL, request.getLoadLevel())
        );
    }

    @Test
    @Tag("negative")
    @DisplayName("Негативный тест: exception при передаче null в loadLevel")
    void testDeliveryRequestThrowsExceptionWhenLoadLevelIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new DeliveryRequest(10, true, false, null)
        );
        assertEquals("Уровень загрузки не может быть null", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "0, false, 50",
            "1, false, 50",
            "2, false, 50",
            "3, false, 100",
            "9, false, 100",
            "10, false, 100",
            "11, false, 200",
            "29, false, 200",
            "30, false, 200",
            "31, false, 300",
            "1, true, 50",
            "5, true, 100",
            "29, true, 200",
            "30, true, 200"
    })
    @Tag("positive")
    @DisplayName("Тест граничных значений расчета надбавки за расстояние")
    void testCalculateDistanceCost(int distance, boolean isFragile, int expectedCost) {
        assertEquals(expectedCost, DeliveryCalculator.calculateDistanceCost(distance, isFragile));
    }

    @Test
    @Tag("negative")
    @DisplayName("Негативный тест: доставка хрупкого товара дальше 30 км")
    void testCalculateDistanceCostExceptionFragileOver30Km() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> DeliveryCalculator.calculateDistanceCost(31, true)
        );
        assertEquals("Хрупкие грузы нельзя перевозить дальше 30 км", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "true, 200",
            "false, 100"
    })
    @Tag("positive")
    @DisplayName("Тест расчета надбавки за большие габариты")
    void testCalculateSizeCost(boolean isLarge, int expectedCost) {
        assertEquals(expectedCost, DeliveryCalculator.calculateSizeCost(isLarge));
    }

    @ParameterizedTest
    @CsvSource({
            "true, 300",
            "false, 0"
    })
    @Tag("positive")
    @DisplayName("Тест расчета надбавки за хрупкость")
    void testCalculateFragileCost(boolean isFragile, int expectedCost) {
        assertEquals(expectedCost, DeliveryCalculator.calculateFragileCost(isFragile));
    }

    @ParameterizedTest
    @CsvSource({
            "VERY_HIGH, 1.6",
            "HIGH, 1.4",
            "INCREASED, 1.2",
            "NORMAL, 1.0"
    })
    @Tag("positive")
    @DisplayName("Тест расчета коэффициента загруженности доставки")
    void testCalculateFragileCost(DeliveryLoad loadLevel, double expectedMultiplier) {
        assertEquals(expectedMultiplier, DeliveryCalculator.calculateLoadMultiplier(loadLevel));
    }

    @ParameterizedTest
    @CsvSource({
            "1, false, false, NORMAL, 400",
            "7, true, true, INCREASED, 720",
            "15, false, true, HIGH, 840",
            "100, true, false, VERY_HIGH, 800"
    })
    @Tag("positive")
    @DisplayName("Тест полного расчета доставки с разными комбинациями данных")
    void testCalculateFinalCost(int distance, boolean isLarge, boolean isFragile, DeliveryLoad loadLevel, int expectedCost) {
        DeliveryRequest request = new DeliveryRequest(distance, isLarge, isFragile, loadLevel);

        assertEquals(expectedCost, DeliveryService.calculateDeliveryCost(request));
    }

}

