package com.dyq.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(callSuper = true,exclude = {"documentCommentUser","dcvotes","dcdownVotes"})
@NoArgsConstructor
@AllArgsConstructor
public class DocumentComment extends BaseComment {
    //资料回复信息
    @OneToMany(mappedBy="documentComment",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<DocumentReply> documentReplys = new HashSet<>();

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="document_id")
    private Document commentDocument;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="user_id")
    private User documentCommentUser;

    //点赞信息
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "document_comment_vote", joinColumns = @JoinColumn(name = "document_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vote_id", referencedColumnName = "id"))
    private Set<DCVote> dcvotes = new HashSet<>();

    //踩信息
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "document_comment_downvote", joinColumns = @JoinColumn(name = "document_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vote_id", referencedColumnName = "id"))
    private Set<DCDownVote> dcdownVotes = new HashSet<>();

    /**
     * 判断是否点过赞
     * @return 点过赞返回vote 未点过赞返回新建vote
     */
    public DCVote isVote(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DCVote vote = new DCVote(user);
        for(DCVote v:this.getDcvotes()){
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
    public DCDownVote isDownVote(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DCDownVote downVote = new DCDownVote(user);
        for(DCDownVote dv:this.getDcdownVotes()){
            //if(v.getUser().getId()==user.getId()){比较不出来
            if(dv.getUser().getId().longValue()==user.getId().longValue()){
                System.out.println("踩过");
                return dv;
            }
        }
        return downVote;
    }
}
