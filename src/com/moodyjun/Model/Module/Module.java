package com.moodyjun.Model.Module;

import com.moodyjun.Model.User.Lecturer;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Module {

    private String moduleId ;
    private String moduleName ;
    private int level;
    private List<Lecturer> lecturerList;
    private List<String> intakeCodeList ;
    private List<Class> classList;
    private int numOfStud ;
    private int maxNumOfStud;
    private int testMarkPct;
    private int examMarkPct;
    private int assignmentMarkPct;


    public Module(String moduleId, String moduleName,int level,List<String> intakeCodeList, List<Class> classList,int maxNumOfStud, int numOfStud, int testMarkPct, int examMarkPct
            , int assignmentMarkPct) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.level = level;
        this.lecturerList = new ArrayList<>();
        this.intakeCodeList = intakeCodeList;
        this.classList = classList;
        this.maxNumOfStud = maxNumOfStud;
        this.numOfStud = numOfStud;
        this.testMarkPct = testMarkPct;
        this.examMarkPct = examMarkPct;
        this.assignmentMarkPct = assignmentMarkPct;
    }



    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Lecturer> getLecturerList() {
        return lecturerList;
    }

    public void setLecturerList(List<Lecturer> lecturerList) {
        this.lecturerList = lecturerList;
    }

    public List<String> getIntakeCodeList() { return intakeCodeList; }

    public void setIntakeCodeList(List<String> intakeCodeList) { this.intakeCodeList = intakeCodeList; }

    public List<Class> getClassList() {
        return classList;
    }

    public void setClassList(List<Class> classList) {
        this.classList = classList;
    }

    public int getNumOfStud() {
        return numOfStud;
    }

    public void setNumOfStud(int numOfStud) {
        this.numOfStud = numOfStud;
    }

    public int getTestMarkPct() {
        return testMarkPct;
    }

    public void setTestMarkPct(int testMarkPct) {
        this.testMarkPct = testMarkPct;
    }

    public int getExamMarkPct() {
        return examMarkPct;
    }

    public void setExamMarkPct(int examMarkPct) {
        this.examMarkPct = examMarkPct;
    }

    public int getAssignmentMarkPct() {
        return assignmentMarkPct;
    }

    public void setAssignmentMarkPct(int assignmentMarkPct) {
        this.assignmentMarkPct = assignmentMarkPct;
    }

    public int getMaxNumOfStud() {
        return maxNumOfStud;
    }

    public void setMaxNumOfStud(int maxNumOfStud) {
        this.maxNumOfStud = maxNumOfStud;
    }

    public String intakeCodeListToString() {
        if(intakeCodeList.size()==0) return "Empty";
        StringJoiner intakeCodeJoiner = new StringJoiner(",");
        for(String intakeCode : intakeCodeList){
            intakeCodeJoiner.add(intakeCode);
        }
        return intakeCodeJoiner.toString();
    }

    public String classListToString(){
        StringJoiner classJoiner = new StringJoiner(",");
        classList.forEach(c->classJoiner.add(c.toString()));
        return classJoiner.toString();
    }

    @Override
    public String toString() {
        return  this.getModuleId()+"\n"+this.getModuleName()+"\n"+getLevel()
                +"\n"+intakeCodeListToString()+"\n"+classListToString()+"\n"
                +this.getMaxNumOfStud()+"\n"+this.getNumOfStud()+"\n"
                +this.getTestMarkPct()+"\n"+this.getExamMarkPct()
                +"\n"+this.getAssignmentMarkPct();
    }



}