package com.user.servlet.service;

import com.user.servlet.dao.UserDao;
import com.user.servlet.dto.CreateUserDto;
import com.user.servlet.entity.User;
import com.user.servlet.exception.ValidationException;
import com.user.servlet.mapper.CreateUserMapper;
import com.user.servlet.validator.CreateUserValidator;
import com.user.servlet.validator.ValidationResult;

public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();

    public Integer create(CreateUserDto userDto) {
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = createUserMapper.mapFrom(userDto);
        userDao.save(userEntity);
        return userEntity.getId();
    }

    private UserService() {}

    public static UserService getInstance() {
        return INSTANCE;
    }


}
