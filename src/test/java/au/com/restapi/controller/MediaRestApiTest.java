package au.com.restapi.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import au.com.restapi.domain.RequestTest;
import au.com.restapi.utils.EpisodeRequestDetailsAnnotation;
import au.com.restapi.utils.JsonDataType;
import au.com.restapi.utils.JsonElement;
import au.com.restapi.utils.RequestAnnotation;
import au.com.restapi.utils.TestUtils;


@RunWith(SpringRunner.class)
@WebMvcTest(MediaController.class)
public class MediaRestApiTest {

	private static Logger logger = LoggerFactory.getLogger(RequestTest.class);
	
	 @Autowired
	 private MockMvc mockMvc;
	 
	 @MockBean
	 private MediaController target;

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
	 public void invaildHttpMethodMethod() throws Exception {
		 String generateRequestJson = TestUtils.generateRequestJson("invaildHttpMethodMethod", this.getClass());
	     MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/media/episodeDetails")
			                                       .contentType(MediaType.APPLICATION_JSON)
			                                       .content(generateRequestJson);
	     mockMvc.perform(builder).andExpect(status().isNotFound());
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
	 public void vaildPostHttpOperation() throws Exception {
		 String generateRequestJson = TestUtils.generateRequestJson("vaildPostHttpOperation", this.getClass());
		 logger.info(generateRequestJson);
		 MockHttpServletRequestBuilder builder =
                 MockMvcRequestBuilders.post("/media/episodeDetails")
                                       .contentType(MediaType.APPLICATION_JSON)
                                       .content(generateRequestJson);
		 this.mockMvc.perform(builder).andExpect(status().isOk());

	 }

	 @Test
	 public void invalidJson() throws Exception {
		 MockHttpServletRequestBuilder builder =
				 MockMvcRequestBuilders.post("/media/episodeDetails")
				 .contentType(MediaType.APPLICATION_JSON)
				 .content("test");
		 this.mockMvc.perform(builder).andExpect(status().isBadRequest());
		 
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
	 public void UnsupportedMediaType() throws Exception {
		 String generateRequestJson = TestUtils.generateRequestJson("UnsupportedMediaType", this.getClass());
		 logger.info(generateRequestJson);
		 MockHttpServletRequestBuilder builder =
				 MockMvcRequestBuilders.post("/media/episodeDetails")
				 .contentType(MediaType.APPLICATION_XML)
				 .content(generateRequestJson);
		 this.mockMvc.perform(builder).andExpect(status().isUnsupportedMediaType());
		 
	 }
	 
	 @Test
	 public void emptyContentInBody() throws Exception {
		 MockHttpServletRequestBuilder builder =
				 MockMvcRequestBuilders.post("/media/episodeDetails")
				 .contentType(MediaType.APPLICATION_JSON)
				 .content("");
		 this.mockMvc.perform(builder).andExpect(status().isBadRequest());
		 
	 }
}

