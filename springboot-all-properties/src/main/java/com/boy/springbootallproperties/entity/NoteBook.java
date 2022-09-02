package com.boy.springbootallproperties.entity;

import com.boy.springbootallproperties.config.ResponseFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Joshua.H.Brooks
 * @description 类型安全的属性注入， 如果是注入yml文件， 必须知名factory属性值， 而且要在config
 * @date 2022-08-20 13:55
 */
@Component
@ConfigurationProperties(prefix = "notebook")
//@PropertySource(value = "classpath:notebook.properties",encoding = "UTF-8",ignoreResourceNotFound = false)
@PropertySource(value = "classpath:notebook.yml",encoding = "UTF-8",factory = ResponseFactory.class,ignoreResourceNotFound = false)
public class NoteBook {
    private String name;
    private String unit;

    @Override
    public String toString() {
        return "NoteBook{" +
                "name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("给name 设置了值:"+name);
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
        System.out.println("给unit 设置了值:"+unit);
    }
}
