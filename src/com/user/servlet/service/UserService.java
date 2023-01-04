package com.user.servlet.service;

import com.user.servlet.dao.UserDao;
import com.user.servlet.dto.CreateUserDto;
import com.user.servlet.dto.UserDto;
import com.user.servlet.exception.ValidationException;
import com.user.servlet.mapper.CreateUserMapper;
import com.user.servlet.mapper.UserMapper;
import com.user.servlet.validator.CreateUserValidator;
import lombok.SneakyThrows;

import java.util.Optional;

public class UserService {

    private static final UserService INSTANCE = new UserService();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();

    private final UserMapper userMapper = UserMapper.getInstance();

    public Optional<UserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email, password)
                .map(userMapper::mapFrom);
    }

    @SneakyThrows
    public Integer create(CreateUserDto userDto) {
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = createUserMapper.mapFrom(userDto);
        imageService.upload(userEntity.getImage(), userDto.getImage().getInputStream());
        userDao.save(userEntity);
        return userEntity.getId();
    }

    private UserService() {}

    public static UserService getInstance() {
        return INSTANCE;
    }


}
