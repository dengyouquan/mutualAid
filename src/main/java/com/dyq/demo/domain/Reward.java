package com.dyq.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Reward extends BaseModel{
    private String title;
    private String description;
    private String tags;
    private String type;//类型
    private int days;//悬赏天数
    private int money;//赏金
    private String cover;//封面图片地址
    @Lob
    private String content;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="user_id")
    private User rewardUser;
}
