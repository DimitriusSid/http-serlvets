package com.user.servlet.mapper;

public interface Mapper<F, T> {
    T mapFrom(F object);
}
