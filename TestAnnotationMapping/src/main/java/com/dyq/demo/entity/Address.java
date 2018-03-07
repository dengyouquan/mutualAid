package com.dyq.demo.entity;

import com.dyq.demo.entity.BaseModel;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(exclude = {"user"})
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseModel {
    //具体地址
    private String address;
    //城市
    private String city;
    //省
    private String province;

    //cascade = {CascadeType.DETACH}
    @ManyToOne
    @JoinColumn(name="u_id")
    private User user;
}
