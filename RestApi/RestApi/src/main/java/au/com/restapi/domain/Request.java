package au.com.restapi.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import au.com.restapi.domain.predicate.DomainOperations;
import au.com.restapi.domain.predicate.MediaPredicate;

@JsonIgnoreProperties
public class Request implements DomainOperations {
	
	 private List<EpisodeRequestDetails> payload = new ArrayList<EpisodeRequestDetails> ();
	 
	 private float skip;
	 
	 private float take;
	 
	 private float totalRecords;

	 public Request() {
		super();
	}

	public float getSkip() {
	  return skip;
	 }

	 public float getTake() {
	  return take;
	 }

	 public float getTotalRecords() {
	  return totalRecords;
	 }

	 public void setSkip(float skip) {
	  this.skip = skip;
	 }

	 public void setTake(float take) {
	  this.take = take;
	 }

	 public void setTotalRecords(float totalRecords) {
	  this.totalRecords = totalRecords;
	 }

	public List<EpisodeRequestDetails> getPayload() {
		return payload;
	}

	public void setPayload(List<EpisodeRequestDetails> payload) {
		this.payload = payload;
	}
	 
	public List<EpisodeRequestDetails> getResultsWithMoreThanOneEpisodeAndDrmTrue() {
		  return this.filter(getPayload(), MediaPredicate.isDrmTrue().and(MediaPredicate.isAtLeastOneEpisode()));
	}
}
