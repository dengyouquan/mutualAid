package com.dyq.demo.service;

import com.dyq.demo.domain.DCVote;
import com.dyq.demo.repository.DCVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DCVoteServiceImpl implements DCVoteService {
    @Autowired
    private DCVoteRepository dcvoteRepository;

    @Override
    public DCVote findById(Long id) {
        return dcvoteRepository.findOne(id);
    }

    @Override
    public DCVote save(DCVote dcvote) {
        return dcvoteRepository.save(dcvote);
    }

    @Override
    public DCVote update(DCVote dcvote) {
        return dcvoteRepository.save(dcvote);
    }

    @Override
    public void remove(Long id) {
        dcvoteRepository.delete(id);
    }

    @Override
    public List<DCVote> findAll() {
        return dcvoteRepository.findAll();
    }

    @Override
    public List<DCVote> findAll(Sort sort) {
        return dcvoteRepository.findAll(sort);
    }

    @Override
    public Page<DCVote> findAll(Pageable pageable) {
        return dcvoteRepository.findAll(pageable);
    }
}
