package com.example.exampracticeapp.Repository.DBRepositories.EntitiesRepositories;

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

public class DBSubjectRepository extends AbstractDataBaseRepository<Long, Subject> {
    public DBSubjectRepository(Validator validator, DataBaseAccess data, String table) {
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
    @Override
    public Optional<Subject> findOne(Long id) {
        if (id==null)
        {
            throw new IllegalArgumentException("Id provided is null!\n");
        }
        String findStatement="SELECT * FROM Subject where SubjectId= ?";
        try {
            PreparedStatement statement= data.createStatement(findStatement);
            statement.setLong(1,id);
            ResultSet resultSet= statement.executeQuery();
            if(resultSet.next())
            {
                Subject subject=getSubject(resultSet);
                data.closeConnection();
                return Optional.of(subject);
            }
            data.closeConnection();
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<Subject> findAll() {
        String findStatement="SELECT * FROM Subject";
        HashSet<Subject> subjects=new HashSet<>();
        try {
            PreparedStatement statement= data.createStatement(findStatement);
            ResultSet resultSet= statement.executeQuery();
            while (resultSet.next())
            {
                Subject subject=getSubject(resultSet);
                data.closeConnection();
                subjects.add(subject);
            }
            data.closeConnection();
            return subjects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Subject> save(Subject entity) {
        //validator.vaildate(entity);
        String insertSQL="INSERT INTO Subject(SubjectName,SubjectDescription,SubjectYear,SubjectSemester) values(?,?,?,?)";
        try {
            PreparedStatement statement= data.createStatement(insertSQL);
            statement.setString(1,entity.getSubjectName());
            statement.setString(2,entity.getSubjectDescription());
            statement.setInt(3,entity.getSubjectYear());
            statement.setInt(4,entity.getSubjectSemester());
            int response=statement.executeUpdate();
            data.closeConnection();
            return response==0?Optional.of(entity):Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Subject> delete(Long id) {
        if(id==null) {
            throw new IllegalArgumentException("Id cannot be null!\n");
        }
        Optional<Subject> subject=findOne(id);
        if(subject.isEmpty())
        {
            throw new RepositoryException("Subject does not exist with this certain id "+id+"!\n" );
        }
        try {
            String deleteStatement="DELETE FROM Subject where SubjectId= ?";
            PreparedStatement statement= data.createStatement(deleteStatement);
            statement.setLong(1,id);
            int response= statement.executeUpdate();
            data.closeConnection();
            return response==0?Optional.empty() : subject;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Subject> update(Subject entity) {
        if (entity==null)
        {
            throw new RuntimeException("Subject must not be null!");
        }
        //validator.vaildate(entity);
        String updateStatement="UPDATE Subject set SubjectName= ?,SubjectDescription= ?,SubjectYear= ?,SubjectSemester=? where SubjectId= ?";
        try {
            PreparedStatement statement= data.createStatement(updateStatement);
            statement.setString(1,entity.getSubjectName());
            statement.setString(2,entity.getSubjectDescription());
            statement.setInt(3,entity.getSubjectYear());
            statement.setInt(4,entity.getSubjectSemester());
            statement.setLong(5,entity.getId());
            int response=statement.executeUpdate();
            data.closeConnection();
            return response==0? Optional.of(entity) : Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
