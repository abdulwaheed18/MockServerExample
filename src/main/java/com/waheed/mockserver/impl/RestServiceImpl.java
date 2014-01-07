package com.waheed.mockserver.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.waheed.mockserver.service.IRestService;

/**
 * Implementation of RestService
 * 
 * @author abdul
 * 
 */
@Service
public class RestServiceImpl implements IRestService {

	private static final Logger LOG = LoggerFactory
			.getLogger(RestServiceImpl.class);

	@Autowired
	RestTemplate restTemplate;

	public String hitGoogle() {
		LOG.info("Testing google :");
		String response;
		try {
			response = restTemplate.getForObject("http://google.com",
					String.class);
			LOG.info("Response : {}", response);
		} catch (HttpStatusCodeException e) {
			LOG.error("Error while testing google", e);
			return "FAILED : " + e.getStatusCode();
		} catch (Exception e) {
			LOG.error("Error while testing google", e);
			return "FAILED : " + e.getStackTrace();
		}
		return response;
	}
}
