package com.nctech.hairxpertise.services;

import java.util.List;

public interface CRUDServices<T, K> {
    public void save(T value);
    public List<T> fetchAll();
    public T fetchByID(K id);
    public void update(T value);
    public boolean delete (K id);

}
