package com.eztech.springbase.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 同步任务
 *
 * @author chenqinru
 * @date 2023/09/01
 */
@Component
@Slf4j
public class SyncTask {

    @Scheduled(cron = "0/5 * * * * ?")
    public void handle() {
        log.info("do sync task ...");
    }
}
