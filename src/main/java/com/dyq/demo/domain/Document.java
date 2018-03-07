package com.dyq.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Entity
@Getter
@Setter
@ToString(exclude = {"documentUser","comments","votes","downVotes"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@org.springframework.data.elasticsearch.annotations.Document(indexName = "document", type = "document")
public class Document extends BaseData {
    private String title;
    private String description;
    private String summary;
    private String tags;
    private String type;
    private String cover;//封面图片地址
    @Lob
    private String content;
    private String filepath;//文档地址
    private String downloadpath;//文档下载地址

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="user_id")
    private User documentUser;

    //评论信息
    @OneToMany(mappedBy="commentDocument",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<DocumentComment> comments = new HashSet<>();

    //点赞信息
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "document_vote", joinColumns = @JoinColumn(name = "document_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vote_id", referencedColumnName = "id"))
    private Set<Vote> votes = new HashSet<>();

    //踩信息
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "document_downvote", joinColumns = @JoinColumn(name = "document_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vote_id", referencedColumnName = "id"))
    private Set<DownVote> downVotes = new HashSet<>();

    /**
     * 判断是否点过赞
     * @return 点过赞返回vote 未点过赞返回新建vote
     */
   public Vote isVote(){
       User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       Vote vote = new Vote(user);
       for(Vote v:this.getVotes()){
           //if(v.getUser().getId()==user.getId()){比较不出来
           if(v.getUser().getId().longValue()==user.getId().longValue()){
               System.out.println("赞过");
               return v;
           }
       }
       return vote;
   }

    /**
     * 判断是否点过踩
     * @return 点过踩返回downvote 未点过赞返回新建downvote
     */
    public DownVote isDownVote(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DownVote downVote = new DownVote(user);
        for(DownVote dv:this.getDownVotes()){
            //if(v.getUser().getId()==user.getId()){比较不出来
            if(dv.getUser().getId().longValue()==user.getId().longValue()){
                System.out.println("踩过");
                return dv;
            }
        }
        return downVote;
    }
}
