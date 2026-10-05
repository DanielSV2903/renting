USE [VideoRent_C4J816_II2026];
-- Delete all records from required tables
DELETE FROM Pelicula_Actor;
DELETE FROM Pelicula;
DELETE FROM Actor;
DELETE FROM Genero;
-- Insert new genero
-- Insert data into Genero table
INSERT INTO Genero (nombre_genero) VALUES
('Action'),
('Comedy'),
('Drama'),
('Sci-Fi'),
('Thriller'),
('Horror'),
('Animation'),
('Adventure'),
('Romance'),
('Crime');