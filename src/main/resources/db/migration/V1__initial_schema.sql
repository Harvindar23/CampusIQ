CREATE TABLE users (
  id BIGSERIAL PRIMARY KEY, name VARCHAR(120) NOT NULL, email VARCHAR(255) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL, college VARCHAR(255), branch VARCHAR(120), graduation_year INTEGER,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL, updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);
CREATE TABLE user_platform_accounts (
  id BIGSERIAL PRIMARY KEY, user_id BIGINT NOT NULL REFERENCES users(id), platform VARCHAR(30) NOT NULL,
  username VARCHAR(120) NOT NULL, external_user_id VARCHAR(255), status VARCHAR(30) NOT NULL,
  verified_at TIMESTAMP WITH TIME ZONE, last_synced_at TIMESTAMP WITH TIME ZONE,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL, updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
  CONSTRAINT uk_user_platform UNIQUE (user_id, platform)
);
CREATE TABLE platform_stat_snapshots (
  id BIGSERIAL PRIMARY KEY, account_id BIGINT NOT NULL UNIQUE REFERENCES user_platform_accounts(id),
  rating BIGINT, global_rank BIGINT, problems_solved INTEGER, easy_solved INTEGER, medium_solved INTEGER, hard_solved INTEGER,
  contest_rating DOUBLE PRECISION, contest_ranking BIGINT, contest_top_percent DOUBLE PRECISION,
  raw_data TEXT, captured_at TIMESTAMP WITH TIME ZONE NOT NULL
);
