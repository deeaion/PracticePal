package com.example.exampracticeapp.Domain;

import com.example.exampracticeapp.Domain.Enum.TestType;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TestResults extends Entity<Long>{
    LocalDateTime timeStamp ;
    TestType testType;
    

}
