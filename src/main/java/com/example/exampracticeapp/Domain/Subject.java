package com.example.exampracticeapp.Domain;

import java.util.Objects;

public class Subject extends Entity<Long>{
    private String SubjectName;
    private String SubjectDescription;
    private int SubjectYear;
    private int SubjectSemester;

    public Subject( String subjectName, String subjectDescription, int subjectYear, int subjectSemester) {
        SubjectName = subjectName;
        SubjectDescription = subjectDescription;
        SubjectYear = subjectYear;
        SubjectSemester = subjectSemester;
    }





    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }

    public String getSubjectDescription() {
        return SubjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        SubjectDescription = subjectDescription;
    }

    public int getSubjectYear() {
        return SubjectYear;
    }

    public void setSubjectYear(int subjectYear) {
        SubjectYear = subjectYear;
    }

    public int getSubjectSemester() {
        return SubjectSemester;
    }

    public void setSubjectSemester(int subjectSemester) {
        SubjectSemester = subjectSemester;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "SubjectId=" + id +
                ", SubjectName='" + SubjectName + '\'' +
                ", SubjectDescription='" + SubjectDescription + '\'' +
                ", SubjectYear=" + SubjectYear +
                ", SubjectSemester=" + SubjectSemester +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject subject)) return false;
        return getSubjectYear() == subject.getSubjectYear() && getSubjectSemester() == subject.getSubjectSemester() && Objects.equals(getId(), subject.getId()) && Objects.equals(getSubjectName(), subject.getSubjectName()) && Objects.equals(getSubjectDescription(), subject.getSubjectDescription());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSubjectName(), getSubjectDescription(), getSubjectYear(), getSubjectSemester());
    }
}
