package au.com.restapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Season {
	
	
	@JsonIgnoreProperties
	private String slug;

	
	public Season() {
		super();
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}
	
	
}
