package com.dyq.demo.service;

import com.dyq.demo.domain.Blog;
import com.dyq.demo.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog findById(Long id) {
        return blogRepository.findOne(id);
    }

    @Override
    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog update(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public void remove(Long id) {
        blogRepository.delete(id);
    }

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public List<Blog> findAll(Sort sort) {
        return blogRepository.findAll(sort);
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public void readingIncrease(Long id) {

    }

    @Override
    public Blog createComment(Long blogId, String commentContent) {
        return null;
    }

    @Override
    public void removeComment(Long blogId, Long commentId) {

    }

    @Override
    public Blog createVote(Long blogId) {
        return null;
    }

    @Override
    public void removeVote(Long blogId, Long voteId) {

    }
}
