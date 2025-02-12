ALTER TABLE topicos DROP COLUMN curso;
ALTER TABLE topicos ADD COLUMN id_curso INT;
ALTER TABLE topicos ADD CONSTRAINT fk_topico_curso FOREIGN KEY(id_curso) REFERENCES cursos(id);