package com.ameri.converter.user;

import com.ameri.converter.Converter;
import com.ameri.objects.classes.user.User;

public class UserConverter extends Converter<User> {

    public UserConverter(Class<User> typeConverter) {
        super(typeConverter);
    }
}
