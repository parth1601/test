package com.example;

import io.quarkus.scheduler.Scheduled;
import com.example.service.ExampleService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ExampleResource {

    @Inject
    ExampleService exampleService;

    @Scheduled(every = "{cron-duration}")
    void fetchData() {
        exampleService.readMails();
    }
}