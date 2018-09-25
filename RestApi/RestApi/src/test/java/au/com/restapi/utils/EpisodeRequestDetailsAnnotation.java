package au.com.restapi.utils;

public @interface EpisodeRequestDetailsAnnotation {

	JsonElement[] jsonElements();
	
	JsonElement image();
	
	JsonElement[] nextEpisode();
	
	JsonElement[] seasons();
	
}
