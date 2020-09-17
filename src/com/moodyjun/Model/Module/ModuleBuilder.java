package com.moodyjun.Model.Module;

import com.moodyjun.Model.User.Lecturer;

import java.util.List;

public class ModuleBuilder {

    private String moduleId ;
    private String moduleName ;
    private int level;
    private List<String> intakeCodeList ;
    private List<Class> classList;
    private int maxNumOfStud;
    private int numOfStud ;
    private int testMarkPct;
    private int examMarkPct;
    private int assignmentMarkPct;
    private static ModuleBuilder moduleBuilder ;

    private ModuleBuilder (){};

    public ModuleBuilder setModuleId(String moduleId) {
        this.moduleId = moduleId;
        return this;
    }

    public ModuleBuilder setModuleName(String moduleName) {
        this.moduleName = moduleName;
        return this;
    }

    public ModuleBuilder setLevel(int level){
        this.level = level;
        return this;
    }

    public ModuleBuilder setIntakeCodeList(List<String> intakeCodeList) {
        this.intakeCodeList = intakeCodeList;
        return this;
    }

    public ModuleBuilder setClassList(List<Class> classList) {
        this.classList = classList;
        return this;
    }
    public ModuleBuilder setMaxNumOfStud(int maxNumOfStud) {
        this.maxNumOfStud = maxNumOfStud;
        return this;
    }

    public ModuleBuilder setNumOfStud(int numOfStud) {
        this.numOfStud = numOfStud;
        return this;
    }

    public ModuleBuilder setTestMarkPct(int testMarkPct) {
        this.testMarkPct = testMarkPct;
        return this;
    }

    public ModuleBuilder setExamMarkPct(int examMarkPct) {
        this.examMarkPct = examMarkPct;
        return this;
    }

    public ModuleBuilder setAssignmentMarkPct(int assignmentMarkPct) {
        this.assignmentMarkPct = assignmentMarkPct;
        return this;
    }


    public static ModuleBuilder getInstance(){
        if(moduleBuilder==null) moduleBuilder = new ModuleBuilder();
        return moduleBuilder ;
    }

    public Module getModule(){
        return new Module(moduleId,moduleName,level,intakeCodeList,classList,maxNumOfStud,numOfStud,
                testMarkPct, examMarkPct, assignmentMarkPct);
    }
}
