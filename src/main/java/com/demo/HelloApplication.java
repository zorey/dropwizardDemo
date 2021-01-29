package com.demo;

import com.demo.config.HelloConfiguration;
import com.demo.dao.UserDao;
import com.demo.resource.DatabaceResource;
import com.demo.resource.HelloResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

public class HelloApplication extends Application<HelloConfiguration> {
    @Override
    public void run(HelloConfiguration config, Environment environment) throws Exception {
        //hello resource注册
        environment.jersey().register(new HelloResource(config));
        //jdbi配置
        final JdbiFactory jdbiFactory = new JdbiFactory();
        final Jdbi jdbi = jdbiFactory.build(environment, config.getDatabase(), "mysql");
        final UserDao userDao = jdbi.onDemand(UserDao.class);
        //data resource注册
        environment.jersey().register(new DatabaceResource(userDao));
    }

    public static void main(String[] args) throws Exception {
        new HelloApplication().run(args);
    }
}
