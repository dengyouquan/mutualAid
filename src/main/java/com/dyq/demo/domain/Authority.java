package com.dyq.demo.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Authority extends BaseModel implements GrantedAuthority {

    @Column(nullable = false) // 映射为字段，值不能为空
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
