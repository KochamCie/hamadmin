package com.beelego.job;

import com.beelego.github.GitRepoService;
import com.beelego.support.KochamcieJob;
import com.beelego.support.KochamcieJobBean;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * @author : hama
 * @since : created in  2018/9/26
 */
@Slf4j
@Component
@KochamcieJob(name = "CodeRepoInitJob", group = "CodeRepoInitJob", cron = "0/2 * * * * ?", target = CodeRepoInitJob.class, description = "代码资源初始化")
public class CodeRepoInitJob extends KochamcieJobBean {

    @Autowired
    GitRepoService gitRepoService;

    @Override
    protected void executeInternal(JobExecutionContext var1) throws JobExecutionException {
        log.info("CodeRepoInitJob ========= start");
        int pageNo = executePageNo(var1);
        int pageSize = executePageSize(var1);
        gitRepoService.gitCodeInit(PageRequest.of(pageNo-1, pageSize));
        log.info("CodeRepoInitJob ========= end in [{}]", var1.getJobRunTime());

    }
}
