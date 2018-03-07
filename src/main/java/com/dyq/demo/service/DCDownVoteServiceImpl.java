package com.dyq.demo.service;

import com.dyq.demo.domain.DCDownVote;
import com.dyq.demo.repository.DCDownVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DCDownVoteServiceImpl implements DCDownVoteService {
    @Autowired
    private DCDownVoteRepository dcdownVoteRepository;

    @Override
    public DCDownVote findById(Long id) {
        return dcdownVoteRepository.findOne(id);
    }

    @Override
    public DCDownVote save(DCDownVote dcdownVote) {
        return dcdownVoteRepository.save(dcdownVote);
    }

    @Override
    public DCDownVote update(DCDownVote dcdownVote) {
        return dcdownVoteRepository.save(dcdownVote);
    }

    @Override
    public void remove(Long id) {
        dcdownVoteRepository.delete(id);
    }

    @Override
    public List<DCDownVote> findAll() {
        return dcdownVoteRepository.findAll();
    }

    @Override
    public List<DCDownVote> findAll(Sort sort) {
        return dcdownVoteRepository.findAll(sort);
    }

    @Override
    public Page<DCDownVote> findAll(Pageable pageable) {
        return dcdownVoteRepository.findAll(pageable);
    }
}
