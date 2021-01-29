package com.demo.dao;

import com.demo.bean.Saying;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface UserDao {

    @RegisterBeanMapper(Saying.class)
    @SqlQuery("select * from saying where id = :id")
    Saying queryById(@Bind("id") Integer id);

    @SqlUpdate("create table saying (id int primary key, content varchar(100))")
    void createTable();

    @SqlUpdate("insert into saying(id, content) values(:id, :content)")
    void insert(@BindBean Saying saying);
}
