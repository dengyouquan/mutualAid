package com.dyq.demo.service;

import com.dyq.demo.domain  .Reward;
import com.dyq.demo.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardServiceImpl implements RewardService {
    @Autowired
    private RewardRepository rewardRepository;

    @Override
    public Reward findById(Long id) {
        return rewardRepository.findOne(id);
    }

    @Override
    public Reward save(Reward reward) {
        return rewardRepository.save(reward);
    }

    @Override
    public Reward update(Reward reward) {
        return rewardRepository.save(reward);
    }

    @Override
    public void remove(Long id) {
        rewardRepository.delete(id);
    }

    @Override
    public List<Reward> findAll() {
        return rewardRepository.findAll();
    }

    @Override
    public List<Reward> findAll(Sort sort) {
        return rewardRepository.findAll(sort);
    }

    @Override
    public Page<Reward> findAll(Pageable pageable) {
        return rewardRepository.findAll(pageable);
    }
}
