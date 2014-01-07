package com.waheed.mockserver;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.RequestMatchers.method;
import static org.springframework.test.web.client.match.RequestMatchers.requestTo;
import static org.springframework.test.web.client.response.ResponseCreators.withServerError;
import static org.springframework.test.web.client.response.ResponseCreators.withStatus;
import static org.springframework.test.web.client.response.ResponseCreators.withSuccess;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.waheed.mockserver.controller.RestController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:WebContent/WEB-INF/spring-servlet.xml")
public class TestRestController {

	private static final Logger LOG = LoggerFactory
			.getLogger(TestRestController.class);

	@Autowired
	private RestTemplate restTemplate;

	private MockRestServiceServer mockServer;

	@Autowired
	RestController restController;

	// Execute the Setup method before the test.
	@Before
	public void setUp() {
		// create a mock Server instance for RestTemplate
		mockServer = MockRestServiceServer.createServer(restTemplate);
	}

	// testCheckGoogle_success() verifies our URL, GET HttpMethod, and returns a
	// 200 Success with a text message of "Success".
	@Test
	public void testCheckGoogle_success() {
		LOG.info("Testing Google Success");

		mockServer.expect(requestTo("http://google.com"))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("SUCCESS", MediaType.TEXT_PLAIN));

		String response = restController.checkGoogle();
		mockServer.verify();
		assertEquals("SUCCESS", response);

	}

	@Test
	public void testCheckGoogle_failure_notAcceptable() {
		LOG.info("Testing Google failure not acceptable");
		mockServer.expect(requestTo("http://google.com"))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withStatus(HttpStatus.NOT_ACCEPTABLE));

		restController.checkGoogle();
		mockServer.verify();

	}

	@Test
	public void testCheckGoogle_failure_serverError() {
		LOG.info("Testing Google failure server error");
		mockServer.expect(requestTo("http://google.com"))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withServerError());

		restController.checkGoogle();
		mockServer.verify();

	}
}
