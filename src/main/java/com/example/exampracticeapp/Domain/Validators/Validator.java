package com.example.exampracticeapp.Domain.Validators;


import com.example.exampracticeapp.Exception.ValidationException;

/*
    Validator for Enitities. It is an univers Validator
 */
public interface Validator <T>{
    void vaildate(T entity) throws ValidationException;
}
