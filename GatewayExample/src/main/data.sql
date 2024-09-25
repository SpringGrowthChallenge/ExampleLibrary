-- Insertar registros en la tabla libros
INSERT INTO libros (isbn, titulo, descripcion, portada_url, formato, fecha_publicacion) VALUES
('9781234567890', 'Libro 1', 'Descripción del Libro 1', 'http://example.com/portada1.jpg', 'físico', '2023-01-01'),
('9781234567891', 'Libro 2', 'Descripción del Libro 2', 'http://example.com/portada2.jpg', 'digital', '2023-02-01'),
('9781234567892', 'Libro 3', 'Descripción del Libro 3', 'http://example.com/portada3.jpg', 'físico', '2023-03-01'),
('9781234567893', 'Libro 4', 'Descripción del Libro 4', 'http://example.com/portada4.jpg', 'digital', '2023-04-01'),
('9781234567894', 'Libro 5', 'Descripción del Libro 5', 'http://example.com/portada5.jpg', 'físico', '2023-05-01'),
('9781234567895', 'Libro 6', 'Descripción del Libro 6', 'http://example.com/portada6.jpg', 'digital', '2023-06-01'),
('9781234567896', 'Libro 7', 'Descripción del Libro 7', 'http://example.com/portada7.jpg', 'físico', '2023-07-01'),
('9781234567897', 'Libro 8', 'Descripción del Libro 8', 'http://example.com/portada8.jpg', 'digital', '2023-08-01'),
('9781234567898', 'Libro 9', 'Descripción del Libro 9', 'http://example.com/portada9.jpg', 'físico', '2023-09-01'),
('9781234567899', 'Libro 10', 'Descripción del Libro 10', 'http://example.com/portada10.jpg', 'digital', '2023-10-01');

-- Insertar registros en la tabla usuarios
INSERT INTO usuarios (nombre, email, password, enabled, fecha_creacion, fecha_modificacion) VALUES
('Usuario 1', 'usuario1@example.com', 'password1', true, '2023-01-01 10:00:00', '2023-01-01 10:00:00'),
('Usuario 2', 'usuario2@example.com', 'password2', true, '2023-02-01 10:00:00', '2023-02-01 10:00:00'),
('Usuario 3', 'usuario3@example.com', 'password3', true, '2023-03-01 10:00:00', '2023-03-01 10:00:00'),
('Usuario 4', 'usuario4@example.com', 'password4', true, '2023-04-01 10:00:00', '2023-04-01 10:00:00'),
('Usuario 5', 'usuario5@example.com', 'password5', true, '2023-05-01 10:00:00', '2023-05-01 10:00:00'),
('Usuario 6', 'usuario6@example.com', 'password6', true, '2023-06-01 10:00:00', '2023-06-01 10:00:00'),
('Usuario 7', 'usuario7@example.com', 'password7', true, '2023-07-01 10:00:00', '2023-07-01 10:00:00'),
('Usuario 8', 'usuario8@example.com', 'password8', true, '2023-08-01 10:00:00', '2023-08-01 10:00:00'),
('Usuario 9', 'usuario9@example.com', 'password9', true, '2023-09-01 10:00:00', '2023-09-01 10:00:00'),
('Usuario 10', 'usuario10@example.com', 'password10', true, '2023-10-01 10:00:00', '2023-10-01 10:00:00');

-- Insertar registros en la tabla de relación usuario_libros
INSERT INTO usuario_libros (libro_id, usuario_id) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 2),
(5, 3),
(6, 3),
(7, 4),
(8, 4),
(9, 5),
(10, 5),
(1, 6),
(2, 6),
(3, 7),
(4, 7),
(5, 8),
(6, 8),
(7, 9),
(8, 9),
(9, 10),
(10, 10);