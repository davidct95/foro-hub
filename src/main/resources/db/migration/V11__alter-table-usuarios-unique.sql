ALTER TABLE usuarios
MODIFY COLUMN nombre VARCHAR(250) NOT NULL UNIQUE;

ALTER TABLE usuarios
MODIFY COLUMN correo_electronico VARCHAR(250) NOT NULL UNIQUE;