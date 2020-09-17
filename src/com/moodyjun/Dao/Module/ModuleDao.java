package com.moodyjun.Dao.Module;

import com.moodyjun.Dao.User.LecturerDao;
import com.moodyjun.Model.Module.Class;
import com.moodyjun.Model.Module.Module;
import com.moodyjun.Model.Module.ModuleBuilder;
import com.moodyjun.Model.User.Lecturer;
import com.moodyjun.Model.User.User;
import com.moodyjun.Model.Util.ID;

import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.*;

public class ModuleDao {

    private static ModuleDao moduleDao;
    private ModuleBuilder moduleBuilder;
    private File file ;
    private PrintWriter printWriter;
    private Scanner scanner ;
    private String newData ;
    private List<Module> moduleList;

    private ModuleDao(){ file = new File("module.txt");moduleList = getAllModule();}

    public int createModule(Module newModule) {
        moduleList.add(newModule);
        return writeToFile();
    }

    public int updateModule(Module newModule){
        Module previousModule = getModuleByID(newModule.getModuleId());
        System.out.println(previousModule);
        System.out.println(newModule);
        moduleList.set(moduleList.indexOf(previousModule),newModule);
        return writeToFile();
    }

    public List<Module> getAllModule(){
        moduleList = new ArrayList<>();
        moduleBuilder = ModuleBuilder.getInstance();
        try {
            scanner = new Scanner(file);
            while(scanner.hasNext()){
                String moduleID =scanner.nextLine();
                String moduleName = scanner.nextLine();
                int level = Integer.parseInt(scanner.nextLine());
                List<String> intakeCodeList  = Arrays.asList(scanner.nextLine().split(","));
                String[] classListString = scanner.nextLine().split(",");
                List<Class> classList = new ArrayList<>();
                for(String classString : classListString){
                    String[] c = classString.split(" ");
                    String classType = c[0];
                    String[] dayAndTime = c[1].split("-");
                    LocalDateTime currentDateTime = LocalDateTime.now();
                    while (!currentDateTime.getDayOfWeek().toString().equals(dayAndTime[0])) {
                        currentDateTime = currentDateTime.plusDays(1);
                    }
                    LocalDateTime dateTime = LocalDateTime.of(currentDateTime.getYear(),currentDateTime.get(ChronoField.MONTH_OF_YEAR)
                            ,currentDateTime.getDayOfMonth(),Integer.parseInt(dayAndTime[1]), Integer.parseInt(dayAndTime[2]));
                    int duration = Integer.parseInt(c[2]);
                    String location = c[3];
                    classList.add(new Class(classType,dateTime,duration,location));
                }
                int maxNumOfStud = Integer.parseInt(scanner.nextLine());
                int numOfStud = Integer.parseInt(scanner.nextLine());
                int testMarkPct = Integer.parseInt(scanner.nextLine());
                int examMarkPct = Integer.parseInt(scanner.nextLine());
                int assignmentPct = Integer.parseInt(scanner.nextLine());
                scanner.nextLine();
                scanner.nextLine();
                Module module = moduleBuilder.setModuleId(moduleID).setModuleName(moduleName)
                        .setLevel(level)
                        .setIntakeCodeList(intakeCodeList).setClassList(classList).setMaxNumOfStud(maxNumOfStud)
                        .setNumOfStud(numOfStud).setTestMarkPct(testMarkPct)
                        .setExamMarkPct(examMarkPct).setAssignmentMarkPct(assignmentPct)
                        .getModule();
                moduleList.add(module);
            }
            return moduleList;
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return null ;
    }

    public Module getModuleByID (String moduleID){
        return this.moduleList.stream().filter(module->module.getModuleId().equals(moduleID)).findFirst().orElse(null);
    }

    public int deleteModule(String moduleID){
        moduleList = getAllModule() ;
        Module moduleDelete = getModuleByID(moduleID);
        if(moduleDelete==null) return 0;

        moduleList.remove(moduleDelete);
        return writeToFile();
    }

    private int writeToFile(){
        newData = "";
        for(Module module : moduleList){
            newData += module.toString()+"\n\n\n";
        }
        try {
            printWriter = new PrintWriter(file);
            printWriter.write(newData);
            printWriter.close();
            System.out.println("Update Successfully.");
            return 1;
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return 0;
    }

    public List<Module> getModuleList() {
        return moduleList;
    }

    public static ModuleDao getInstance(){
        if(moduleDao==null){
            synchronized(ModuleDao.class){
                if(moduleDao==null)  moduleDao = new ModuleDao();
            }
        }
        return moduleDao;
    }

}
