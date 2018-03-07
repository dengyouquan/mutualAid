package com.dyq.demo.service.ES;

import com.dyq.demo.domain.ES.ESDocument;
import com.dyq.demo.repository.ES.ESDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ESDocumentServiceImpl implements ESDocumentService {
    @Autowired
    private ESDocumentRepository esDocumentRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Override
    public void removeESDocument(String id) {
        esDocumentRepository.delete(id);
    }

    @Override
    public ESDocument updateESDocument(ESDocument esDocument) {
        return esDocumentRepository.save(esDocument);
    }

    @Override
    public ESDocument getESDocumentByDocumentId(Long documentId) {
        //return esDocumentRepository.findByDocumentId(documentId);
        return esDocumentRepository.findByEsdocumentId(documentId);
    }

    @Override
    public long count() {
        return esDocumentRepository.count();
    }

    @Override
    public Page<ESDocument> getNewestESDocuments(String type,String keyword, Pageable pageable) {
        Page<ESDocument> pages = null;
        Sort sort = new Sort(Sort.Direction.DESC,"createdAt");
        pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
        //判断是否有类型
        if(type==null || type.equalsIgnoreCase("")){
            pages = esDocumentRepository.findDistinctByTitleContainingOrContentContainingOrDescriptionContaining(keyword,keyword,keyword,pageable);
        }else{
            pages = esDocumentRepository.findDistinctByTypeAndTitleContainingOrContentContainingOrDescriptionContaining(type,keyword,keyword,keyword,pageable);
        }
        return pages;
    }

    @Override
    public Page<ESDocument> getHotestESDocuments(String type,String keyword, Pageable pageable) {
        Page<ESDocument> pages = null;
        Sort sort = new Sort(Sort.Direction.DESC,"createdAt");
        pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
        if(type==null || type.equalsIgnoreCase("")){
            pages = esDocumentRepository.findDistinctByTitleContainingOrContentContainingOrDescriptionContaining(keyword,keyword,keyword,pageable);
        }else{
            pages = esDocumentRepository.findDistinctByTypeAndTitleContainingOrContentContainingOrDescriptionContaining(type,keyword,keyword,keyword,pageable);
        }
        return pages;
    }

    @Override
    public Page<ESDocument> getCustomESDocuments(String type,String keyword, Pageable pageable) {
        Page<ESDocument> pages = null;
       /* Sort sort = new Sort(Sort.Direction.DESC,"readSize","commentSize","voteSize","createdAt");*/
        Sort sort = new Sort(Sort.Direction.DESC,"createdAt");
        pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
        if(type==null || type.equalsIgnoreCase("")){
            pages = esDocumentRepository.findDistinctByTitleContainingOrContentContainingOrDescriptionContaining(keyword,keyword,keyword,pageable);
        }else{
            pages = esDocumentRepository.findDistinctByTypeAndTitleContainingOrContentContainingOrDescriptionContaining(type,keyword,keyword,keyword,pageable);
        }
        return pages;
    }

    @Override
    public Page<ESDocument> getESDocuments(Pageable pageable) {
        return esDocumentRepository.findAll(pageable);
    }
}
