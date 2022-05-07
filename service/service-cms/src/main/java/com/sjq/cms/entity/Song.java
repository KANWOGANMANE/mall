package com.sjq.cms.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Author Kemp
 * @create 2022/5/4 17:02
 * elasticsearch测试使用类
 */
@Document(indexName = "music",type = "_doc")
public class Song {
    @Id
    private Integer id;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String name;
    @Field(type = FieldType.Keyword)
    private String songName;
    @Field(type = FieldType.Integer)
    private Integer year;
}
