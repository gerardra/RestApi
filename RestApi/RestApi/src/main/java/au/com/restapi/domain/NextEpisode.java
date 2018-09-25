package au.com.restapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class NextEpisode {
	 
	 private String channel;
	 
	 private String channelLogo;
	
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	 private String date;
	 
	 private String html;
	
	 private String url;

	 
	 public NextEpisode() {
		super();
	}

	public String getChannel() {
	  return channel;
	 }

	 public String getChannelLogo() {
	  return channelLogo;
	 }

	 public String getDate() {
	  return date;
	 }

	 public String getHtml() {
	  return html;
	 }

	 public String getUrl() {
	  return url;
	 }

	 public void setChannel(String channel) {
	  this.channel = channel;
	 }

	 public void setChannelLogo(String channelLogo) {
	  this.channelLogo = channelLogo;
	 }

	 public void setDate(String date) {
	  this.date = date;
	 }

	 public void setHtml(String html) {
	  this.html = html;
	 }

	 public void setUrl(String url) {
	  this.url = url;
	 }

	
	 
	 
}
