package com.moodyjun.Model.User;
import com.moodyjun.Model.Module.Module;
import com.moodyjun.Model.Module.Result;
import com.moodyjun.Model.Util.ID;

import java.util.*;
import java.util.stream.Collectors;

public class Student extends User {

    private int level;
    private String intakeCode ;
    private List<Module> moduleList;
    private Map<String, Result> results;

    public Student(ID id, int role, String name, String password, Gender gender, int age, String email, String phoneNum,int level, String intakeCode) {
        super(id, role, name, password, gender, age, email, phoneNum);
        this.level = level;
        this.intakeCode = intakeCode;
        moduleList = new ArrayList<>();
        results = new HashMap<>();
    }

    public Student(ID id, int role, String name, String password, Gender gender, int age, String email, String phoneNum,int level, String intakeCode, List<Module> moduleList, Map<String, Result> results) {
        super(id, role, name, password, gender, age, email, phoneNum);
        this.level = level;
        this.intakeCode = intakeCode;
        this.moduleList = moduleList;
        this.results = results;
        moduleList = new ArrayList<>();
        results = new HashMap<>();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getIntakeCode() {
        return intakeCode;
    }

    public void setIntakeCode(String intakeCode) {
        this.intakeCode = intakeCode;
    }

    public List<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    public Map<String, Result> getResults() {
        return results;
    }

    public void setResults(Map<String, Result> results) {
        this.results = results;
    }

    public String moduleListToString(){
        if(this.moduleList.size()==0) return "Empty";
        StringJoiner moduleJoiner = new StringJoiner(",");
        for(Module module : this.moduleList){
            moduleJoiner.add(module.getModuleId());
        }
        return moduleJoiner.toString();
    }
    
    public String resultsToString(){
        if(this.results.size()==0) return "Empty";
        StringJoiner resultJoiner = new StringJoiner(",");
        results.forEach((moduleID,result)->{resultJoiner.add(result.getResultID().toString());});
        return resultJoiner.toString();
    }

    @Override
    public String toString() {
        return super.toString()+"\n"+this.getLevel()+"\n"+this.getIntakeCode()+"\n"+moduleListToString()
                +"\n"+ resultsToString();
    }
}
