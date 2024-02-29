package com.example.exampracticeapp.Utils.Observer;


import com.example.exampracticeapp.Utils.Event;

public interface Observable <E extends Event> {
    void addObserver(Observer<E> e);
    void removeObserver(Observer<E> e);
    void notifyObservers(E t);
}
