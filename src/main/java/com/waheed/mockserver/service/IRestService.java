package com.waheed.mockserver.service;

/**
 * An interface for exposing Rest Service functionality.
 * 
 * @author abdul
 * 
 */
public interface IRestService {

	/**
	 * REST client that makes a call to a URL and retrun response in String
	 * format.
	 * 
	 * @return response of url
	 */
	public String hitGoogle();

}
