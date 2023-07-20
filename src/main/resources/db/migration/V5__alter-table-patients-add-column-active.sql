ALTER TABLE patients
ADD active BOOLEAN;

UPDATE patients
SET active = TRUE;
