package rhirabay.infra.db;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from users")
    List<UserEntity> findAll();

    @Insert("INSERT INTO users (id, name) VALUES(#{id}, #{name})")
    void insert(UserEntity user);
}
