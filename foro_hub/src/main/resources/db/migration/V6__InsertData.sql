INSERT INTO perfil (nombre) VALUES ('Administrador'), ('Usuario');

INSERT INTO usuario (nombre, correo_electronico, contrasena, perfil_id) VALUES
                                                                            ('Juan Perez', 'juan@example.com', 'password123', 2),
                                                                            ('Maria Garcia', 'maria@example.com', 'password123', 2);

INSERT INTO curso (nombre, categoria) VALUES
                                          ('Java Básico', 'Programación'),
                                          ('HTTP en la web', 'Redes');

INSERT INTO topico (titulo, mensaje, fecha_creacion, status, autor_id, curso_id) VALUES
                                                                                     ('Problema con la instalación de Java', 'No puedo instalar Java en mi computadora.', '2024-06-01T00:00:00', 'ABIERTO', 1, 1),
                                                                                     ('Duda sobre HTTP', '¿Cómo funciona el protocolo HTTP?', '2024-06-02T00:00:00', 'ABIERTO', 2, 2);

INSERT INTO respuesta (mensaje, fecha_creacion, solucion, autor_id, topico_id) VALUES
                                                                                   ('Intenta descargar la última versión del sitio oficial.', '2024-06-03T00:00:00', FALSE, 2, 1),
                                                                                   ('HTTP es un protocolo de comunicación para la transferencia de información.', '2024-06-04T00:00:00', TRUE, 1, 2);
