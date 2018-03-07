package com.dyq.demo.service;

import com.dyq.demo.domain.DocumentReply;
import com.dyq.demo.repository.DocumentReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentReplyServiceImpl implements DocumentReplyService {
    @Autowired
    private DocumentReplyRepository DocumentReplyRepository;

    @Override
    public DocumentReply findById(Long id) {
        return DocumentReplyRepository.findOne(id);
    }

    @Override
    public DocumentReply save(DocumentReply documentReply) {
        return DocumentReplyRepository.save(documentReply);
    }

    @Override
    public DocumentReply update(DocumentReply documentReply) {
        return DocumentReplyRepository.save(documentReply);
    }

    @Override
    public void remove(Long id) {
        DocumentReplyRepository.delete(id);
    }

    @Override
    public List<DocumentReply> findAll() {
        return DocumentReplyRepository.findAll();
    }

    @Override
    public List<DocumentReply> findAll(Sort sort) {
        return DocumentReplyRepository.findAll(sort);
    }

    @Override
    public Page<DocumentReply> findAll(Pageable pageable) {
        return DocumentReplyRepository.findAll(pageable);
    }
}
