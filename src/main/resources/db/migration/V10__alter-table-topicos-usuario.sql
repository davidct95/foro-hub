ALTER TABLE topicos DROP COLUMN autor;
ALTER TABLE topicos ADD COLUMN id_usuario INT;
ALTER TABLE topicos ADD CONSTRAINT fk_topico_usuario FOREIGN KEY(id_usuario) REFERENCES usuarios(id);