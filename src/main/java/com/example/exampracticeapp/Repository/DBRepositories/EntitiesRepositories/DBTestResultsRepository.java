package com.example.exampracticeapp.Repository.DBRepositories.EntitiesRepositories;

import com.example.exampracticeapp.Domain.AnswerIdea;
import com.example.exampracticeapp.Domain.Enum.TestType;
import com.example.exampracticeapp.Domain.TestResults;
import com.example.exampracticeapp.Domain.Validators.Validator;
import com.example.exampracticeapp.Exception.RepositoryException;
import com.example.exampracticeapp.Repository.DBRepositories.Util.AbstractDataBaseRepository;
import com.example.exampracticeapp.Repository.DBRepositories.Util.DataBaseAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

public class DBTestResultsRepository extends AbstractDataBaseRepository<Long, TestResults> {
    public DBTestResultsRepository(Validator validator, DataBaseAccess data, String table) {
        super(validator, data, table);
    }
    private TestResults getTestResults(ResultSet resultSet) throws SQLException
    {
        Long id=resultSet.getLong("id_test");
        LocalDateTime test_date= resultSet.getTimestamp("test_date").toLocalDateTime();
        TestType testType= TestType.valueOf(resultSet.getString("test_type"));
        TestResults testResults=new TestResults(test_date,testType);
        testResults.setId(id);
        return testResults;
    }
    @Override
    public Optional<TestResults> findOne(Long id) {

        if (id==null)
        {
            throw new IllegalArgumentException("Id provided is null!\n");
        }
        String findStatement="select * from TestResults() where id_test=?";
        try {
            PreparedStatement statement= data.createStatement(findStatement);
            statement.setLong(1,id);
            ResultSet resultSet= statement.executeQuery();
            if(resultSet.next())
            {
                TestResults testResults=getTestResults(resultSet);
                data.closeConnection();
                return Optional.of(testResults);
            }
            data.closeConnection();
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<TestResults> findAll() {
        String findStatement="select * from TestResults";
        HashSet<TestResults> testResults=new HashSet<>();
        try {
            PreparedStatement statement= data.createStatement(findStatement);
            ResultSet resultSet= statement.executeQuery();
            while (resultSet.next())
            {
                TestResults testResult=getTestResults(resultSet);
                data.closeConnection();
                testResults.add(testResult);
            }
            data.closeConnection();
            return testResults;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<TestResults> save(TestResults entity) {

        String insertSQL=
                "INSERT INTO TestResults(test_date,test_type) values (?,?)";
        try {
            PreparedStatement statement= data.createStatement(insertSQL);
            statement.setTimestamp(1, Timestamp.valueOf(entity.getTimeStamp()));
            statement.setString(2, String.valueOf(entity.getTestType()));
            int response=statement.executeUpdate();
            data.closeConnection();
            return response==0?Optional.of(entity):Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<TestResults> delete(Long id) {

        if(id==null) {
            throw new IllegalArgumentException("Id cannot be null!\n");
        }
        Optional<TestResults> testResults=findOne(id);
        if(testResults.isEmpty())
        {
            throw new RepositoryException("Test does not exist with this certain id "+id+"!\n" );
        }
        try {
            String deleteStatement="DELETE FROM TestResults where id_test= ?";
            PreparedStatement statement= data.createStatement(deleteStatement);
            statement.setLong(1,id);
            int response= statement.executeUpdate();
            data.closeConnection();
            return response==0?Optional.empty() :testResults;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<TestResults> update(TestResults entity) {
        if (entity==null)
        {
            throw new RuntimeException("TestResults must not be null!");
        }
        //validator.vaildate(entity);
        String updateStatement="UPDATE TestResults set test_date=?, test_type=? where id_test=?";
        try {
            PreparedStatement statement= data.createStatement(updateStatement);
            statement.setLong(3,entity.getId());
            statement.setTimestamp(1, Timestamp.valueOf(entity.getTimeStamp()));
            statement.setString(2,entity.getTestType().toString());
            int response=statement.executeUpdate();
            data.closeConnection();
            return response==0? Optional.of(entity) : Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
