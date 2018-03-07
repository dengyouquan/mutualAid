package com.dyq.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString(callSuper = true)
@MappedSuperclass
public abstract class BaseComment extends BaseData {
    @NotEmpty(message = "评论内容不能为空")
    @Size(min=2, max=500)
    @Column(nullable = false) // 映射为字段，值不能为空
    private String content;
    //状态 审核
    private int status;
    private String fromAvatar;//缓存头像
    private String fromName;//缓存名字
}
