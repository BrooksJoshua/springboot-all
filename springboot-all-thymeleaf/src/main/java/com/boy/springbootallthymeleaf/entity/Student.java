package com.boy.springbootallthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-23 22:10
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    String id;
    String uname;
    String addr;


}
