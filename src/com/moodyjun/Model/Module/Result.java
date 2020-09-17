package com.moodyjun.Model.Module;

import java.util.UUID;

public class Result {

    private UUID resultID ;
    private Module module ;
    private int testMark;
    private int examMark;
    private int assignmentMark;
    private String comment;
    private int totalMark;

    public Result(UUID resultID, Module module, int testMark, int examMark, int assignmentMark, int totalMark, String comment) {
        this.resultID = resultID;
        this.module = module;
        this.testMark = testMark;
        this.examMark = examMark;
        this.assignmentMark = assignmentMark;
        this.totalMark = totalMark;
        this.comment = comment;
    }

    public UUID getResultID() {
        return resultID;
    }

    public void setResultID(UUID resultID) {
        this.resultID = resultID;
    }

    public Module getModule() { return module; }

    public void setModule(Module module) { this.module = module; }

    public int getTestMark() {
        return testMark;
    }

    public void setTestMark(int testMark) {
        this.testMark = testMark;
    }

    public int getExamMark() {
        return examMark;
    }

    public void setExamMark(int examMark) {
        this.examMark = examMark;
    }

    public int getAssignmentMark() {
        return assignmentMark;
    }

    public void setAssignmentMark(int assignmentMark) {
        this.assignmentMark = assignmentMark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(int totalMark) {
        this.totalMark = totalMark;
    }

    private String commentToString(){
        if(comment.equals("")) return "Empty";
        return comment;
    }

    @Override
    public String toString() {
        return resultID.toString() +"\n"+ module.getModuleId()+"\n"+ testMark +"\n"+ examMark +"\n"+assignmentMark+"\n"+totalMark+"\n"+commentToString();
    }
}
