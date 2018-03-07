package com.dyq.demo.service;

import com.dyq.demo.domain.Document;
import com.dyq.demo.domain.DocumentComment;
import com.dyq.demo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DocumentCommentService extends BaseModelService<DocumentComment>  {
    Page<DocumentComment> findByCommentDocument(Document document, Pageable pageable);
    List<DocumentComment> findByDocumentCommentUser(User user);
    DocumentComment addVote(Long did);
    DocumentComment addDownVote(Long did);
}
