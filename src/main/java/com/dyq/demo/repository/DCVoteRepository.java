package com.dyq.demo.repository;

import com.dyq.demo.domain.DCVote;
import com.dyq.demo.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DCVoteRepository extends JpaRepository<DCVote,Long> {
}
