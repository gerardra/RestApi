package au.com.restapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class EpisodeResponseDetails {

	private Image imageObj;
	private String slug;
	private String title;
	private String image;

	
	
	public EpisodeResponseDetails() {
		super();
	}


	public EpisodeResponseDetails(Image imageObj, String slug, String title) {
		super();
		this.imageObj = imageObj;
		this.slug = slug;
		this.title = title;
	}

	public EpisodeResponseDetails(String image, String slug, String title) {
		super();
		this.image = image;
		this.slug = slug;
		this.title = title;
	}
	
	public String getSlug() {
		return slug;
 	}

	public String getTitle() {
		return title;
	}
	
	public String getImage() {
		return imageObj != null ? imageObj.getShowImage() : image;
	}

	
	
}
