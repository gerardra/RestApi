package au.com.restapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Image {
	
	 private String showImage;

	 public Image() {
		super();
	}

	public String getShowImage() {
	  return showImage;
	 }

	 public void setShowImage(String showImage) {
	  this.showImage = showImage;
	 }
}
