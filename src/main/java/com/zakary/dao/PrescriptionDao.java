package com.zakary.dao;

public class PrescriptionDao {
    private Integer prescription_id;
    private Integer doctor_id;
    private Integer patient_id;
    private Integer drug_id;
    private Integer drug_num;


    public Integer getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(Integer prescription_id) {
        this.prescription_id = prescription_id;
    }

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public Integer getDrug_id() {
        return drug_id;
    }

    public void setDrug_id(Integer drug_id) {
        this.drug_id = drug_id;
    }

    public Integer getDrug_num() {
        return drug_num;
    }

    public void setDrug_num(Integer drug_num) {
        this.drug_num = drug_num;
    }
}
