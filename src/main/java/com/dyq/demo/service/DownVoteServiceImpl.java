package com.dyq.demo.service;

import com.dyq.demo.domain.DownVote;
import com.dyq.demo.domain.Vote;
import com.dyq.demo.repository.DownVoteRepository;
import com.dyq.demo.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DownVoteServiceImpl implements DownVoteService {
    @Autowired
    private DownVoteRepository downVoteRepository;

    @Override
    public DownVote findById(Long id) {
        return downVoteRepository.findOne(id);
    }

    @Override
    public DownVote save(DownVote downVote) {
        return downVoteRepository.save(downVote);
    }

    @Override
    public DownVote update(DownVote downVote) {
        return downVoteRepository.save(downVote);
    }

    @Override
    public void remove(Long id) {
        downVoteRepository.delete(id);
    }

    @Override
    public List<DownVote> findAll() {
        return downVoteRepository.findAll();
    }

    @Override
    public List<DownVote> findAll(Sort sort) {
        return downVoteRepository.findAll(sort);
    }

    @Override
    public Page<DownVote> findAll(Pageable pageable) {
        return downVoteRepository.findAll(pageable);
    }
}
