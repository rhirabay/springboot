package rhirabay;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ConstructorMock {
    @Test
    void test() {
        // Mock化の準備。close()の必要があるので try with resource文で
        try (MockedConstruction<Random> mockedRandom = mockConstruction(Random.class)) {
            // Mock化されたインスタンスが生成される
            var random = new Random();

            // Mock動作を指定（通常のMockと一緒）
            when(random.nextInt()).thenReturn(1);

            assertThat(random.nextInt()).isEqualTo(1);
            assertThat(random.nextInt()).isEqualTo(1);
            assertThat(random.nextInt()).isEqualTo(1);

            // Mock呼び出され方を検証（通常のMockと一緒）
            verify(random, times(3)).nextInt();
        }
    }
}
