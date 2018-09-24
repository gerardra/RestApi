package au.com.restapi.domain.predicate;

import java.util.function.Predicate;

import au.com.restapi.domain.EpisodeRequestDetails;


public class MediaPredicate {
	public static Predicate<EpisodeRequestDetails> isDrmTrue() {
		return s -> s.isDrm();
	}

	public static Predicate<EpisodeRequestDetails> isAtLeastOneEpisode() {
		return s -> s.getEpisodeCount() > 0;
	}
}
