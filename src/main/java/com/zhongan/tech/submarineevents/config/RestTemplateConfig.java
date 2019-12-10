/*
 * Copyright 2019 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.zhongan.tech.submarineevents.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

  @Bean
  public RestTemplate restTemplate(){
    return new RestTemplate(clientHttpRequestFactory());
  }

  @Bean
  public ClientHttpRequestFactory clientHttpRequestFactory(){
	  SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
	  factory.setReadTimeout(20000);
	  factory.setConnectTimeout(20000);
	  return factory;
  }
}
