package com.example.exampracticeapp.Repository.DBRepositories.EntitiesRepositories;

import com.example.exampracticeapp.Domain.*;
import com.example.exampracticeapp.Domain.Enum.Difficulty;
import com.example.exampracticeapp.Domain.Enum.QuestionType;
import com.example.exampracticeapp.Domain.Enum.TestType;
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

public class DBTestAnswerRepository extends AbstractDataBaseRepository<Long, TestAnswer> {
    public DBTestAnswerRepository(Validator validator, DataBaseAccess data, String table) {
        super(validator, data, table);
    }
    private Subject getSubject(ResultSet resultSet) throws SQLException {
        Long id=resultSet.getLong("SubjectId");
        String name=resultSet.getString("SubjectName");
        String description=resultSet.getString("SubjectDescription");
        int year=resultSet.getInt("SubjectYear");
        int semester=resultSet.getInt("SubjectSemester");
        Subject newSubject=new Subject(name,description,year,semester);
        newSubject.setId(id);
        return newSubject;
    }
    private TestResults getTestResults(ResultSet resultSet) throws SQLException
    {
        Long id=resultSet.getLong("id_test");
        LocalDateTime test_date= resultSet.getTimestamp("test_date").toLocalDateTime();
        TestType testType= TestType.valueOf(resultSet.getString("test_type"));
        TestResults testResults=new TestResults(test_date,testType,getSubject(resultSet));

        testResults.setId(id);
        return testResults;
    }
    private Chapter getChapter(ResultSet resultSet) throws SQLException {
        Long id=resultSet.getLong("ChapterId");
        int ChapterNumber=resultSet.getInt("ChapterNumber");
        String ChapterTitle=resultSet.getString("ChapterTitle");
        String ChapterDifficulty=resultSet.getString("ChapterDifficulty").toUpperCase();

        Subject subject=getSubject(resultSet);
        Chapter chapter=new Chapter(subject,ChapterNumber,ChapterTitle, Difficulty.valueOf(ChapterDifficulty));
        chapter.setId(id);
        return chapter;
    }
    private Question getQuestion(ResultSet resultSet) throws SQLException {
        Long id=resultSet.getLong("questionid");
        String title=resultSet.getString("QuestionTitle");
        String type=resultSet.getString("QuestionType");
        Chapter chapter=getChapter(resultSet);
        Question question=new Question(chapter,title, QuestionType.valueOf(type.toUpperCase()));
        question.setId(id);
        return question;

    }
    private TestAnswer getTestAnswer(ResultSet resultSet) throws SQLException
    {
        Long id=resultSet.getLong("id_testAnswer");
        Question question=getQuestion(resultSet);
        TestResults testResults=getTestResults(resultSet);
        String answer=resultSet.getString("answer");
        String veracity=resultSet.getString("veracity");
        TestAnswer testAnswer=new TestAnswer(question,answer,veracity,testResults);
        testAnswer.setId(id);
        return testAnswer;
    }
    @Override
    public Optional<TestAnswer> findOne(Long id) {


        if (id==null)
        {
            throw new IllegalArgumentException("Id provided is null!\n");
        }
        String findStatement="select * from get_all_test_answers() where id_testAnswer=?";
        try {
            PreparedStatement statement= data.createStatement(findStatement);
            statement.setLong(1,id);
            ResultSet resultSet= statement.executeQuery();
            if(resultSet.next())
            {
                TestAnswer testAnswer=getTestAnswer(resultSet);
                data.closeConnection();
                return Optional.of(testAnswer);
            }
            data.closeConnection();
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<TestAnswer> findAll() {

        String findStatement="select * from get_all_test_answers()";
        HashSet<TestAnswer> testAnswers=new HashSet<>();
        try {
            PreparedStatement statement= data.createStatement(findStatement);
            ResultSet resultSet= statement.executeQuery();
            while (resultSet.next())
            {
                TestAnswer testAnswer=getTestAnswer(resultSet);
                data.closeConnection();
                testAnswers.add(testAnswer);
            }
            data.closeConnection();
            return testAnswers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<TestAnswer> save(TestAnswer entity) {
        String insertSQL=
                "INSERT INTO TestAnswer(id_question,id_test,answer,veracity) values (?,?,?,?)";
        try {
            PreparedStatement statement= data.createStatement(insertSQL);
            statement.setLong(1,entity.getQuestion().getId());
            statement.setLong(2,entity.getCorrespondingTest().getId());
            statement.setString(3,entity.getAnswer());
            statement.setString(4, entity.getVeracity());
            int response=statement.executeUpdate();
            data.closeConnection();
            return response==0?Optional.of(entity):Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<TestAnswer> delete(Long id) {
        if(id==null) {
            throw new IllegalArgumentException("Id cannot be null!\n");
        }
        Optional<TestAnswer> testAnswer=findOne(id);
        if(testAnswer.isEmpty())
        {
            throw new RepositoryException("TestAnswer does not exist with this certain id "+id+"!\n" );
        }
        try {
            String deleteStatement="DELETE FROM TestAnswer where id_testAnswer= ?";
            PreparedStatement statement= data.createStatement(deleteStatement);
            statement.setLong(1,id);
            int response= statement.executeUpdate();
            data.closeConnection();
            return response==0?Optional.empty() :testAnswer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<TestAnswer> update(TestAnswer entity) {

        if (entity==null)
        {
            throw new RuntimeException("TestAnswer must not be null!");
        }
        //validator.vaildate(entity);
        String updateStatement="UPDATE TestAnswer set id_question=?,answer=?, veracity=? where id_testAnswer=?";
        try {
            PreparedStatement statement= data.createStatement(updateStatement);
            statement.setLong(1,entity.getQuestion().getId());
            statement.setString(2,entity.getAnswer());
            statement.setString(3,entity.getVeracity());
            statement.setLong(4,entity.getId());
            int response=statement.executeUpdate();
            data.closeConnection();
            return response==0? Optional.of(entity) : Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
