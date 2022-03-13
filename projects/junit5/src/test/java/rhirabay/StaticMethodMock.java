package rhirabay;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

public class StaticMethodMock {
    @Test
    void test() {
        var mockedValue = LocalDate.of(2022, 3, 1);

        // Mock化の準備。close()の必要があるので try with resource文で
        try (MockedStatic<LocalDate> mockedLocalDate = mockStatic(LocalDate.class)) {
            // staticメソッドのMock化
            mockedLocalDate.when(() -> LocalDate.now()).thenReturn(mockedValue);

            // staticメソッドの呼び出し
            var actual = LocalDate.now();

            // 戻り値の検証
            assertThat(actual.toString()).isEqualTo("2022-03-01");

            // staticメソッドの呼び出され方の検証
            mockedLocalDate.verify(() -> LocalDate.now());
        }
    }
}

