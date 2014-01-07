package com.waheed.mockserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.waheed.mockserver.service.IRestService;

/**
 * REST controller which handles all the REST related calls.
 * 
 * @author abdul
 * 
 */
@Controller
@RequestMapping("/test")
public class RestController {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(RestController.class);
	
	@Autowired
	RestTemplate template;
	
	@Autowired
	IRestService iRestService;
	
	@RequestMapping(value = "/google", method = RequestMethod.GET)
	@ResponseBody
	public String checkGoogle() {
		LOG.info("Notification : hit google command received from REST");
		return iRestService.hitGoogle();
	}
}
