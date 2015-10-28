package com.vimisky.dms.entity;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class AttachmentBase extends IdEntity{
	
	private ContentBase contentBase;
	private URL url;
	private URI uri;
	private String urlString;
	private String uriString;
	private AttachmentFormat attachmentFormat;
	
	/**
	 * @return the contentBase
	 */
	public ContentBase getContentBase() {
		return contentBase;
	}
	/**
	 * @param contentBase the contentBase to set
	 */
	public void setContentBase(ContentBase contentBase) {
		this.contentBase = contentBase;
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
	 * @return the uri
	 */
	public URI getUri() {
		return uri;
	}
	/**
	 * @param uri the uri to set
	 */
	public void setUri(URI uri) {
		this.uri = uri;
	}
	/**
	 * @return the urlString
	 */
	public String getUrlString() {
		return urlString;
	}
	/**
	 * @param urlString the urlString to set,and the url to set
	 */
	public void setUrlString(String urlString){
		this.urlString = urlString;
		if (null == this.url || ! this.url.toString().equals(urlString)) {
			try {
				this.url = new URL(urlString);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * @return the uriString
	 */
	public String getUriString(){
		return uriString;
	}
	/**
	 * @param uriString the uriString to set,and the uri to set
	 */
	public void setUriString(String uriString) {
		this.uriString = uriString;
		if (null == this.uri || ! this.uri.toString().equals(uriString)) {
				try {
					this.uri = new URI(uriString);
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	/**
	 * @return the attachmentFormat
	 */
	public AttachmentFormat getAttachmentFormat() {
		return attachmentFormat;
	}
	/**
	 * @param attachmentFormat the attachmentFormat to set
	 */
	public void setAttachmentFormat(AttachmentFormat attachmentFormat) {
		this.attachmentFormat = attachmentFormat;
	}
	
	
	
}
