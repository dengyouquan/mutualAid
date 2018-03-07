package com.dyq.demo.repository;

import com.dyq.demo.domain.DocumentComment;
import com.dyq.demo.domain.DocumentReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DocumentReplyRepository extends JpaRepository<DocumentReply,Long> {
}
