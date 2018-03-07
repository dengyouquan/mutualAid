package com.dyq.demo.service;

import com.dyq.demo.domain.Document;
import com.dyq.demo.domain.DownVote;
import com.dyq.demo.domain.ES.ESDocument;
import com.dyq.demo.domain.User;
import com.dyq.demo.domain.Vote;
import com.dyq.demo.repository.DocumentRepository;
import com.dyq.demo.service.ES.ESDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private ESDocumentService esDocumentService;
    @Autowired
    private VoteService voteService;
    @Autowired
    private DownVoteService downVoteService;

    @Override
    public Document findById(Long id) {
        return documentRepository.findOne(id);
    }

    @Override
    public Document save(Document document) {
        boolean isNew = (document.getId() == null);
        ESDocument esDocument = null;
        Document returnDocument = documentRepository.save(document);
        if (isNew) {
            esDocument = new ESDocument(returnDocument);
        } else {
            esDocument = esDocumentService.getESDocumentByDocumentId(returnDocument.getId());
            esDocument.update(returnDocument);
        }
        esDocumentService.updateESDocument(esDocument);
        return returnDocument;
    }

    @Override
    public Document update(Document document) {
        boolean isNew = (document.getId() == null);
        ESDocument esDocument = null;
        Document returnDocument = documentRepository.save(document);
        if (isNew) {
            esDocument = new ESDocument(returnDocument);
        } else {
            esDocument = esDocumentService.getESDocumentByDocumentId(returnDocument.getId());
            esDocument.update(returnDocument);
        }
        esDocumentService.updateESDocument(esDocument);
        return returnDocument;
    }

    @Override
    public void remove(Long id) {
        documentRepository.delete(id);
    }

    @Override
    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    @Override
    public List<Document> findAll(Sort sort) {
        return documentRepository.findAll(sort);
    }

    @Override
    public Page<Document> findAll(Pageable pageable) {
        return documentRepository.findAll(pageable);
    }

    public List<Document> findByType(String type) {
        return documentRepository.findByType(type);
    }

    @Override
    public List<Document> findByType(String type, Sort sort) {
        return documentRepository.findByType(type,sort);
    }

    @Override
    public Page<Document> findByType(String type, Pageable pageable) {
        return documentRepository.findByType(type,pageable);
    }

    @Override
    public int getCount() {
        return documentRepository.getCount();
    }

    @Override
    public int getCount(String type) {
        return documentRepository.getCount();
    }

    @Override
    public Document addVote(Long did) {
        Document document = this.findById(did);
        Vote vote = document.isVote();
        //已经赞过
        if(vote.getId()!=null){
            //需要取赞
            System.out.println("取赞");
            document.getVotes().remove(vote);
            document.setVoteSize(document.getVotes().size());
            voteService.remove(vote.getId());
        }else{
            //需要点赞
            System.out.println("点赞");
            document.getVotes().add(vote);
            document.setVoteSize(document.getVotes().size());
        }
        return this.save(document);
    }

    @Override
    public Document addDownVote(Long did) {
        Document document = this.findById(did);
        DownVote downVote = document.isDownVote();
        //已经踩过
        if(downVote.getId()!=null){
            //需要取踩
            System.out.println("取踩");
            document.getDownVotes().remove(downVote);
            document.setDownVoteSize(document.getDownVotes().size());
            downVoteService.remove(downVote.getId());
        }else{
            //需要点踩
            System.out.println("点踩");
            document.getDownVotes().add(downVote);
            document.setDownVoteSize(document.getDownVotes().size());
        }
        return this.save(document);
    }

}
