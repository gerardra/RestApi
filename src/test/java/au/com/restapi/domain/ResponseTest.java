package au.com.restapi.domain;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import au.com.restapi.utils.JsonElement;
import au.com.restapi.utils.ResponseElement;
import au.com.restapi.utils.ResponseEpisodeElement;
import au.com.restapi.utils.TestUtils;

public class ResponseTest {

	private static Logger logger = LoggerFactory.getLogger(ResponseTest.class);

	private ObjectMapper mapper ;
	
    @Before
    public void setUp() throws Exception{
        mapper = new ObjectMapper();
    }
    
    @After
    public void tearDown() throws Exception{
        mapper = null;
    }
    
	@Test
	@ResponseElement (response = { 
		@ResponseEpisodeElement(
			jsonElements = {
					@JsonElement(name = "image", value = "http://mybeautifulcatchupservice.com/img/shows/16KidsandCounting1280.jpg"),
					@JsonElement(name = "slug", value = "show/16kidsandcounting"),
					@JsonElement(name = "title", value = "16 Kids and Counting")
			}), 
		@ResponseEpisodeElement(
				jsonElements = {
						@JsonElement(name = "image", value = "http://mybeautifulcatchupservice.com/img/shows/TheTaste1280.jpg"),
						@JsonElement(name = "slug", value = "show/thetaste"),
						@JsonElement(name = "title", value = "The Taste")
				}),
		@ResponseEpisodeElement(
				jsonElements = {
						@JsonElement(name = "image", value = "http://mybeautifulcatchupservice.com/img/shows/Thunderbirds_1280.jpg"),
						@JsonElement(name = "slug", value = "show/thunderbirds"),
						@JsonElement(name = "title", value = "Thunderbirds")
				})
		})

	public void responseWithAllValidValues() {
		try {
			String generateJson = TestUtils.generateResponseJson("responseWithAllValidValues", this.getClass());
			Response response = mapper.readValue(generateJson, Response.class);
			Assert.assertNotNull(response.getResponse());
			Assert.assertEquals(3, response.getResponse().size());
			EpisodeResponseDetails episodeResponseDetails = response.getResponse().get(0);
			Assert.assertTrue("http://mybeautifulcatchupservice.com/img/shows/16KidsandCounting1280.jpg".equals(episodeResponseDetails.getImage()));
			Assert.assertTrue("show/16kidsandcounting".equals(episodeResponseDetails.getSlug()));
			Assert.assertTrue("16 Kids and Counting".equals(episodeResponseDetails.getTitle()));
		} catch (Exception e) {
			logger.error("Exception occured in junit", e);
			fail();
		}
	}
	
	@Test
	@ResponseElement (response = { 
		@ResponseEpisodeElement(
			jsonElements = {
					@JsonElement(name = "image", value = "http://mybeautifulcatchupservice.com/img/shows/16KidsandCounting1280.jpg"),
					@JsonElement(name = "slug", value = "###tt%%**(())??>>??//}{&^%$#@!"),
					@JsonElement(name = "title", value = "<br><span class= visit>Visit the Official Website</span></span>")
			})
		})

	public void responseWithSpecialCharacterInSlug() {
		try {
			String generateJson = TestUtils.generateResponseJson("responseWithSpecialCharacterInSlug", this.getClass());
			ObjectMapper mapper = new ObjectMapper();
			Response response = mapper.readValue(generateJson, Response.class);
			Assert.assertNotNull(response.getResponse());
			Assert.assertEquals(1, response.getResponse().size());
			EpisodeResponseDetails episodeResponseDetails = response.getResponse().get(0);
			Assert.assertTrue("http://mybeautifulcatchupservice.com/img/shows/16KidsandCounting1280.jpg".equals(episodeResponseDetails.getImage()));
			Assert.assertTrue("###tt%%**(())??>>??//}{&^%$#@!".equals(episodeResponseDetails.getSlug()));
			Assert.assertTrue("<br><span class= visit>Visit the Official Website</span></span>".equals(episodeResponseDetails.getTitle()));
		} catch (Exception e) {
			logger.error("Exception occured in junit", e);
			fail();
		}
	}
	
	@Test
	@ResponseElement (response = { 
		@ResponseEpisodeElement(
			jsonElements = {
					@JsonElement(name = "image", value = "http://mybeautifulcatchupservice.com/img/shows/16KidsandCounting1280.jpg"),
					@JsonElement(name = "slug", value = "show/16kidsandcounting")
			})
		})

	public void responseWithMissingTitleElement() {
		try {
			String generateJson = TestUtils.generateResponseJson("responseWithMissingTitleElement", this.getClass());
			ObjectMapper mapper = new ObjectMapper();
			Response response = mapper.readValue(generateJson, Response.class);
			Assert.assertNotNull(response.getResponse());
			Assert.assertEquals(1, response.getResponse().size());
			EpisodeResponseDetails episodeResponseDetails = response.getResponse().get(0);
			Assert.assertTrue("http://mybeautifulcatchupservice.com/img/shows/16KidsandCounting1280.jpg".equals(episodeResponseDetails.getImage()));
			Assert.assertTrue("show/16kidsandcounting".equals(episodeResponseDetails.getSlug()));
			Assert.assertTrue(StringUtils.isEmpty(episodeResponseDetails.getTitle()));
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	@ResponseElement (response = { 
		@ResponseEpisodeElement(
			jsonElements = {
					@JsonElement(name = "image", value = "http://mybeautifulcatchupservice.com/img/shows/16KidsandCounting1280.jpg")
			})
		})

	public void responseWithMissingTitleAndSlugElements() {
		try {
			String generateJson = TestUtils.generateResponseJson("responseWithMissingTitleAndSlugElements", this.getClass());
			ObjectMapper mapper = new ObjectMapper();
			Response response = mapper.readValue(generateJson, Response.class);
			Assert.assertNotNull(response.getResponse());
			Assert.assertEquals(1, response.getResponse().size());
			EpisodeResponseDetails episodeResponseDetails = response.getResponse().get(0);
			Assert.assertTrue("http://mybeautifulcatchupservice.com/img/shows/16KidsandCounting1280.jpg".equals(episodeResponseDetails.getImage()));
			Assert.assertTrue(StringUtils.isEmpty(episodeResponseDetails.getSlug()));
			Assert.assertTrue(StringUtils.isEmpty(episodeResponseDetails.getTitle()));
		} catch (Exception e) {
			fail();
		}
	}
	
	

}
