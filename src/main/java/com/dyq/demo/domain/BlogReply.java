package com.dyq.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(exclude = {"blogComment"})
@NoArgsConstructor
@AllArgsConstructor
public class BlogReply extends BaseComment {
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="comment_id")
    private BlogComment blogComment;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="from_user_id")
    private User blogReplyUser;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="to_user_id")
    private User toBlogReplyUser;
}
