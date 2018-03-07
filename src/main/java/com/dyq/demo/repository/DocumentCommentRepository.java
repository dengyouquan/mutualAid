package com.dyq.demo.repository;

import com.dyq.demo.domain.Document;
import com.dyq.demo.domain.DocumentComment;
import com.dyq.demo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface DocumentCommentRepository extends JpaRepository<DocumentComment,Long> {
    Page<DocumentComment> findByCommentDocument(Document document, Pageable pageable);
    List<DocumentComment> findByDocumentCommentUser(User user);
}
