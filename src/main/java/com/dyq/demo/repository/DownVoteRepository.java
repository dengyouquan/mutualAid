package com.dyq.demo.repository;

import com.dyq.demo.domain.DownVote;
import com.dyq.demo.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DownVoteRepository extends JpaRepository<DownVote,Long> {
}
