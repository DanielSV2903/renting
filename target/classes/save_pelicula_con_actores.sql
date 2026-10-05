USE [VideoRent_C4J816_II2026] -- TODO CAMBIAN POR SU B.D.
-- Delete all records from required tables
DELETE FROM Pelicula_Actor;
DELETE FROM Pelicula;
DELETE FROM Actor;
DELETE FROM Genero;
-- Insert new genero
INSERT INTO Genero (nombre_genero) VALUES ('Action');
-- Capture generated genero_id
DECLARE @genero_id INT = SCOPE_IDENTITY();
-- Insert new actors
INSERT INTO Actor (nombre_actor, apellidos_actor) VALUES ('Keanu', 'Reeves');
DECLARE @actor1_id INT = SCOPE_IDENTITY();
INSERT INTO Actor (nombre_actor, apellidos_actor) VALUES ('Laurence', 'Fishburne');
DECLARE @actor2_id INT = SCOPE_IDENTITY();
