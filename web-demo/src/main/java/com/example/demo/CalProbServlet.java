package com.example.demo;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "CalProbServlet", value = "/getMatchingProb")
public class CalProbServlet extends HttpServlet {
    private CalProbModel model;

    public void init() {
        model = new CalProbModel();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Record record = getRecord(request);

        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + record.getProb() + "</h1>");
        out.println("</body></html>");

        model.add(record);
    }

    public Record getRecord(HttpServletRequest request){
        Record record = new Record();

        record.setFirstName(request.getParameter("First_Name"));
        record.setSecondName(request.getParameter("Second_Name"));
        record.setEmail(request.getParameter("Email"));

        record.setStep1(Integer.parseInt(request.getParameter("step1")));
        record.setStep1Failure(Integer.parseInt(request.getParameter("step1Failure")));
        record.setStep2(Integer.parseInt(request.getParameter("step2")));
        record.setStep2Failure(Integer.parseInt(request.getParameter("step2Failure")));
        record.setStep2CSFailure(Integer.parseInt(request.getParameter("step2CSFailure")));
        record.setStep3(Integer.parseInt(request.getParameter("step3")));
        record.setStep3Failure(Integer.parseInt(request.getParameter("step3Failure")));

        record.setVisaNeeded(request.getParameter("visaNeeded").equals("1"));
        record.setYOG(Integer.parseInt(request.getParameter("yog")));
        record.setPriorResidency(request.getParameter("priorResidency").equalsIgnoreCase("Yes"));

        record.setResearchExperience(Integer.parseInt(request.getParameter("researchExperience")));
        record.setClinicalExperience(Integer.parseInt(request.getParameter("clinicalExperience")));
        record.setPapers(Integer.parseInt(request.getParameter("papers")));
        record.setSpeciality(request.getParameter("speciality"));

        return record;
    }


    public void destroy() {
        model.save();
    }
}