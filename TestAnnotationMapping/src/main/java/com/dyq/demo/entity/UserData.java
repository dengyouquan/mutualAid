package com.dyq.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(exclude = {"user"})
@NoArgsConstructor
@AllArgsConstructor
public class UserData extends BaseModel {
    private int integral = 0; // 积分
    private int praise;//点赞量
    private int comment;//评论量
    private int browse;//浏览量
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH})
    //@OneToOne
    @JoinColumn(name = "u_id")
    private User user;
}
