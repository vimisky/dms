package com.vimisky.crawler.datamodel;

import java.io.Serializable;
import java.net.URL;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CrawlURL implements Serializable, BDBDataModelInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2187060805218120820L;

	/**URL to crawl**/
	private URL url;

    // Processing progress
    private int fetchStatus = 0;    // default to unattempted
    private int fetchAttempts = 0; // the number of fetch attempts that have been made

    /**
     * 可变性较大的数据
     * **/
    private Map<String, Object> data;
    /**
     * Takes a status code and converts it into a human readable string.
     *
     * @param code the status code
     * @return a human readable string declaring what the status code is.
     */
    public static String fetchStatusCodesToString(int code){
        switch(code){
            // HTTP Informational 1xx
            case 100  : return "HTTP-100-Info-Continue";
            case 101  : return "HTTP-101-Info-Switching Protocols";
            // HTTP Successful 2xx
            case 200  : return "HTTP-200-Success-OK";
            case 201  : return "HTTP-201-Success-Created";
            case 202  : return "HTTP-202-Success-Accepted";
            case 203  : return "HTTP-203-Success-Non-Authoritative";
            case 204  : return "HTTP-204-Success-No Content ";
            case 205  : return "HTTP-205-Success-Reset Content";
            case 206  : return "HTTP-206-Success-Partial Content";
            // HTTP Redirection 3xx
            case 300  : return "HTTP-300-Redirect-Multiple Choices";
            case 301  : return "HTTP-301-Redirect-Moved Permanently";
            case 302  : return "HTTP-302-Redirect-Found";
            case 303  : return "HTTP-303-Redirect-See Other";
            case 304  : return "HTTP-304-Redirect-Not Modified";
            case 305  : return "HTTP-305-Redirect-Use Proxy";
            case 307  : return "HTTP-307-Redirect-Temporary Redirect";
            // HTTP Client Error 4xx
            case 400  : return "HTTP-400-ClientErr-Bad Request";
            case 401  : return "HTTP-401-ClientErr-Unauthorized";
            case 402  : return "HTTP-402-ClientErr-Payment Required";
            case 403  : return "HTTP-403-ClientErr-Forbidden";
            case 404  : return "HTTP-404-ClientErr-Not Found";
            case 405  : return "HTTP-405-ClientErr-Method Not Allowed";
            case 407  : return "HTTP-406-ClientErr-Not Acceptable";
            case 408  : return "HTTP-407-ClientErr-Proxy Authentication Required";
            case 409  : return "HTTP-408-ClientErr-Request Timeout";
            case 410  : return "HTTP-409-ClientErr-Conflict";
            case 406  : return "HTTP-410-ClientErr-Gone";
            case 411  : return "HTTP-411-ClientErr-Length Required";
            case 412  : return "HTTP-412-ClientErr-Precondition Failed";
            case 413  : return "HTTP-413-ClientErr-Request Entity Too Large";
            case 414  : return "HTTP-414-ClientErr-Request-URI Too Long";
            case 415  : return "HTTP-415-ClientErr-Unsupported Media Type";
            case 416  : return "HTTP-416-ClientErr-Requested Range Not Satisfiable";
            case 417  : return "HTTP-417-ClientErr-Expectation Failed";
            // HTTP Server Error 5xx
            case 500  : return "HTTP-500-ServerErr-Internal Server Error";
            case 501  : return "HTTP-501-ServerErr-Not Implemented";
            case 502  : return "HTTP-502-ServerErr-Bad Gateway";
            case 503  : return "HTTP-503-ServerErr-Service Unavailable";
            case 504  : return "HTTP-504-ServerErr-Gateway Timeout";
            case 505  : return "HTTP-505-ServerErr-HTTP Version Not Supported";
            // Unknown return code
            default : return Integer.toString(code);
        }
    }
	/**
	 * @return the url
	 */
	public URL getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(URL url) {
		this.url = url;
	}
	/**
	 * @return the fetchStatus
	 */
	public int getFetchStatus() {
		return fetchStatus;
	}
	/**
	 * @param fetchStatus the fetchStatus to set
	 */
	public void setFetchStatus(int fetchStatus) {
		this.fetchStatus = fetchStatus;
	}
	/**
	 * @return the fetchAttempts
	 */
	public int getFetchAttempts() {
		return fetchAttempts;
	}
	/**
	 * @param fetchAttempts the fetchAttempts to set
	 */
	public void setFetchAttempts(int fetchAttempts) {
		this.fetchAttempts = fetchAttempts;
	}
	/**
	 * @return the data
	 */
	public Map<String, Object> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	@JsonIgnore
	public String getKeyString(){
		
		if (getUrl() != null) {
			return getUrl().toString();
		}else {
			return null;
		}
		
	}
    
}
