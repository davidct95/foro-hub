CREATE TABLE respuestas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_topico INT,
    mensaje VARCHAR(255) NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    autor VARCHAR(255),
    solucion BOOLEAN DEFAULT FALSE,
    FOREIGN KEY(id_topico) REFERENCES topicos(id)
);