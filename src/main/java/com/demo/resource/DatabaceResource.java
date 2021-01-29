package com.demo.resource;

import com.codahale.metrics.annotation.Timed;
import com.demo.bean.Saying;
import com.demo.dao.UserDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description TODO
 * @Author liuyang
 * @Date 2021/1/28
 */
@Path("/data")
@Produces(MediaType.APPLICATION_JSON)
public class DatabaceResource {

    private final UserDao userDao;
    private final AtomicInteger counter;

    public DatabaceResource(UserDao userDao) {
        this.userDao = userDao;
        this.counter = new AtomicInteger();
    }

    @Timed
    @GET
    @Path("/create")
    public void create() {
        userDao.createTable();
    }

    @Timed
    @GET
    @Path("/insert")
    public void insert(@QueryParam("content") Optional<String> content) {
        Saying saying = new Saying();
        saying.setId(counter.incrementAndGet());
        saying.setContent(content.orElse("this is content"));
        userDao.insert(saying);
    }

    @Timed
    @GET
    @Path("/query")
    public Saying queryData(@QueryParam("id") Optional<Integer> id) {
        return userDao.queryById(id.orElse(1));
    }


}
