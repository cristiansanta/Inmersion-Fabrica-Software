-- Crear tabla regionales
CREATE TABLE IF NOT EXISTS regionales (
          id SERIAL PRIMARY KEY,
          nombre VARCHAR(255) NOT NULL
    );

-- Crear tabla centros_formacion
CREATE TABLE IF NOT EXISTS centros_formacion (
         id SERIAL PRIMARY KEY,
         nombre VARCHAR(255) NOT NULL,
        regional_id INTEGER NOT NULL,
        FOREIGN KEY (regional_id) REFERENCES regionales(id)
    );

-- Crear tabla fuentes_financiacion
CREATE TABLE IF NOT EXISTS fuentes_financiacion (
        id SERIAL PRIMARY KEY,
        nombre VARCHAR(255) NOT NULL
    );

-- Crear tabla giras_tecnicas
CREATE TABLE IF NOT EXISTS giras_tecnicas (
        id SERIAL PRIMARY KEY,
        regional_id INTEGER NOT NULL,
        centro_formacion_id INTEGER NOT NULL,
        fuente_financiacion_id INTEGER NOT NULL,
        objetivo_general TEXT NOT NULL,
        resultado_esperado TEXT NOT NULL,
        valor_total DECIMAL(10, 2) NOT NULL,
        observaciones TEXT,
        FOREIGN KEY (regional_id) REFERENCES regionales(id),
        FOREIGN KEY (centro_formacion_id) REFERENCES centros_formacion(id),
        FOREIGN KEY (fuente_financiacion_id) REFERENCES fuentes_financiacion(id)
    );

-- Crear tabla participantes
CREATE TABLE IF NOT EXISTS participantes (
        id SERIAL PRIMARY KEY,
        gira_tecnica_id INTEGER NOT NULL,
        nombre VARCHAR(255) NOT NULL,
        rol VARCHAR(255) NOT NULL,
        FOREIGN KEY (gira_tecnica_id) REFERENCES giras_tecnicas(id)
    );