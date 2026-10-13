# CampusIQ

Personal Spring Boot dashboard foundation. Configure a local PostgreSQL database (`campusiq`) and set `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, and a Base64 `JWT_SECRET`, then run `./mvnw spring-boot:run`.

Public endpoints: `POST /api/auth/register`, `POST /api/auth/login`. Send `Authorization: Bearer <token>` for `/api/me` and `/api/platforms`.

Supported integrations are LeetCode (alfa-leetcode-api wrapper), Codeforces (official public API), CodeChef (public profile HTML), and GeeksforGeeks (the documented `gfg-stats.tashif.codes` service). GFG uses its profile, stats, and heatmap endpoints for solved counts, coding score, ratings, and streaks where available. Code360 is intentionally not enabled: no stable, verifiable public profile API was found.
