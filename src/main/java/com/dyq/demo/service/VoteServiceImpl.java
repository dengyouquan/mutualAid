package com.dyq.demo.service;

import com.dyq.demo.domain.Vote;
import com.dyq.demo.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    private VoteRepository voteRepository;

    @Override
    public Vote findById(Long id) {
        return voteRepository.findOne(id);
    }

    @Override
    public Vote save(Vote vote) {
        return voteRepository.save(vote);
    }

    @Override
    public Vote update(Vote vote) {
        return voteRepository.save(vote);
    }

    @Override
    public void remove(Long id) {
        voteRepository.delete(id);
    }

    @Override
    public List<Vote> findAll() {
        return voteRepository.findAll();
    }

    @Override
    public List<Vote> findAll(Sort sort) {
        return voteRepository.findAll(sort);
    }

    @Override
    public Page<Vote> findAll(Pageable pageable) {
        return voteRepository.findAll(pageable);
    }
}
