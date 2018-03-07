package com.dyq.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = {"comments","votes"})
@NoArgsConstructor
@AllArgsConstructor
public class Blog extends BaseData {
    private String title;
    private String description;
    private String summary;
    private String type;//博客类型
    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User blogUser;


    //评论信息
    @OneToMany(mappedBy="commentBlog",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<BlogComment> comments = new HashSet<>();

    //分类信息
    @ManyToOne
    @JoinColumn(name="blog_id")
    private Catalog catalog;

    //点赞信息
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "blog_vote", joinColumns = @JoinColumn(name = "blog_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vote_id", referencedColumnName = "id"))
    private Set<Vote> votes = new HashSet<>();

    //标签信息
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "join_blog_tag", joinColumns = @JoinColumn(name = "blog_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<BlogTag> blogTags = new HashSet<>();
}
