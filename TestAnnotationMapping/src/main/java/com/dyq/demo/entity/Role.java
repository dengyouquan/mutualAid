package com.dyq.demo.entity;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(exclude = {"user"})
@Entity
public class Role extends BaseModel {
    private String name;
}
