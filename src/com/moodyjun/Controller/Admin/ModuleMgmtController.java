package com.moodyjun.Controller.Admin;

import com.moodyjun.Dao.Module.ModuleDao;
import com.moodyjun.Exception.MarksPctException;
import com.moodyjun.Exception.NullValueException;
import com.moodyjun.Model.Module.Class;
import com.moodyjun.Model.Module.Module;
import com.moodyjun.Model.Module.ModuleBuilder;
import com.moodyjun.Services.Module.ModuleService;
import com.moodyjun.View.Admin.ModuleMgmt.*;
import com.moodyjun.View.Util.JClassPicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class ModuleMgmtController extends AdminTemplateController {
    private ModuleMgmtPage moduleMgmtPage;
    private ModuleService moduleService;
    private static ModuleMgmtController moduleMgmtPageController;

    private ModuleMgmtController(ModuleMgmtPage moduleMgmtPage , ModuleService moduleService) {
        super(moduleMgmtPage);
        this.moduleMgmtPage = moduleMgmtPage;
        this.moduleService = moduleService;
        buttonActionListenerInit();

    }

    private void buttonActionListenerInit(){
        moduleMgmtPage.getCreateModulePanel().getActionButton().addActionListener(e -> createModule(moduleMgmtPage.getCreateModulePanel()));
        moduleMgmtPage.getCreateModulePanel().getAddClassButton().addActionListener(e->addClassToggle(e,moduleMgmtPage.getCreateModulePanel()));
        moduleMgmtPage.getCreateModulePanel().getClassPickerList().forEach(c->c.getDeleteClassButton().addActionListener(e->addClassToggle(e,moduleMgmtPage.getCreateModulePanel())));

        moduleMgmtPage.getUpdateModulePanel().getActionButton().addActionListener(e -> updateModule(moduleMgmtPage.getUpdateModulePanel()));
        moduleMgmtPage.getUpdateModulePanel().getAddClassButton().addActionListener(e->addClassToggle(e,moduleMgmtPage.getUpdateModulePanel()));
        moduleMgmtPage.getUpdateModulePanel().getClassPickerList().forEach(c->c.getDeleteClassButton().addActionListener(e->addClassToggle(e,moduleMgmtPage.getUpdateModulePanel())));
        moduleMgmtPage.getUpdateModulePanel().getFindModuleButton().addActionListener(e -> findModule(moduleMgmtPage.getUpdateModulePanel()) );

        moduleMgmtPage.getDeleteModulePanel().getDeleteModuleButton().addActionListener(e -> deleteModule(moduleMgmtPage.getDeleteModulePanel()));
    }

    public void addClassToggle(ActionEvent e , ModuleFormTemplatePanel moduleFormTemplatePanel){
        if(e.getSource()==moduleFormTemplatePanel.getAddClassButton()
                ||e.getSource()==moduleFormTemplatePanel.getAddClassButton()){
            for(JClassPicker classPicker : moduleFormTemplatePanel.getClassPickerList()){
                if(!classPicker.isVisible()){
                    classPicker.setVisible(true);
                    break;
                }
            }
        }else{
            for(JClassPicker classPicker : moduleFormTemplatePanel.getClassPickerList()){
                if(e.getSource()==classPicker.getDeleteClassButton()){
                    classPicker.setVisible(false);
                    break;
                }
            }
        }
        List<JClassPicker> availableClasses = moduleFormTemplatePanel.getClassPickerList().stream().filter(Component::isVisible).collect(Collectors.toList());
        if(availableClasses.size()==5) {
            moduleFormTemplatePanel.getAddClassButton().setVisible(false);
        }else{
            moduleFormTemplatePanel.getAddClassButton().setVisible(true);
        }
    }

    public void createModule(CreateModulePanel createModulePanel){
        try {
            String moduleID =  createModulePanel.getModuleIDField().getText();
            String moduleName =  createModulePanel.getModuleNameField().getText();
            List<String> intakeCodeList;
            String[] intakeCodeString =  createModulePanel.getIntakesCodeField().getText().split(",");
            if(moduleID.equals("")||moduleName.equals("")||intakeCodeString[0].equals("")) throw new NullValueException();
            int level = Integer.parseInt(createModulePanel.getLevelBox().getSelectedItem().toString());
            intakeCodeList = Arrays.stream( createModulePanel.getIntakesCodeField().getText().split(","))
                    .map(String::trim).collect(Collectors.toList());
            int maxNumOfStud = (int)  createModulePanel.getNumOfStudBox().getSelectedItem();
            int numOfStud = 0 ;
            List<Class> classList = getClassList(createModulePanel,moduleID);
            int testMarkPct = Integer.parseInt(createModulePanel.getTestMarkPctField().getText());
            int examMarkPct = Integer.parseInt(createModulePanel.getExamMarkPctField().getText());
            int assignmentMarkPct = Integer.parseInt(createModulePanel.getAssignmentMarkPctField().getText());
            if(testMarkPct+examMarkPct+assignmentMarkPct!=100) throw new MarksPctException(testMarkPct,examMarkPct,assignmentMarkPct);
            ModuleBuilder moduleBuilder = ModuleBuilder.getInstance();
            Module module = moduleBuilder.setModuleId(moduleID).setModuleName(moduleName).setLevel(level).setIntakeCodeList(intakeCodeList)
                    .setClassList(classList).setMaxNumOfStud(maxNumOfStud).setNumOfStud(numOfStud)
                    .setTestMarkPct(testMarkPct).setExamMarkPct(examMarkPct).setAssignmentMarkPct(assignmentMarkPct).getModule();
            if(moduleService.createModule(module)==1) {
                JOptionPane.showMessageDialog(createModulePanel,"Module Create Successfully.");
                clearFormValue(createModulePanel);
            }
            else JOptionPane.showMessageDialog(createModulePanel,"Module Name Exist");
        } catch (NullValueException | NullPointerException | MarksPctException e) {
            JOptionPane.showMessageDialog(createModulePanel,e.toString());
        }

    }


    public void updateModule(UpdateModulePanel updateModulePanel){
        try{
            String moduleID =updateModulePanel.getModuleIDField().getText();
            String moduleName = updateModulePanel.getModuleNameField().getText();
            int level = Integer.parseInt(updateModulePanel.getLevelBox().getSelectedItem().toString());
            List<String> intakeCodeList;
            String[] intakeCodeString =  updateModulePanel.getIntakesCodeField().getText().split(",");
            if(moduleID.equals("")||moduleName.equals("")||intakeCodeString.length==0) throw new NullValueException();
            intakeCodeList = Arrays.stream(updateModulePanel.getIntakesCodeField().getText().split(","))
                    .map(String::trim).collect(Collectors.toList());
            int maxNumOfStud = (int)  updateModulePanel.getNumOfStudBox().getSelectedItem();
            int numOfStud = (int) ModuleDao.getInstance().getModuleByID(moduleID).getNumOfStud();
            List<Class> classList = getClassList(updateModulePanel,moduleID);
            int testMarkPct = Integer.parseInt(updateModulePanel.getTestMarkPctField().getText());
            int examMarkPct = Integer.parseInt(updateModulePanel.getExamMarkPctField().getText());
            int assignmentMarkPct = Integer.parseInt(updateModulePanel.getAssignmentMarkPctField().getText());
            if(testMarkPct+examMarkPct+assignmentMarkPct!=100) throw new MarksPctException(testMarkPct,examMarkPct,assignmentMarkPct);
            ModuleBuilder moduleBuilder = ModuleBuilder.getInstance();
            Module module = moduleBuilder.setModuleId(moduleID).setModuleName(moduleName).setLevel(level)
                    .setIntakeCodeList(intakeCodeList)
                    .setClassList(classList).setMaxNumOfStud(maxNumOfStud).setNumOfStud(numOfStud)
                    .setTestMarkPct(testMarkPct).setExamMarkPct(examMarkPct).setAssignmentMarkPct(assignmentMarkPct).getModule();
            if(moduleService.updateModule(module)==1) {
                JOptionPane.showMessageDialog(updateModulePanel,"Module Update Successfully.");
                clearFormValue(updateModulePanel);
            }
            else JOptionPane.showMessageDialog(updateModulePanel,"Module does not Exist");
        } catch (NullValueException | NullPointerException | MarksPctException e) {
            JOptionPane.showMessageDialog(updateModulePanel,e.toString());
        }

    }

    public void findModule(UpdateModulePanel updateModulePanel){
        String moduleID = updateModulePanel.getModuleIDField().getText();
        Module module = moduleService.getModuleByID(moduleID);
        if (module==null){ JOptionPane.showMessageDialog(updateModulePanel,"Module ID does not exist"); return;}
        clearFormValue(updateModulePanel);
        updateModulePanel.getModuleIDField().setText(moduleID);
        updateModulePanel.getModuleNameField().setText(module.getModuleName());
        StringJoiner stringJoiner = new StringJoiner(",");
        updateModulePanel.getLevelBox().setSelectedItem(module.getLevel());
        module.getIntakeCodeList().forEach(stringJoiner::add);
        updateModulePanel.getIntakesCodeField().setText(stringJoiner.toString());
        for(int i = 0; i <module.getClassList().size() ; i++){
            updateModulePanel.getClassPickerList().get(i).setVisible(true);
            updateModulePanel.getClassPickerList().get(i).getClassTypeBox().setSelectedItem(module.getClassList().get(i).getClassType().split("-")[1]);
            updateModulePanel.getClassPickerList().get(i).getDayOfWeek().setSelectedItem(module.getClassList().get(i).getDateTime().getDayOfWeek().toString());
            updateModulePanel.getClassPickerList().get(i).getHours().setSelectedItem(module.getClassList().get(i).getDateTime().getHour());
            updateModulePanel.getClassPickerList().get(i).getMinutes().setSelectedItem(module.getClassList().get(i).getDateTime().getMinute());
            switch (module.getClassList().get(i).getDuration()) {
                case 60 -> updateModulePanel.getClassPickerList().get(i).getOneHourRadioButton().isSelected();
                case 90 -> updateModulePanel.getClassPickerList().get(i).getOneAndHalfHourRadioButton().isSelected();
                case 120 -> updateModulePanel.getClassPickerList().get(i).getTwoHourRadioButton().isSelected();
            }
            updateModulePanel.getClassPickerList().get(i).getLocationField().setText(module.getClassList().get(i).getLocation());

        }
        updateModulePanel.getNumOfStudBox().setSelectedItem(module.getMaxNumOfStud());
        updateModulePanel.getTestMarkPctField().setText(String.valueOf(module.getTestMarkPct()));
        updateModulePanel.getExamMarkPctField().setText(String.valueOf(module.getExamMarkPct()));
        updateModulePanel.getAssignmentMarkPctField().setText(String.valueOf(module.getAssignmentMarkPct()));
    }

    public void deleteModule(DeleteModulePanel deleteModulePanel){
        int option = JOptionPane.showConfirmDialog(deleteModulePanel,"Do you sure delete this module?");
        if(option == 1) return ;
        try {
            String moduleID = deleteModulePanel.getModuleIDField().getText();
            if (moduleID.equals("")) throw new NullValueException();
            if(moduleService.deleteModule(moduleID)==1){
                JOptionPane.showMessageDialog(deleteModulePanel,"Module Delete Successfully");
                deleteModulePanel.getModuleIDField().setText("");
            }else{
                JOptionPane.showMessageDialog(deleteModulePanel,"Module does not exist");
            }
        } catch (NullValueException e) {
            JOptionPane.showMessageDialog(deleteModulePanel,e.toString());
        }

    }

    public List<Class> getClassList(ModuleFormTemplatePanel moduleFormTemplatePanel , String moduleID) throws NullValueException {
        List<Class> classList = new ArrayList<>();
        int lecturerClassCount = 0 ;
        int tutorialClassCount = 0 ;
        int labClassCount = 0 ;
        List<JClassPicker> classPickerList = moduleFormTemplatePanel.getClassPickerList().stream().filter(Component::isVisible).collect(Collectors.toList());
        for(JClassPicker classPicker : classPickerList) {
            String classType;
            if(classPicker.getClassTypeBox().getSelectedIndex()==0){
               lecturerClassCount++;
               classType =  moduleID+"-"+"Lecturer-L"+lecturerClassCount ;
            }else if(classPicker.getClassTypeBox().getSelectedIndex()==1){
               tutorialClassCount++;
               classType =  moduleID+"-"+"Tutorial-T"+tutorialClassCount ;
            }else {
               labClassCount++;
               classType =  moduleID+"-"+"Lab-Lab"+labClassCount ;
            }
            LocalDateTime currentDateTime = LocalDateTime.now();
            while (!currentDateTime.getDayOfWeek().toString().equals(classPicker.getDayOfWeek().getSelectedItem())) {
                currentDateTime = currentDateTime.plusDays(1);
            }
            LocalDateTime dateTime = LocalDateTime.of(currentDateTime.getYear(),currentDateTime.get(ChronoField.MONTH_OF_YEAR)
                    ,currentDateTime.getDayOfMonth(),(int)classPicker.getHours().getSelectedItem(),(int) classPicker.getMinutes().getSelectedItem());

            int duration = classPicker.getOneHourRadioButton().isSelected()? 60 : (classPicker.getTwoHourRadioButton().isSelected() ? 120 : 90 );
            String location = classPicker.getLocationField().getText();
            if(location.equals("")) throw new NullValueException();

            classList.add(new Class(classType,dateTime,duration,location));
        }
        return classList;
    }


    public void clearFormValue(ModuleFormTemplatePanel moduleFormTemplatePanel){
        moduleFormTemplatePanel.getModuleIDField().setText("");
        moduleFormTemplatePanel.getModuleNameField().setText("");
        moduleFormTemplatePanel.getIntakesCodeField().setText("");
        for(JClassPicker classPicker : moduleFormTemplatePanel.getClassPickerList() ) {
            classPicker.setVisible(moduleFormTemplatePanel.getClassPickerList().indexOf(classPicker)<2);
            classPicker.getClassTypeBox().setSelectedIndex(0);
            classPicker.getDayOfWeek().setSelectedIndex(0);
            classPicker.getHours().setSelectedIndex(0);
            classPicker.getMinutes().setSelectedIndex(0);
            classPicker.getOneHourRadioButton().setSelected(true);
            classPicker.getLocationField().setText("");
        }
        moduleFormTemplatePanel.getNumOfStudBox().setSelectedIndex(0);
        moduleFormTemplatePanel.getTestMarkPctField().setText("");
        moduleFormTemplatePanel.getExamMarkPctField().setText("");
        moduleFormTemplatePanel.getAssignmentMarkPctField().setText("");
    }

    public ModuleMgmtPage getModuleMgmtPage() {
        return moduleMgmtPage;
    }

    public static ModuleMgmtController getInstance(){
        moduleMgmtPageController = new ModuleMgmtController(new ModuleMgmtPage(), ModuleService.getInstance());
        return moduleMgmtPageController;
    }

}
