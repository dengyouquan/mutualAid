package com.dyq.demo.entity;

import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document
public class File {
    @Id
    private String id;// 主键
    private String name; // 文件名称
    private String type; // 文件类型
    private long size;//大小
    private Date uploadDate = new Date();//上传时间
    private String md5;//md5码
    private Binary content; // 文件内容

    //重写hascode和equals方法，以便判断是否为同一对象

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, type, size, uploadDate, md5, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        File file = (File) obj;
        return java.util.Objects.equals(size, file.size)
                && java.util.Objects.equals(name, file.name)
                && java.util.Objects.equals(type, file.type)
                && java.util.Objects.equals(uploadDate, file.uploadDate)
                && java.util.Objects.equals(md5, file.md5)
                && java.util.Objects.equals(id, file.id);
    }

    public File(String name, String type, long size, Binary content) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.content = content;
        this.uploadDate = new Date();
    }
}
