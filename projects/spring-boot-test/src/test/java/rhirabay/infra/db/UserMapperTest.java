package rhirabay.infra.db;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    @Sql(statements = {
            "INSERT INTO users VALUES ('1', 'rhirabay')"
    })
    void test_findAll() {
        var users = userMapper.findAll();
        assertThat(users).hasSize(1);
    }
}