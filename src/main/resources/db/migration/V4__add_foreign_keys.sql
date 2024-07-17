-- V4__Add_Foreign_Keys.sql

-- Add foreign key for the experiences table
ALTER TABLE experiences
ADD CONSTRAINT fk_experiences_profile FOREIGN KEY (profile_id) REFERENCES profiles(id);

-- Add foreign key for the skills table
ALTER TABLE skills
ADD CONSTRAINT fk_skills_profile FOREIGN KEY (profile_id) REFERENCES profiles(id);

-- Add foreign key for the educations table
ALTER TABLE educations
ADD CONSTRAINT fk_educations_profile FOREIGN KEY (profile_id) REFERENCES profiles(id);

-- Add foreign key for the jobs table
ALTER TABLE jobs
ADD CONSTRAINT fk_jobs_user FOREIGN KEY (user_id) REFERENCES users(id);

-- Add foreign key for the job_application_mappers table
ALTER TABLE job_application_mappers
ADD CONSTRAINT fk_job_application_mappers_job FOREIGN KEY (job_id) REFERENCES jobs(id);

-- Add foreign key for the user_job_application_mappers table
ALTER TABLE user_job_application_mappers
ADD CONSTRAINT fk_user_job_application_mappers_user FOREIGN KEY (user_id) REFERENCES users(id),
ADD CONSTRAINT fk_user_job_application_mappers_job_application_mapper FOREIGN KEY (job_application_mapper_id) REFERENCES job_application_mappers(id);

-- Add foreign key for the users table
ALTER TABLE users
ADD CONSTRAINT fk_users_profile FOREIGN KEY (profile_id) REFERENCES profiles(id);

-- Add foreign key for the posts table
ALTER TABLE posts
ADD CONSTRAINT fk_posts_user FOREIGN KEY (user_id) REFERENCES users(id);

-- Add foreign key for the comments table
ALTER TABLE comments
ADD CONSTRAINT fk_comments_post FOREIGN KEY (post_id) REFERENCES posts(id),
ADD CONSTRAINT fk_comments_user_post_comment_mapper FOREIGN KEY (user_post_comment_mapper_id) REFERENCES user_post_comment_mappers(id);

-- Add foreign key for the user_post_comment_mappers table
ALTER TABLE user_post_comment_mappers
ADD CONSTRAINT fk_user_post_comment_mappers_post FOREIGN KEY (post_id) REFERENCES posts(id),
ADD CONSTRAINT fk_user_post_comment_mappers_user FOREIGN KEY (user_id) REFERENCES users(id);
