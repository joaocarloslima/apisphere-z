ALTER TABLE users
ADD COLUMN avatar VARCHAR(255);

UPDATE users SET avatar = 'https://avatar.iran.liara.run/username?username=john+doe' WHERE email = 'john.doe@example.com';
UPDATE users SET avatar = 'https://avatar.iran.liara.run/username?username=jane+smith' WHERE email = 'jane.smith@example.com';