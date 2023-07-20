ALTER TABLE doctors
ADD active BOOLEAN;

UPDATE doctors
SET active = TRUE;
