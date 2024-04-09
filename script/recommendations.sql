
--recommendations
CREATE TABLE recommendations (
     id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    recommendation_text TEXT NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
)

-- Insert recommendations

INSERT INTO recommendations (user_id, recommendation_text, date) VALUES

(1, 'Drink 2 liters of water at least 30 minutes before daily exercise.', '2024-03-01'),
(2, 'Add more fruits and vegetables into your diet..', '2024-03-02'),
(3, 'Join a local gym class and stay active', '2024-03-03'),
(4, 'Increase your sleep to at least 8 hours daily to help improve your well-being.', '2024-03-04'),
(5, 'Stress kills so monitor your stress level.', '2024-03-05'),
(6, 'Schedule regular check-ups with your doctor at intervals.', '2024-03-06'),
(7, 'incorporate strength training exercises into your workout routine.', '2024-03-07'),
(8, 'Limit processed foods and focus on organic and cooked food.', '2024-03-08'),
(9, 'Practice portion control and mindful eating to maintain a healthy weight.', '2024-03-09'),
(10)'Stay consistent with your healthy habits and see progress!', '2024-03-10');

--show table
SELECT * FROM recommendations

