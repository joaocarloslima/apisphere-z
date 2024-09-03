CREATE TABLE IF NOT EXISTS posts (
   id BIGINT AUTO_INCREMENT,
   text VARCHAR(255),
   created_at datetime,
    primary key (id)
);

INSERT INTO posts (text, created_at) VALUES
     ('First post on my blog!', '2024-08-26 10:30:00'),
     ('Exploring the H2 database', '2024-08-26 11:00:00'),
     ('Java and Spring Boot are awesome!', '2024-08-26 11:30:00'),
     ('Understanding @Entity annotation in JPA', '2024-08-26 12:00:00'),
     ('Learning SQL with H2 database', '2024-08-26 12:30:00');