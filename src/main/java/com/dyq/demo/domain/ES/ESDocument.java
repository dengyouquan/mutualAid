package com.dyq.demo.domain.ES;

import com.dyq.demo.domain.Document;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@org.springframework.data.elasticsearch.annotations.Document(indexName = "document", type = "document")
public class ESDocument implements Serializable {
    @Id  // 主键
    private String id;
    @Field(index = FieldIndex.not_analyzed)
    private Long esdocumentId;
    @Field(index = FieldIndex.not_analyzed)  // 不做全文检索字段
    private Date createdAt;
    @Field(index = FieldIndex.not_analyzed)  // 不做全文检索字段
    private Date updatedAt;
    private String title;
    private String description;
    private String summary;
    @Field(index = FieldIndex.not_analyzed)  // 不做全文检索字段
    private String tags;
    @Field(index = FieldIndex.not_analyzed)  // 不做全文检索字段
    private String type;
    @Field(index = FieldIndex.not_analyzed)  // 不做全文检索字段
    private String cover;//封面图片地址
    private String content;
    @Field(index = FieldIndex.not_analyzed)  // 不做全文检索字段
    private String filepath;//文档地址
    @Field(index = FieldIndex.not_analyzed)  // 不做全文检索字段
    private String downloadpath;//文档下载地址

    @Field(index = FieldIndex.not_analyzed)  // 不做全文检索字段
    private int voteSize;//点赞量
    @Field(index = FieldIndex.not_analyzed)  // 不做全文检索字段
    private int downVoteSize;//踩量
    @Field(index = FieldIndex.not_analyzed)  // 不做全文检索字段
    private int commentSize;//评论量
    @Field(index = FieldIndex.not_analyzed)  // 不做全文检索字段
    private int readSize;//浏览量

    public ESDocument(Document document){
        this.esdocumentId = document.getId();
        this.title = document.getTitle();
        this.summary = document.getSummary();
        this.content = document.getContent();
        this.cover = document.getCover();
        this.description = document.getDescription();
        this.filepath = document.getFilepath();
        this.downloadpath = document.getDownloadpath();
        this.tags = document.getTags();
        this.type = document.getType();
        this.createdAt = document.getCreatedAt();
        this.updatedAt = document.getUpdatedAt();
        this.readSize = document.getReadSize();
        this.commentSize = document.getCommentSize();
        this.downVoteSize = document.getDownVoteSize();
        this.voteSize = document.getVoteSize();
    }

    public void update(Document document){
        this.esdocumentId = document.getId();
        this.title = document.getTitle();
        this.summary = document.getSummary();
        this.content = document.getContent();
        this.cover = document.getCover();
        this.description = document.getDescription();
        this.filepath = document.getFilepath();
        this.downloadpath = document.getDownloadpath();
        this.tags = document.getTags();
        this.type = document.getType();
        this.createdAt = document.getCreatedAt();
        this.updatedAt = document.getUpdatedAt();
        this.readSize = document.getReadSize();
        this.commentSize = document.getCommentSize();
        this.downVoteSize = document.getDownVoteSize();
        this.voteSize = document.getVoteSize();
    }
}
