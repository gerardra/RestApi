package au.com.restapi.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties
public class Response {
	
	private List<EpisodeResponseDetails> response = new ArrayList<EpisodeResponseDetails>();

	public List<EpisodeResponseDetails> getResponse() {
		return response;
	}

	public void setResponse(List<EpisodeResponseDetails> response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "Response [response=" + response + "]";
	}
	
	
}
