package com.dyq.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = {"blogs"})
@NoArgsConstructor
@AllArgsConstructor
public class Catalog extends BaseModel {
    @NotEmpty(message = "名称不能为空")
    @Size(min=2, max=30)
    @Column(nullable = false) // 映射为字段，值不能为空
    private String name;


    @JsonBackReference
    @OneToMany(mappedBy="catalog",cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
    private Set<Blog> blogs = new HashSet<>();
}
