package au.com.restapi.utils;


import org.apache.commons.lang3.StringUtils;

public final class TestUtils {
	
	public static String generateResponseJson(final String methodName, final Class className) throws NoSuchMethodException, SecurityException {
		final StringBuilder jsonBuilder = new StringBuilder("{\"response\": [");
		ResponseEpisodeElement[] response = className.getMethod(methodName).getAnnotation(ResponseElement.class).response();
		
		for (ResponseEpisodeElement responseEpisodeElement : response) {
			if (responseEpisodeElement.jsonElements().length > 0) {
				jsonBuilder.append("{");
				for (JsonElement jsonElement : responseEpisodeElement.jsonElements()) {
					if (!StringUtils.isEmpty(jsonElement.name())) {
						jsonBuilder.append("\"").append(jsonElement.name()).append("\" :");
						if (!jsonBuilder.toString().contains("\"")) {
							jsonBuilder.append(jsonElement.value()).append(",");
						} else {
							jsonBuilder.append("\"").append(jsonElement.value()).append("\",");
						} 
					}
					
				}
				String substring = jsonBuilder.substring(0, jsonBuilder.length() - 1);
				jsonBuilder.replace(0, jsonBuilder.length(), substring);
				jsonBuilder.append("},");
			}
		}
		jsonBuilder.replace(0, jsonBuilder.length(), jsonBuilder.toString().replaceAll(",$", ""));
		jsonBuilder.append("]}");
		return jsonBuilder.toString();
	}

	
	public static String generateRequestJson(final String methodName, @SuppressWarnings("rawtypes") final Class className) throws NoSuchMethodException, SecurityException {
		final StringBuilder jsonBuilder = new StringBuilder("{\"payload\": [");
		@SuppressWarnings("unchecked")
		EpisodeRequestDetailsAnnotation[] episodeRequestDetailsAnnotations = className.getMethod(methodName).getAnnotation(RequestAnnotation.class).payload();
		for (EpisodeRequestDetailsAnnotation episodeRequestDetailsAnnotation : episodeRequestDetailsAnnotations) {
			jsonBuilder.append("{");
			for (JsonElement jsonElement : episodeRequestDetailsAnnotation.jsonElements()) {
				if (StringUtils.isNotBlank(jsonElement.name())) {
					if (StringUtils.isNotBlank(jsonElement.name())) {
						jsonBuilder.append("\"").append(jsonElement.name()).append("\":");
						appendElementValue(jsonElement, jsonBuilder);
						jsonBuilder.append(",");
					}
				}
			}
			JsonElement image = episodeRequestDetailsAnnotation.image();
			if (StringUtils.isNotBlank(image.name())) {
				jsonBuilder.append("\"").append("image").append("\" : {");
				jsonBuilder.append("\"").append(image.name()).append("\":");
				appendElementValue(image, jsonBuilder);
				jsonBuilder.append("},");
			}
			JsonElement[] nextEpisodes = episodeRequestDetailsAnnotation.nextEpisode();
			if (!isJsonElementEmpty(nextEpisodes)) {
				jsonBuilder.append("\"").append("nextEpisode").append("\" : {");
				for (JsonElement jsonElement : nextEpisodes) {
					if (StringUtils.isNotBlank(jsonElement.name())) {
						jsonBuilder.append("\"").append(jsonElement.name()).append("\" :");
						appendElementValue(jsonElement, jsonBuilder);
						jsonBuilder.append(",");
					}
				}
				jsonBuilder.replace(0, jsonBuilder.length(), jsonBuilder.toString().replaceAll(",$", ""));
				jsonBuilder.append("},");
			}
			JsonElement[] seasons = episodeRequestDetailsAnnotation.seasons();
			if (!isJsonElementEmpty(seasons)) {
				jsonBuilder.append("\"").append("seasons").append("\" : [ ");
				for (JsonElement jsonElement : seasons) {
					jsonBuilder.append("{\"").append(jsonElement.name()).append("\":");
					appendElementValue(jsonElement, jsonBuilder);
					jsonBuilder.append("},");
				}
				jsonBuilder.replace(0, jsonBuilder.length(), jsonBuilder.toString().replaceAll(",$", ""));
				jsonBuilder.append("]");
			}
			jsonBuilder.replace(0, jsonBuilder.length(), jsonBuilder.toString().replaceAll(",$", ""));
			jsonBuilder.append("},");
		}
		jsonBuilder.replace(0, jsonBuilder.length(), jsonBuilder.toString().replaceAll(",$", ""));
		jsonBuilder.append("]}");
		return jsonBuilder.toString();
	}
	
	private static void appendElementValue(final JsonElement jsonElement, final StringBuilder jsonBuilder) {
		String value = jsonElement.value();
		if ("null".equals(value)) {
			value = null;
			jsonBuilder.append(value);
			return;
		} 
		if (jsonElement.dataType() == JsonDataType.BOOLEAN) {
			jsonBuilder.append(Boolean.valueOf(value));
		} else if (jsonElement.dataType() == JsonDataType.INTEGER) {
			jsonBuilder.append(Integer.valueOf(value));
		} else if (jsonElement.dataType() == JsonDataType.DATE) {
			jsonBuilder.append("\"").append(value).append("\"");
		} else {
			jsonBuilder.append("\"").append(value).append("\"");
		}
	}
	
	
	private static boolean isJsonElementEmpty(JsonElement[] element) {
		
		if (element.length == 1 && StringUtils.isBlank(element[0].name())) {
			return true;
		}
		return false;
	}
}
