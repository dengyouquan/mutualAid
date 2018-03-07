package com.dyq.demo.repository;

import com.dyq.demo.domain.DCDownVote;
import com.dyq.demo.domain.DownVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DCDownVoteRepository extends JpaRepository<DCDownVote,Long> {
}
