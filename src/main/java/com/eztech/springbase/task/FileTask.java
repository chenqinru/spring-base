package com.eztech.springbase.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * FileTask
 *
 * @author chenqinru
 * @date 2023/09/01
 */
@Component
@Slf4j
public class FileTask {

    @Scheduled(cron = "0/5 * * * * ?")
    public void handle() {
        log.info("do file task ...");
    }
}
