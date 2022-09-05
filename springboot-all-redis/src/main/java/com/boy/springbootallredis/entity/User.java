package com.boy.springbootallredis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-09-05 13:12
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    String name;
    String address;
}
