package com.example.exampracticeapp.Repository.DBRepositories.EntitiesRepositories;

import com.example.exampracticeapp.Domain.Chapter;
import com.example.exampracticeapp.Domain.Enum.Difficulty;
import com.example.exampracticeapp.Domain.Enum.QuestionType;
import com.example.exampracticeapp.Domain.Question;
import com.example.exampracticeapp.Domain.Subject;
import com.example.exampracticeapp.Domain.Validators.Validator;
import com.example.exampracticeapp.Exception.RepositoryException;
import com.example.exampracticeapp.Repository.DBRepositories.Util.AbstractDataBaseRepository;
import com.example.exampracticeapp.Repository.DBRepositories.Util.DataBaseAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;

public class DBQuestionRepository extends AbstractDataBaseRepository<Long, Question> {
    public DBQuestionRepository(Validator validator, DataBaseAccess data, String table) {
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
    @Override
    public Optional<Question> findOne(Long id) {
        if (id==null)
        {
            throw new IllegalArgumentException("Id provided is null!\n");
        }
        String findStatement="SELECT * FROM get_all_questions() where QuestionId= ?";
        try {
            PreparedStatement statement= data.createStatement(findStatement);
            statement.setLong(1,id);
            ResultSet resultSet= statement.executeQuery();
            if(resultSet.next())
            {
                Question question=getQuestion(resultSet);
                data.closeConnection();
                return Optional.of(question);
            }
            data.closeConnection();
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Iterable<Question> findAll() {
        String findStatement="SELECT * FROM get_all_questions()";
        HashSet<Question> questions=new HashSet<>();
        try {
            PreparedStatement statement= data.createStatement(findStatement);
            ResultSet resultSet= statement.executeQuery();
            while (resultSet.next())
            {
                Question question=getQuestion(resultSet);
                data.closeConnection();
                questions.add(question);
            }
            data.closeConnection();
            return questions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Question> save(Question entity) {
        String insertSQL=
                "INSERT INTO Question(ChapterId,QuestionTitle,QuestionType) values (?,?,?)";
        try {
            PreparedStatement statement= data.createStatement(insertSQL);
            statement.setLong(1,entity.getQuestionChapter().getId());
            statement.setString(2,entity.getQuestionTitle());
            statement.setString(3,entity.getQuestionType().toString());

            int response=statement.executeUpdate();
            data.closeConnection();
            return response==0?Optional.of(entity):Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Question> delete(Long id) {

        if(id==null) {
            throw new IllegalArgumentException("Id cannot be null!\n");
        }
        Optional<Question> question=findOne(id);
        if(question.isEmpty())
        {
            throw new RepositoryException("Chapter does not exist with this certain id "+id+"!\n" );
        }
        try {
            String deleteStatement="DELETE FROM Question where QuestionId= ?";
            PreparedStatement statement= data.createStatement(deleteStatement);
            statement.setLong(1,id);
            int response= statement.executeUpdate();
            data.closeConnection();
            return response==0?Optional.empty() : question;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Question> update(Question entity) {

        if (entity==null)
        {
            throw new RuntimeException("Question must not be null!");
        }
        //validator.vaildate(entity);
        String updateStatement="UPDATE Question set ChapterId=?,QuestionTitle=?,QuestionType=? where QuestionId=?";
        try {
            PreparedStatement statement= data.createStatement(updateStatement);
            statement.setLong(1,entity.getQuestionChapter().getId());
            statement.setString(2,entity.getQuestionTitle());
            statement.setString(3,entity.getQuestionType().toString());
            statement.setLong(4,entity.getId());
            int response=statement.executeUpdate();
            data.closeConnection();
            return response==0? Optional.of(entity) : Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
