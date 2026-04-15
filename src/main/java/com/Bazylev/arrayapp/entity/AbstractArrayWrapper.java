package com.bazylev.arrayapp.entity;

import com.bazylev.arrayapp.observer.Observable;
import com.bazylev.arrayapp.observer.Observer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AbstractArrayWrapper implements Observable {
    private String id;
    private String name;
    private List<Observer> observers;

    public AbstractArrayWrapper() {
        this.id = UUID.randomUUID().toString();
        this.name = "Array_" + id.substring(0, 8);
        this.observers = new ArrayList<>();
    }

    public AbstractArrayWrapper(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.observers = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyObservers();
    }

    public abstract int getLength();

    public abstract int[] getArray();

    public abstract void setArray(int[] array);

    @Override
    public void addObserver(Observer observer) {
        if (observers == null) {
            observers = new ArrayList<>();
        }
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observers != null) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        if (observers != null) {
            for (Observer observer : observers) {
                observer.update(this);
            }
        }
    }
}