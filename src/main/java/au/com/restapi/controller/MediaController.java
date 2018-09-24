package au.com.restapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import au.com.restapi.domain.EpisodeRequestDetails;
import au.com.restapi.domain.EpisodeResponseDetails;
import au.com.restapi.domain.Request;
import au.com.restapi.domain.Response;

@RestController
public class MediaController  {

	private static Logger logger = LoggerFactory.getLogger(MediaController.class);

	@RequestMapping(method = RequestMethod.POST, value="/media/episodeDetails", consumes = "application/json")
    @ResponseBody
    public Response getEpisodeDetails(@RequestBody Request request) {
		logger.info("Received a request with size " + request.getPayload().size() + " received");
		final Response response = new Response();
		List<EpisodeRequestDetails> results = request.getResultsWithMoreThanOneEpisodeAndDrmTrue();
		if (!CollectionUtils.isEmpty(results)) {
			List<EpisodeResponseDetails> episodeDetails = results.stream().map(r -> new EpisodeResponseDetails(r.getImage(), r.getSlug(), r.getTitle())).collect(Collectors.toList());
			response.setResponse(episodeDetails);
			logger.info("Response with size " + response.getResponse().size() + " sent");
		} else {
			logger.info("Returning a empty response");
		}
        return response;
    }
}
