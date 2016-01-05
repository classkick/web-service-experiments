package com.classkick.sbj.controllers;

import com.classkick.sbj.domain.TestClass;
import com.classkick.sbj.services.TestService;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

@RestController
@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {

    @Autowired
    private TestService service;

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public @ResponseBody ImmutableMap<String, Object> testMap() {
        return ImmutableMap.of("value", (Object)"hello from springboot", "value2", Lists.newArrayList(5, 2, 3, 1));
    }

    @RequestMapping(value = "/object", method = RequestMethod.GET)
    public @ResponseBody Callable<TestClass> testObject() {

        // Returning a callable is supposed to use non-blocking IO rather than thread-per-request
        return () -> new TestClass("this is value 1", service.getSomething());
    }

    @RequestMapping(value = "/object", method = RequestMethod.POST)
    public @ResponseBody Callable<TestClass> testPost(@RequestBody TestClass input) {

        return () -> {
            service.setSomething(input.getValue2());

            return new TestClass("this is value 1", service.getSomething());
        };
    }

}
