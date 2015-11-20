package com.pingan.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExampleScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleScheduler.class);

    public void execute() {
        LOGGER.info("ExampleQuartz.execute");
    }
}
