package com.iware.lottery.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by johnma on 2016/11/2.
 */@Configuration
@EnableJpaRepositories(basePackages = {"com.iware.lottery.admin"})
public class DataJpaConfig {


}