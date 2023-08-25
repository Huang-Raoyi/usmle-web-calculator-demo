package com.example.demo;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Record {

    private String firstName, secondName, email;
    private int step1, step1Failure, Step2, Step2Failure, Step2CSFailure, Step3, Step3Failure;
    private boolean visaNeeded, priorResidency;
    private int YOG, clinicalExperience, ResearchExperience;
    private int papers;
    private String speciality;

    private double prob;

    private static final int[] features_toNormalize = {0, 2, 5, 8, 10, 11, 12};

    private static final double[] means = {229.6396018858041, 237.16657936092196, 80.1115767417496, 2015.152959664746, 5.937139863803038, 3.1660555264536407, 2.3352540597171294};
    private static final double[] stds = {16.611292383263265, 14.078639345823985, 107.01593572959325, 3.8259661074140108, 7.94749729153651, 8.501213392185532, 5.104024668890264};

    private static final double[] coefficients =
            {0.66587901,  0.47922321, -0.01762145,  0.26580458, -0.20619842,
            -0.09937123, -0.11526286,  0.10817695, -0.73634059,  0.27247702,
            0.24068173,  0.03001517,  0.08404334,  0.0256346 , -0.00457345,
            0.01901885,  0.03692684,  0.48415762,  0.61918067, -0.48883153};


    public double getProb(){
        double visaCode = visaNeeded ? 1.0 : 0.0;
        double residencyCode = priorResidency ? 1.0 : 0.0;
        Double[] x_temp = {(double) step1, (double)step1Failure,  (double)Step2, (double)Step2Failure, (double)Step2CSFailure, (double)Step3, (double)Step3Failure, visaCode, (double)YOG, residencyCode, (double)clinicalExperience, (double)ResearchExperience, (double)papers};

        ArrayList<Double> x = new ArrayList<>(Arrays.asList(x_temp));
        System.out.println(x);

        for (int i=0; i < features_toNormalize.length; i++) {
            x.set(features_toNormalize[i], (x.get(features_toNormalize[i]) - means[i])/ stds[i]);
//            System.out.println(x);
        }

        if (speciality.equalsIgnoreCase("Family Medicine")) {
            x.add(1.0); x.add(0.0); x.add(0.0); x.add(0.0); x.add(0.0); x.add(0.0);
        }else if (speciality.equalsIgnoreCase("Internal Medicine")) {
            x.add(0.0); x.add(1.0); x.add(0.0); x.add(0.0); x.add(0.0); x.add(0.0);
        } else if (speciality.equalsIgnoreCase("Neurology")) {
            x.add(0.0); x.add(0.0); x.add(1.0); x.add(0.0); x.add(0.0); x.add(0.0);
        } else if (speciality.equalsIgnoreCase("Pathology")) {
            x.add(0.0); x.add(0.0); x.add(0.0); x.add(1.0); x.add(0.0); x.add(0.0);
        } else if (speciality.equalsIgnoreCase("Pediatrics")) {
            x.add(0.0); x.add(0.0); x.add(0.0); x.add(0.0); x.add(1.0); x.add(0.0);
        } else if(speciality.equalsIgnoreCase("Psychiatry")){
            x.add(0.0); x.add(0.0); x.add(0.0); x.add(0.0); x.add(0.0); x.add(1.0);
        } else {
            throw new IllegalArgumentException("Unknown speciality input");
        }

        double res = coefficients[0];
        for (int i=0; i < x.size(); i++) {
            res += coefficients[i+1] * x.get(i);
        }

        prob = Math.exp(res) / (1+Math.exp(res));

        return prob;
    }

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
        System.out.println(speciality);
        if (speciality == null) {
            throw new IllegalArgumentException("Wrong speciality entered");
        }
        this.speciality = speciality;
    }
}
