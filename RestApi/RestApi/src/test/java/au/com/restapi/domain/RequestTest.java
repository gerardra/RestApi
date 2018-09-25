package au.com.restapi.domain;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import au.com.restapi.utils.EpisodeRequestDetailsAnnotation;
import au.com.restapi.utils.JsonDataType;
import au.com.restapi.utils.JsonElement;
import au.com.restapi.utils.RequestAnnotation;
import au.com.restapi.utils.TestUtils;

public class RequestTest {

	private static Logger logger = LoggerFactory.getLogger(RequestTest.class);
	
	private ObjectMapper mapper ;
	
    @Before
    public void setUp() throws Exception{
        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    }
    
    @After
    public void tearDown() throws Exception{
        mapper = null;
    }

	@Test
	@RequestAnnotation(payload = 
		@EpisodeRequestDetailsAnnotation(
			jsonElements = { 
					@JsonElement(name = "country", value = "UK"),
					@JsonElement(name = "description", value = "What's life like when you have enough children to field your own football team?"),
					@JsonElement(name = "drm", value = "true", dataType = JsonDataType.BOOLEAN),
					@JsonElement(name = "episodeCount", value = "3", dataType = JsonDataType.INTEGER),
					@JsonElement(name = "genre", value = "genre"),
					@JsonElement(name = "language", value = "English"),
					@JsonElement(name = "primaryColour", value = "#ff7800"),
					@JsonElement(name = "slug", value = "show/16kidsandcounting"),
					@JsonElement(name = "title", value = "null"),
					@JsonElement(name = "tvChannel", value = "GEM")
			}, 
			image = @JsonElement(name = "showImage", value = "null"), 
			nextEpisode = { 
					@JsonElement(name = "channel", value = "null"),
					@JsonElement(name = "channelLogo", value = "http://mybeautifulcatchupservice.com/img/player/logo_go.gif"),
					@JsonElement(name = "date", value = "20-11-2000 12:00", dataType = JsonDataType.DATE),
					@JsonElement(name = "html", value = "ee"),
					@JsonElement(name = "url", value = "http://go.ninemsn.com.au/")
			}, 
			seasons = { 
					@JsonElement(name = "slug", value = "show/thunderbirds/season/1"),
					@JsonElement(name = "slug", value = "show/thunderbirds/season/2"),
					@JsonElement(name = "slug", value = "show/thunderbirds/season/3")
			})
	)
	public void RequestWithValidValues() {
		try {
			String generateJson = TestUtils.generateRequestJson("RequestWithValidValues", this.getClass());
			logger.info(generateJson);
			Request request = mapper.readValue(generateJson, Request.class);
			Assert.assertEquals(1, request.getPayload().size());
			Assert.assertEquals(3, request.getPayload().get(0).getSeasons().size());
			Assert.assertEquals("20-11-2000 12:00", request.getPayload().get(0).getNextEpisode().getDate());
		} catch (Exception e) {
			logger.error(e.getMessage());
			fail();
		} 
	}


	@Test
	@RequestAnnotation(payload = 
		@EpisodeRequestDetailsAnnotation(
			jsonElements = { 
					@JsonElement(name = "country", value = "UK"),
					@JsonElement(name = "description", value = "What's life like when you have enough children to field your own football team?"),
					@JsonElement(name = "drm", value = "true", dataType = JsonDataType.BOOLEAN),
					@JsonElement(name = "episodeCount", value = "3", dataType = JsonDataType.INTEGER),
					@JsonElement(name = "genre", value = "genre"),
					@JsonElement(name = "language", value = "English"),
					@JsonElement(name = "primaryColour", value = "#ff7800"),
					@JsonElement(name = "slug", value = "show/16kidsandcounting"),
					@JsonElement(name = "title", value = "null"),
					@JsonElement(name = "tvChannel", value = "GEM")
			}, 
			image = @JsonElement(), 
			nextEpisode = { 
					@JsonElement()
			}, 
			seasons = { 
					@JsonElement(name = "slug", value = "show/thunderbirds/season/1"),
					@JsonElement(name = "slug", value = "show/thunderbirds/season/2"),
					@JsonElement(name = "slug", value = "show/thunderbirds/season/3")
			})
	)
	public void RequestWithoutNextEpisodeAndImage() {
		try {
			String generateJson = TestUtils.generateRequestJson("RequestWithoutNextEpisodeAndImage", this.getClass());
			logger.info(generateJson);
			Request request = mapper.readValue(generateJson, Request.class);
			Assert.assertEquals(1, request.getPayload().size());
			Assert.assertEquals(3, request.getPayload().get(0).getSeasons().size());
			Assert.assertNull(request.getPayload().get(0).getNextEpisode());
			Assert.assertNull(request.getPayload().get(0).getImage());
			Assert.assertTrue(request.getPayload().get(0).getSeasons().size() == 3);
		} catch (Exception e) {
			logger.error(e.getMessage());
			fail();
		} 
	}
	
	
	@Test
	@RequestAnnotation(payload = {
		@EpisodeRequestDetailsAnnotation(
			jsonElements = { 
					@JsonElement(name = "country", value = "UK"),
					@JsonElement(name = "description", value = "What's life like when you have enough children to field your own football team?"),
					@JsonElement(name = "drm", value = "true", dataType = JsonDataType.BOOLEAN),
					@JsonElement(name = "episodeCount", value = "3", dataType = JsonDataType.INTEGER),
					@JsonElement(name = "genre", value = "genre"),
					@JsonElement(name = "language", value = "English"),
					@JsonElement(name = "primaryColour", value = "#ff7800"),
					@JsonElement(name = "slug", value = "show/16kidsandcounting"),
					@JsonElement(name = "title", value = "null"),
					@JsonElement(name = "tvChannel", value = "GEM")
			}, image = @JsonElement(name = ""), nextEpisode = { @JsonElement(name = "") }, seasons = { @JsonElement(name = "") }),
		@EpisodeRequestDetailsAnnotation(
				jsonElements = { 
						@JsonElement(name = "country", value = "UK"),
						@JsonElement(name = "description", value = "What's life like when you have enough children to field your own football team?"),
						@JsonElement(name = "drm", value = "true", dataType = JsonDataType.BOOLEAN),
						@JsonElement(name = "episodeCount", value = "3", dataType = JsonDataType.INTEGER),
						@JsonElement(name = "genre", value = "genre"),
						@JsonElement(name = "language", value = "English"),
						@JsonElement(name = "primaryColour", value = "#ff7800"),
						@JsonElement(name = "slug", value = "show/16kidsandcounting"),
						@JsonElement(name = "title", value = "null"),
						@JsonElement(name = "tvChannel", value = "GEM")
				}, image = @JsonElement(name = ""), nextEpisode = { @JsonElement(name = "") }, seasons = { @JsonElement(name = "") })
	})
	public void RequestWithoutNextEpisodeSeasonsAndImage() {
		try {
			String generateJson = TestUtils.generateRequestJson("RequestWithoutNextEpisodeSeasonsAndImage", this.getClass());
			logger.info(generateJson);
			Request request = mapper.readValue(generateJson, Request.class);
			Assert.assertEquals(2, request.getPayload().size());
			Assert.assertEquals(3, request.getPayload().get(0).getEpisodeCount());
			Assert.assertEquals(true, request.getPayload().get(0).isDrm());
		} catch (Exception e) {
			logger.error(e.getMessage());
			fail();
		} 
	}
}
