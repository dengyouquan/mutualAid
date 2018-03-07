package com.dyq.demo.repository;

import com.dyq.demo.domain.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BlogRepository extends JpaRepository<Blog,Long> {
    List<Blog> findByType(String type);
    List<Blog> findByType(String type, Sort sort);
    Page<Blog> findByType(String type, Pageable pageable);
}
