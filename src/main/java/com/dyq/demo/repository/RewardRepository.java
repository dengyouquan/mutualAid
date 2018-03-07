package com.dyq.demo.repository;

import com.dyq.demo.domain.Blog;
import com.dyq.demo.domain.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RewardRepository extends JpaRepository<Reward,Long> {
}
