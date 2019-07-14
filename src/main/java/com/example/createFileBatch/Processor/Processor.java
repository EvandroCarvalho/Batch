package com.example.createFileBatch.Processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Processor implements ItemProcessor {


    @Override
    public Object process(Object item) throws Exception {
        log.info("#####PROCESSOR#######");
        return item;
    }
}
