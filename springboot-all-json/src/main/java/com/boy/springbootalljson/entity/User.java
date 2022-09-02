package com.boy.springbootalljson.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-24 11:38
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"address","grade"})
public class User {
    @JsonIgnore
    private String id;
    @JsonProperty(index = 10,value = "nickname")
    private String name;
    //@JsonFormat(pattern = "yyyy/MM//dd HH:mm:ss.SSS",timezone = "Asia/Shanghai") 这里优先级比全局配置的高
    private Date birthday;
    private String age;
    private String address;
    private String grade;
    private String slogan;
}
