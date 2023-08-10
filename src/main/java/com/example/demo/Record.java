package com.example.demo;


import java.util.Random;

public class Record {

    private String firstName, secondName, email;
    private int step1, step1Failure, Step2, Step2Failure, Step2CSFailure, Step3, Step3Failure;
    private boolean visaNeeded, priorResidency;
    private int YOG, clinicalExperience, ResearchExperience;
    private int papers;
    private String speciality;

    private double prob;


    @Override
    public String toString() {
        return  firstName +
                "," + secondName +
                "," + email +
                "," + step1 +
                "," + step1Failure +
                "," + Step2 +
                "," + Step2Failure +
                "," + Step2CSFailure +
                "," + Step3 +
                "," + Step3Failure +
                "," + visaNeeded +
                "," + priorResidency +
                "," + YOG +
                "," + clinicalExperience +
                "," + ResearchExperience +
                "," + papers +
                "," + speciality +
                "," + String.format("%.2f", prob * 100);
    }

    public double getProb(){
        Random r = new Random();
        prob = r.nextDouble();

        return prob;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || !firstName.matches("^[a-zA-Z]*$")) {
            throw new IllegalArgumentException("Wrong first name entered");
        }
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        if (secondName == null || !secondName.matches("^[a-zA-Z]*$")) {
            throw new IllegalArgumentException("Wrong second name entered");
        }
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null ||
                !email.matches("^(.+)@(\\S+)$")){
            throw new IllegalArgumentException("Wrong email entered");
        }
        this.email = email;
    }

    public int getStep1() {
        return step1;
    }

    public void setStep1(int step1) {
        this.step1 = step1;
    }

    public int getStep1Failure() {
        return step1Failure;
    }

    public void setStep1Failure(int step1Failure) {
        this.step1Failure = step1Failure;
    }

    public int getStep2() {
        return Step2;
    }

    public void setStep2(int step2) {
        Step2 = step2;
    }

    public int getStep2Failure() {
        return Step2Failure;
    }

    public void setStep2Failure(int step2Failure) {
        Step2Failure = step2Failure;
    }

    public int getStep2CSFailure() {
        return Step2CSFailure;
    }

    public void setStep2CSFailure(int step2CSFailure) {
        Step2CSFailure = step2CSFailure;
    }

    public int getStep3() {
        return Step3;
    }

    public void setStep3(int step3) {
        Step3 = step3;
    }

    public int getStep3Failure() {
        return Step3Failure;
    }

    public void setStep3Failure(int step3Failure) {
        Step3Failure = step3Failure;
    }

    public boolean getVisaNeeded() {
        return visaNeeded;
    }

    public void setVisaNeeded(boolean visaNeeded) {
        this.visaNeeded = visaNeeded;
    }

    public int getYOG() {
        return YOG;
    }

    public void setYOG(int YOG) {
        this.YOG = YOG;
    }

    public boolean getPriorResidency() {
        return priorResidency;
    }

    public void setPriorResidency(boolean priorResidency) {
        this.priorResidency = priorResidency;
    }

    public int getClinicalExperience() {
        return clinicalExperience;
    }

    public void setClinicalExperience(int clinicalExperience) {
        if (clinicalExperience < 0) {
            throw new IllegalArgumentException("Clinical experience can't be negative");
        }
        this.clinicalExperience = clinicalExperience;
    }

    public int getResearchExperience() {
        return ResearchExperience;
    }

    public void setResearchExperience(int researchExperience) {
        if (researchExperience < 0) {
            throw new IllegalArgumentException("Research experience can't be negative");
        }
        ResearchExperience = researchExperience;
    }

    public int getPapers() {
        return papers;
    }

    public void setPapers(int papers) {
        if (papers < 0){
            throw new IllegalArgumentException("Paper # can't be negative");
        }
        this.papers = papers;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        if (speciality == null || !speciality.matches("^[a-zA-Z]*$")) {
            throw new IllegalArgumentException("Wrong speciality entered");
        }
        this.speciality = speciality;
    }
}
