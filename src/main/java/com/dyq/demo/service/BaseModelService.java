package com.dyq.demo.service;

import com.dyq.demo.domain.BaseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface BaseModelService<T extends BaseModel> {
    T findById(Long id);
    T save(T t);
    T update(T t);
    void remove(Long id);
    List<T> findAll();
    List<T> findAll(Sort sort);
    //List<User> list = page.getContent();
    Page<T> findAll(Pageable pageable);
}
