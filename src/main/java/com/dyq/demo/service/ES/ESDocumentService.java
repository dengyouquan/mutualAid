package com.dyq.demo.service.ES;

import com.dyq.demo.domain.ES.ESDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ESDocumentService {
    void removeESDocument(String id);
    ESDocument updateESDocument(ESDocument esDocument);
    ESDocument getESDocumentByDocumentId(Long documentId);
    long count();
    Page<ESDocument> getNewestESDocuments(String type,String keyword, Pageable pageable);
    Page<ESDocument> getHotestESDocuments(String type,String keyword, Pageable pageable);
    Page<ESDocument> getCustomESDocuments(String type,String keyword, Pageable pageable);
    Page<ESDocument> getESDocuments(Pageable pageable);
}
