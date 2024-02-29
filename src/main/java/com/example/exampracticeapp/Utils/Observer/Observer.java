package com.example.exampracticeapp.Utils.Observer;


import com.example.exampracticeapp.Utils.Event;

public interface Observer <E extends Event> {
    void update(E e);
}
