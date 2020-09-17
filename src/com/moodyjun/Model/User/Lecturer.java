package com.moodyjun.Model.User;
import com.moodyjun.Model.Module.Module;
import com.moodyjun.Model.Util.ID;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Lecturer extends User {

    private List<Module> moduleList;

    public Lecturer(ID id, int role, String name, String password,
                    Gender gender, int age, String email, String phoneNum) {
        super(id, role, name, password, gender, age, email, phoneNum);
    }

    public Lecturer(ID id, int role, String name, String password,
                    Gender gender, int age, String email, String phoneNum, List<Module> moduleList) {
        super(id, role, name, password, gender, age, email, phoneNum);
        this.moduleList = moduleList;
    }

    public void addModule(Module module){ this.moduleList.add(module); }

    public void removeModule(Module module){
        this.moduleList.remove(module);
    }

    public List<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    public String moduleListToString(){
        if(this.moduleList.size()==0) return "Empty";
        StringJoiner moduleJoiner = new StringJoiner(",");
        for(Module module : this.moduleList){
            moduleJoiner.add(module.getModuleId());
        }
        return moduleJoiner.toString();
    }

    @Override
    public String toString() {
        return super.toString()+"\n"+ moduleListToString();
    }
}