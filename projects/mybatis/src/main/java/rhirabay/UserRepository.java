package rhirabay;

import io.micrometer.core.annotation.Timed;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface UserRepository {
    @Timed(value = "sql.time")
    @Select("SELECT * FROM USER WHERE id = #{id}")
    Optional<User> findById(String id);
}
