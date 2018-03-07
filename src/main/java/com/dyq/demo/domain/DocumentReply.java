package com.dyq.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@ToString(exclude = {"documentComment"},callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class DocumentReply extends BaseComment {
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="comment_id")
    private DocumentComment documentComment;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="from_user_id")
    private User documentReplyUser;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="to_user_id")
    private User toDocumentReplyUser;
}
