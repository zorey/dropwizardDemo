package com.demo.resource;

import com.codahale.metrics.annotation.Timed;
import com.demo.bean.Saying;
import com.demo.config.HelloConfiguration;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource {

    private final HelloConfiguration configuration;
    private final AtomicInteger counter;

    public HelloResource(HelloConfiguration configuration) {
        this.configuration = configuration;
        this.counter = new AtomicInteger();
    }

    @Timed
    @GET
    @Path("/hello")
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        Saying saying = new Saying();
        saying.setContent(configuration.render(name));
        saying.setId(counter.incrementAndGet());
        return saying;
    }
}
