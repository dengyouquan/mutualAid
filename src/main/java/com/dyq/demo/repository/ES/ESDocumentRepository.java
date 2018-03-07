package com.dyq.demo.repository.ES;

import com.dyq.demo.domain.ES.ESDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESDocumentRepository extends ElasticsearchRepository<ESDocument, String> {
    //ESDocument findByDocumentId(Long documentId);
    ESDocument findByEsdocumentId(Long esdocumentId);
    Page<ESDocument> findDistinctByTitleContainingOrContentContainingOrDescriptionContaining(String title,String content,String description,Pageable pageable);
    Page<ESDocument> findDistinctByTypeAndTitleContainingOrContentContainingOrDescriptionContaining(String type,String title,String content,String description,Pageable pageable);
    //int countAll();
}
