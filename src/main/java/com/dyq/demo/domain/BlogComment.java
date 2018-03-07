package com.dyq.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class BlogComment extends BaseComment {
    //资料回复信息
    @OneToMany(mappedBy="blogComment",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<BlogReply> blogReplys = new HashSet<>();

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="blog_id")
    private Blog commentBlog;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="user_id")
    private User blogCommentUser;
}
