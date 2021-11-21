package rhirabay.converter;

import org.springframework.core.convert.converter.Converter;
import rhirabay.model.User;

public class UserConverter implements Converter<String, User> {
    @Override
    public User convert(String source) {
        return null;
    }
}
