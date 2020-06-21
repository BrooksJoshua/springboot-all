package com.boy.springbootrest.dao;

import com.boy.springbootrest.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


@RepositoryRestResource(path="function",collectionResourceRel = "bs",itemResourceRel="b_item" )
public interface BookDao extends JpaRepository<Book,Integer> { //泛型参数: 前者为实体类, 后者为表的主键类型

    @RestResource(exported=true, path="method",rel="displayedRel")
    List<Book> findBookByNameContainingAndIdGreaterThan(@Param("name") String name, @Param("id") int id);


}
