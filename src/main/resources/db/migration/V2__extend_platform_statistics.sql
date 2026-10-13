ALTER TABLE platform_stat_snapshots ADD COLUMN current_rating DOUBLE PRECISION;
ALTER TABLE platform_stat_snapshots ADD COLUMN max_rating DOUBLE PRECISION;
ALTER TABLE platform_stat_snapshots ADD COLUMN coding_score BIGINT;
ALTER TABLE platform_stat_snapshots ADD COLUMN current_streak INTEGER;
ALTER TABLE platform_stat_snapshots ADD COLUMN max_streak INTEGER;
