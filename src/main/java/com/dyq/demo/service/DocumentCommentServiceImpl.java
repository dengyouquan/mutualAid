package com.dyq.demo.service;

import com.dyq.demo.domain.*;
import com.dyq.demo.repository.DocumentCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentCommentServiceImpl implements DocumentCommentService {
    @Autowired
    private DocumentCommentRepository documentCommentRepository;
    @Autowired
    private DCVoteService dcvoteService;
    @Autowired
    private DCDownVoteService dcdownVoteService;

    @Override
    public DocumentComment findById(Long id) {
        return documentCommentRepository.findOne(id);
    }

    @Override
    public DocumentComment save(DocumentComment documentComment) {
        return documentCommentRepository.save(documentComment);
    }

    @Override
    public DocumentComment update(DocumentComment documentComment) {
        return documentCommentRepository.save(documentComment);
    }

    @Override
    public void remove(Long id) {
        documentCommentRepository.delete(id);
    }

    @Override
    public List<DocumentComment> findAll() {
        return documentCommentRepository.findAll();
    }

    @Override
    public List<DocumentComment> findAll(Sort sort) {
        return documentCommentRepository.findAll(sort);
    }

    @Override
    public Page<DocumentComment> findAll(Pageable pageable) {
        return documentCommentRepository.findAll(pageable);
    }

    @Override
    public Page<DocumentComment> findByCommentDocument(Document document, Pageable pageable) {
        return documentCommentRepository.findByCommentDocument(document,pageable);
    }
    @Override
    public List<DocumentComment> findByDocumentCommentUser(User user) {
        return documentCommentRepository.findByDocumentCommentUser(user);
    }

    @Override
    public DocumentComment addVote(Long dcid) {
        DocumentComment documentComment = this.findById(dcid);
        DCVote vote = documentComment.isVote();
        //已经赞过
        if(vote.getId()!=null){
            //需要取赞
            System.out.println("取赞");
            documentComment.getDcvotes().remove(vote);
            documentComment.setVoteSize(documentComment.getDcvotes().size());
            dcvoteService.remove(vote.getId());
        }else{
            //需要点赞
            System.out.println("点赞");
            documentComment.getDcvotes().add(vote);
            documentComment.setVoteSize(documentComment.getDcvotes().size());
        }
        return this.save(documentComment);
    }

    @Override
    public DocumentComment addDownVote(Long dcid) {
        DocumentComment documentComment = this.findById(dcid);
        DCDownVote downVote = documentComment.isDownVote();
        //已经踩过
        if(downVote.getId()!=null){
            //需要取踩
            System.out.println("取踩");
            documentComment.getDcdownVotes().remove(downVote);
            documentComment.setDownVoteSize(documentComment.getDcdownVotes().size());
            dcdownVoteService.remove(downVote.getId());
        }else{
            //需要点踩
            System.out.println("点踩");
            documentComment.getDcdownVotes().add(downVote);
            documentComment.setDownVoteSize(documentComment.getDcdownVotes().size());
        }
        return this.save(documentComment);
    }
}
