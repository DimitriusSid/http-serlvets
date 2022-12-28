package com.user.servlet.validator;

import com.user.servlet.dto.CreateUserDto;
import com.user.servlet.entity.Gender;
import com.user.servlet.util.LocalDateFormatter;

public class CreateUserValidator implements Validator<CreateUserDto> {

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    private CreateUserValidator() {}

    @Override
    public ValidationResult isValid(CreateUserDto object) {
        var validationResult = new ValidationResult();

        if (!LocalDateFormatter.isValid(object.getBirthday())) {
            validationResult.add(Error.of("invalid.birthday", "Birthday is invalid"));
        }

        if (Gender.find(object.getGender()).isEmpty()) {
            validationResult.add(Error.of("invalid.gender", "Gender is invalid"));
        }

        return validationResult;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }

}
