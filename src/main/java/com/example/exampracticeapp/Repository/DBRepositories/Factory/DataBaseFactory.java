package com.example.exampracticeapp.Repository.DBRepositories.Factory;


import com.example.exampracticeapp.Domain.Validators.Validator;
import com.example.exampracticeapp.Exception.RepositoryException;
import com.example.exampracticeapp.Repository.DBRepositories.Util.AbstractDataBaseRepository;

public interface DataBaseFactory {
    /**
     * Creates Factory for strategy
     * @param strategy Strategy that decides type of Validator
     * @return DataBaseFactory conform to strategy
     */
    AbstractDataBaseRepository createRepository(DataBaseRepositoryStrategy strategy, Validator validator) throws RepositoryException;
}
