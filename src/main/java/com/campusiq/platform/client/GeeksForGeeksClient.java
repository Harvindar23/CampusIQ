package com.campusiq.platform.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GeeksForGeeksClient {
    private final RestClient http;
    public GeeksForGeeksClient(RestClient.Builder builder, @Value("${platform.geeksforgeeks.base-url}") String baseUrl) {
        this.http = builder.baseUrl(baseUrl).build();
    }
    public BasicResponse basic(String username) { return get("/" + username, new ParameterizedTypeReference<>() {}); }
    public ProfileResponse profile(String username) { return get("/" + username + "/profile", new ParameterizedTypeReference<>() {}); }
    public StatsResponse stats(String username) { return get("/" + username + "/stats", new ParameterizedTypeReference<>() {}); }
    public HeatmapResponse heatmap(String username) { return get("/" + username + "/heatmap", new ParameterizedTypeReference<>() {}); }
    private <T> T get(String path, ParameterizedTypeReference<T> type) {
        try { return http.get().uri(path).retrieve().body(type); }
        catch (Exception e) { throw new PlatformClientException("GeeksforGeeks is unavailable or returned an invalid response", e); }
    }
    @JsonIgnoreProperties(ignoreUnknown = true) public record BasicResponse(String status, String username, BasicData data) {}
    @JsonIgnoreProperties(ignoreUnknown = true) public record ProfileResponse(String status, String username, ProfileData data) {}
    @JsonIgnoreProperties(ignoreUnknown = true) public record StatsResponse(String status, String username, StatsData data) {}
    @JsonIgnoreProperties(ignoreUnknown = true) public record HeatmapResponse(String status, String username, HeatmapData data) {}
    @JsonIgnoreProperties(ignoreUnknown = true) public record BasicData(Integer totalSolved, Integer totalProblemsSolved, Double currentRating, Double maxRating, Long codingScore) {}
    @JsonIgnoreProperties(ignoreUnknown = true) public record ProfileData(String displayName, String username, String avatar, String institute, Long instituteRank, Long codingScore, Integer totalProblemsSolved, Integer currentStreak, Integer maxStreak) {}
    @JsonIgnoreProperties(ignoreUnknown = true) public record StatsData(Integer totalSolved, Integer totalQuestions, Double acceptanceRate, Difficulty byDifficulty) {}
    @JsonIgnoreProperties(ignoreUnknown = true) public record Difficulty(Integer easy, Integer medium, Integer hard) {}
    @JsonIgnoreProperties(ignoreUnknown = true) public record HeatmapData(Integer currentStreak, Integer longestStreak, Integer totalActiveDays) {}
}
