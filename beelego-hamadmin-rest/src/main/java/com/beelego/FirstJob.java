package com.beelego;

import com.beelego.support.KochamcieJob;
import com.beelego.support.KochamcieJobBean;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * @author : hama
 * @since : created in  2018/9/24
 */
@Component
@KochamcieJob(name = "FirstJob", group = "FirstJob", cron = "0/2 * * * * ?", target = FirstJob.class, description = "66666")
public class FirstJob extends KochamcieJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        int pageNo = 6;
        System.out.println(pageNo + "========================");
    }
}
