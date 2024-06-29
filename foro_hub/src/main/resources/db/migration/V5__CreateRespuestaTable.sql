CREATE TABLE respuesta (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           mensaje TEXT NOT NULL,
                           fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           solucion BOOLEAN DEFAULT FALSE,
                           autor_id BIGINT,
                           topico_id BIGINT,
                           FOREIGN KEY (autor_id) REFERENCES usuario(id),
                           FOREIGN KEY (topico_id) REFERENCES topico(id)
);
