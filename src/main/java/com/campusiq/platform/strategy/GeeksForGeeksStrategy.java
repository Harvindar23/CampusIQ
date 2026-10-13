package com.campusiq.platform.strategy;

import com.campusiq.platform.client.GeeksForGeeksClient;
import com.campusiq.platform.dto.PlatformProfileResponse;
import com.campusiq.platform.entity.Platform;
import org.springframework.stereotype.Component;

@Component
public class GeeksForGeeksStrategy implements PlatformProfileStrategy {
    private final GeeksForGeeksClient client;
    public GeeksForGeeksStrategy(GeeksForGeeksClient client) { this.client = client; }
    @Override public Platform getPlatform() { return Platform.GEEKSFORGEEKS; }
    @Override public boolean verifyUser(String username) {
        var response = client.basic(username);
        return response != null && "success".equalsIgnoreCase(response.status()) && response.data() != null;
    }
    @Override public PlatformProfileResponse fetchProfile(String username) {
        var basic = client.basic(username);
        var profile = client.profile(username);
        var stats = client.stats(username);
        var heatmap = client.heatmap(username);
        var b = basic == null ? null : basic.data();
        var p = profile == null ? null : profile.data();
        var s = stats == null ? null : stats.data();
        var h = heatmap == null ? null : heatmap.data();
        var d = s == null ? null : s.byDifficulty();
        Integer total = s != null && s.totalSolved() != null ? s.totalSolved() : b == null ? null : first(b.totalSolved(), b.totalProblemsSolved());
        Double current = b == null ? null : b.currentRating();
        Double maximum = b == null ? null : b.maxRating();
        Long score = p != null && p.codingScore() != null ? p.codingScore() : b == null ? null : b.codingScore();
        Integer currentStreak = h != null && h.currentStreak() != null ? h.currentStreak() : p == null ? null : p.currentStreak();
        Integer maxStreak = h != null && h.longestStreak() != null ? h.longestStreak() : p == null ? null : p.maxStreak();
        String actualUsername = p != null && p.username() != null ? p.username() : username;
        return new PlatformProfileResponse(Platform.GEEKSFORGEEKS, actualUsername,
                "https://www.geeksforgeeks.org/user/" + actualUsername + "/",
                p == null ? null : p.displayName(), p == null ? null : p.avatar(),
                p == null ? null : p.instituteRank(), score, total,
                d == null ? null : d.easy(), d == null ? null : d.medium(), d == null ? null : d.hard(),
                current, null, null, current, maximum, score, currentStreak, maxStreak, null, null);
    }
    private static Integer first(Integer first, Integer second) { return first != null ? first : second; }
}
