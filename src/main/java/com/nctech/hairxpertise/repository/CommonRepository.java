package com.nctech.hairxpertise.repository;

import java.util.List;

public interface CommonRepository <T>{
    public void save(T value);
    public List<T> get();
}
