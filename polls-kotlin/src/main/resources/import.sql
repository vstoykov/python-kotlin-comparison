INSERT INTO questions (id, question_text, pub_date) VALUES (1, 'What is your favorite color?', '2025-03-24 10:00:00');
INSERT INTO questions (id, question_text, pub_date) VALUES (2, 'What is your favorite food?', '2025-03-24 19:00:00');

INSERT INTO choices (id, question_id, choice_text, votes) VALUES (1, 1, 'Red', 0);
INSERT INTO choices (id, question_id, choice_text, votes) VALUES (2, 1, 'Green', 0);
INSERT INTO choices (id, question_id, choice_text, votes) VALUES (3, 1, 'Blue', 0);
INSERT INTO choices (id, question_id, choice_text, votes) VALUES (4, 2, 'Steak', 0);
INSERT INTO choices (id, question_id, choice_text, votes) VALUES (5, 2, 'Pizza', 0);
INSERT INTO choices (id, question_id, choice_text, votes) VALUES (6, 2, 'Sushi', 0);
