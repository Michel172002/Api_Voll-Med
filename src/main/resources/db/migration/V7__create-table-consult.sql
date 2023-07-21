CREATE TABLE consult (
    id SERIAL PRIMARY KEY,
    doctor_id bigint NOT NULL,
    patient_id bigint NOT NULL,
    date timestamp NOT NULL,
    CONSTRAINT fk_consult_doctor_id FOREIGN KEY (doctor_id) REFERENCES doctors(id),
    CONSTRAINT fk_consult_patient_id FOREIGN KEY (patient_id) REFERENCES patients(id)
);
