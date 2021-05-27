package rhirabay;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.migrator.PropertiesMigrationListenerWrapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class, TestAutoConfiguration.class})
class PropertyMigrateTest {

    @Autowired
    private PropertiesMigrationListenerWrapper migrationListener;

    @Test
    void test() {
        assertFalse(migrationListener.existsDeprecatedProperty());
    }
}