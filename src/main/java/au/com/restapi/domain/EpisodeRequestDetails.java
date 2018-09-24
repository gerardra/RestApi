package au.com.restapi.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import au.com.restapi.domain.predicate.DomainOperations;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class EpisodeRequestDetails implements DomainOperations {
	
	private String country;
	
	private String description;
	
	private boolean drm;
	
	private int episodeCount;
	
	private String genre;
	
	private Image image;
	
	private String language;
	
	private NextEpisode nextEpisode;
	
	private String primaryColour;
	
	private List<Season> seasons;
	
	private String slug;
	
	private String title;
	
	private String tvChannel;

	
	public EpisodeRequestDetails() {
		super();
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDrm() {
		return drm;
	}

	public void setDrm(boolean drm) {
		this.drm = drm;
	}

	public int getEpisodeCount() {
		return episodeCount;
	}

	public void setEpisodeCount(int episodeCount) {
		this.episodeCount = episodeCount;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public NextEpisode getNextEpisode() {
		return nextEpisode;
	}

	public void setNextEpisode(NextEpisode nextEpisode) {
		this.nextEpisode = nextEpisode;
	}

	public String getPrimaryColour() {
		return primaryColour;
	}

	public void setPrimaryColour(String primaryColour) {
		this.primaryColour = primaryColour;
	}

	public List<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTvChannel() {
		return tvChannel;
	}

	public void setTvChannel(String tvChannel) {
		this.tvChannel = tvChannel;
	}

	
}
	
