ALTER TABLE posts
ADD COLUMN user_id BIGINT;

ALTER TABLE posts
ADD CONSTRAINT fk_posts_user
FOREIGN KEY (user_id) REFERENCES users(id);

INSERT INTO users (name, email, password, bio, created_at, updated_at) VALUES
    ('John Doe', 'john.doe@example.com', '$2a$12$UUaV1dqulg09HiWsdLXVDefmqENZxEX29NTuWN1cc7XQxTLSG8tg.', 'Bio of John Doe', '2024-08-25 10:00:00', '2024-08-25 10:00:00'),
    ('Jane Smith', 'jane.smith@example.com', '$2a$12$UUaV1dqulg09HiWsdLXVDefmqENZxEX29NTuWN1cc7XQxTLSG8tg.', 'Bio of Jane Smith', '2024-08-25 11:00:00', '2024-08-25 11:00:00');

UPDATE posts SET user_id = 1 WHERE id IN (1, 2);
UPDATE posts SET user_id = 2 WHERE id IN (3, 4, 5);