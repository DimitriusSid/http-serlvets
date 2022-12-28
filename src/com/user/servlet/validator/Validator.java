package com.user.servlet.validator;

public interface Validator<T> {

    ValidationResult isValid(T object);

}
