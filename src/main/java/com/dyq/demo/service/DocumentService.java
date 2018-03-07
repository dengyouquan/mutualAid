package com.dyq.demo.service;

import com.dyq.demo.domain.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface DocumentService extends BaseModelService<Document> {
    List<Document> findByType(String type);
    List<Document> findByType(String type, Sort sort);
    Page<Document> findByType(String type, Pageable pageable);
    int getCount();
    int getCount(String type);
    Document addVote(Long did);
    Document addDownVote(Long did);
}
