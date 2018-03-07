package com.dyq.demo.domain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class BaseData extends BaseModel {
    private int voteSize;//点赞量
    private int downVoteSize;//踩量
    private int commentSize;//评论量
    private int readSize;//浏览量
}
