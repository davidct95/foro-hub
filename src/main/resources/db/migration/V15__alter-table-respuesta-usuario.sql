ALTER TABLE respuestas DROP COLUMN autor;
ALTER TABLE respuestas ADD COLUMN id_usuario INT;
ALTER TABLE respuestas ADD CONSTRAINT fk_usuario_respuesta FOREIGN KEY(id_usuario) REFERENCES usuarios(id)