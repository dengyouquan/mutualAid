package com.dyq.demo.repository;

import com.dyq.demo.domain.Blog;
import com.dyq.demo.domain.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface DocumentRepository extends JpaRepository<Document,Long> {
    List<Document> findByType(String type);
    List<Document> findByType(String type, Sort sort);
    Page<Document> findByType(String type, Pageable pageable);
    @Query("select count(*) from Document")
    int getCount();
    @Query("select count(*) from Document where type=?1")
    int getCount(String type);
    int countByType(String type);
}
