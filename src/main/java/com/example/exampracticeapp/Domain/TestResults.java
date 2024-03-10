package com.example.exampracticeapp.Domain;

import com.example.exampracticeapp.Domain.Enum.TestType;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class TestResults extends Entity<Long>{
    LocalDateTime timeStamp ;
    TestType testType;
    Subject subject;

    public TestResults(LocalDateTime timeStamp, TestType testType, Subject subject) {
        this.timeStamp = timeStamp;
        this.testType = testType;
        this.subject=subject;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public TestType getTestType() {
        return testType;
    }

    public void setTestType(TestType testType) {
        this.testType = testType;
    }

    @Override
    public String toString() {
        return "TestResults{" +
                "timeStamp=" + timeStamp +
                ", testType=" + testType +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestResults that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getTimeStamp(), that.getTimeStamp()) && getTestType() == that.getTestType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTimeStamp(), getTestType());
    }
}
