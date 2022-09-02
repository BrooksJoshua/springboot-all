package com.boy.springbootallthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-22 13:35
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String nickname;

}
