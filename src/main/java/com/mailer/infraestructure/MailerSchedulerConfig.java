package com.mailer.infraestructure;

import org.jobrunr.configuration.JobRunr;
import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.server.JobActivator;
import org.jobrunr.storage.StorageProvider;
import org.jobrunr.storage.nosql.redis.JedisRedisStorageProvider;
import org.jobrunr.storage.sql.common.SqlStorageProviderFactory;
import org.jobrunr.storage.sql.sqlserver.SQLServerStorageProvider;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MailerSchedulerConfig {

  @Bean
  public JobScheduler initJobRunr(DataSource dataSource, JobActivator jobActivator) {
    return JobRunr.configure()
      .useJobActivator(jobActivator)
      .useStorageProvider(SqlStorageProviderFactory
        .using(dataSource))
      .useBackgroundJobServer()
      .useDashboard()
      .initialize().getJobScheduler();
  }

  /*
  private final DataSource dataSource;

  @Autowired
  public MailerSchedulerConfig(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Bean
  public StorageProvider storageProvider(JobMapper jobMapper) {
    SQLServerStorageProvider storageProvider = new SQLServerStorageProvider(dataSource);
    storageProvider.setJobMapper(jobMapper);
    return storageProvider;
  }
  */

  /* Redis

  @Bean
  public StorageProvider storageProvider(JobMapper jobMapper) {
    JedisRedisStorageProvider storageProvider = new JedisRedisStorageProvider();
    storageProvider.setJobMapper(jobMapper);
    return storageProvider;
  }
  */

}
