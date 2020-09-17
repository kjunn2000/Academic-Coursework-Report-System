package com.moodyjun.Controller.Lecturer;

import com.moodyjun.Exception.NullValueException;
import com.moodyjun.Model.Module.Module;
import com.moodyjun.Model.Module.Result;
import com.moodyjun.Model.User.Lecturer;
import com.moodyjun.Model.User.Student;
import com.moodyjun.Services.Module.ModuleService;
import com.moodyjun.Services.Result.KeyResultService;
import com.moodyjun.View.Lecturer.KeyResult.KeyResultPage;
import com.moodyjun.View.Util.JResultBox;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeyResultController extends LecturerTemplateController{

    private static KeyResultController keyResultController ;
    private KeyResultPage keyResultPage;
    private KeyResultService keyResultService ;
    private HashMap<Student , Result> studentResultHashMap;

    private KeyResultController(KeyResultPage keyResultPage, KeyResultService keyResultService) {
        super(keyResultPage);
        this.keyResultPage = keyResultPage;
        this.keyResultService = keyResultService;
        availModuleInit(keyResultPage);
        actionListenerInit(keyResultPage);
    }

    private void actionListenerInit(KeyResultPage keyResultPage){
        keyResultPage.getKeyResultPanel().getModuleBox().addItemListener(e->showStudentResultByModuleID(keyResultPage));
        keyResultPage.getKeyResultPanel().getSearchField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { searchResultBoxByStudID(keyResultPage); }
            @Override
            public void removeUpdate(DocumentEvent e) { searchResultBoxByStudID(keyResultPage); }
            @Override
            public void changedUpdate(DocumentEvent e) { searchResultBoxByStudID(keyResultPage); }
        });
        keyResultPage.getKeyResultPanel().getResetButton().addActionListener(e->showStudentResultByModuleID(keyResultPage));
        keyResultPage.getKeyResultPanel().getKeyInButton().addActionListener(e->keyInResult(keyResultPage));
    }

    private void availModuleInit(KeyResultPage keyResultPage){
        Lecturer currentLec = (Lecturer) user;
        for(Object moduleID : currentLec.getModuleList().stream().map(Module::getModuleId).toArray()){
            keyResultPage.getKeyResultPanel().getModuleBox().addItem(moduleID);
        }
        if( currentLec.getModuleList().size()==0) {
            removeKeyResultForm(keyResultPage);
            return;
        }
        keyResultPage.getKeyResultPanel().getModuleBox().setSelectedIndex(0);
        showStudentResultByModuleID(keyResultPage);
    }

    private void showStudentResultByModuleID(KeyResultPage keyResultPage){
        String moduleID = (String) keyResultPage.getKeyResultPanel().getModuleBox().getSelectedItem();
        studentResultHashMap = keyResultService.getStudentResultListByModuleID(moduleID);
        keyResultPage.getKeyResultPanel().getTestPct().setText(Integer.toString(ModuleService.getInstance().getModuleByID(moduleID).getTestMarkPct()));
        keyResultPage.getKeyResultPanel().getExamPct().setText(Integer.toString(ModuleService.getInstance().getModuleByID(moduleID).getExamMarkPct()));
        keyResultPage.getKeyResultPanel().getAssignmentPct().setText(Integer.toString(ModuleService.getInstance().getModuleByID(moduleID).getAssignmentMarkPct()));
        if(studentResultHashMap.size()>0){
            keyResultPage.getKeyResultPanel().getSearchField().setEditable(true);
            keyResultPage.getKeyResultPanel().getSearchField().setText("");
        }
        else keyResultPage.getKeyResultPanel().getSearchField().setEditable(false);
        keyResultPage.getKeyResultPanel().getResultBoxList().clear();
        for(Map.Entry<Student,Result> entry : studentResultHashMap.entrySet()){
            JResultBox resultBox = new JResultBox();
            resultBox.getStudentIDField().setText(entry.getKey().getId().toString());
            resultBox.getStudNameField().setText(entry.getKey().getName());
            resultBox.getTestMarkField().setText(String.valueOf(entry.getValue().getTestMark()));
            resultBox.getExamMarkField().setText(String.valueOf(entry.getValue().getExamMark()));
            resultBox.getAssignmentMarkField().setText(String.valueOf(entry.getValue().getAssignmentMark()));
            resultBox.getCommentField().setText(entry.getValue().getComment());
            keyResultPage.getKeyResultPanel().getResultBoxList().add(resultBox);
        }
        removeResultBoxFromPanel(keyResultPage);
        addResultBoxToPanel(keyResultPage , keyResultPage.getKeyResultPanel().getResultBoxList());
    }

    private void searchResultBoxByStudID(KeyResultPage keyResultPage){
        List<JResultBox> resultBoxList = keyResultPage.getKeyResultPanel().getResultBoxList().stream()
                .filter(jResultBox -> jResultBox.getStudentIDField().getText().startsWith(keyResultPage.getKeyResultPanel().getSearchField().getText()))
                .collect(Collectors.toList());
        removeResultBoxFromPanel(keyResultPage);
        addResultBoxToPanel(keyResultPage, resultBoxList);
    }

    private void addResultBoxToPanel(KeyResultPage keyResultPage, List<JResultBox> resultBoxList){
        keyResultPage.getKeyResultPanel().getGbc().gridx = 2 ;
        keyResultPage.getKeyResultPanel().getGbc().gridy = 6 ;
        keyResultPage.getKeyResultPanel().getGbc().gridwidth = 1 ;
        keyResultPage.getKeyResultPanel().getGbc().fill = GridBagConstraints.HORIZONTAL;
        keyResultPage.getKeyResultPanel().add(keyResultPage.getKeyResultPanel().getResetButton(),keyResultPage.getKeyResultPanel().getGbc());
        keyResultPage.getKeyResultPanel().getGbc().gridx = 0 ;
        keyResultPage.getKeyResultPanel().getGbc().gridy ++ ;
        keyResultPage.getKeyResultPanel().getGbc().gridwidth = 3 ;
        for(JResultBox resultBox : resultBoxList){
            keyResultPage.getKeyResultPanel().add(resultBox,keyResultPage.getKeyResultPanel().getGbc());
            keyResultPage.getKeyResultPanel().getGbc().gridy ++ ;
        }
        keyResultPage.getKeyResultPanel().getGbc().gridx = 2 ;
        keyResultPage.getKeyResultPanel().getGbc().gridwidth = 1 ;
        keyResultPage.getKeyResultPanel().add(keyResultPage.getKeyResultPanel().getKeyInButton(),keyResultPage.getKeyResultPanel().getGbc());
       keyResultToggle(keyResultPage);
    }

    private void keyResultToggle(KeyResultPage keyResultPage){
        if(keyResultPage.getKeyResultPanel().getResultBoxList().size() > 0) {
            keyResultPage.getKeyResultPanel().remove(keyResultPage.getKeyResultPanel().getNotificationLabel());
            keyResultPage.getKeyResultPanel().getResetButton().setEnabled(true);
            keyResultPage.getKeyResultPanel().getKeyInButton().setEnabled(true);
        }else{
            keyResultPage.getKeyResultPanel().getResetButton().setEnabled(false);
            keyResultPage.getKeyResultPanel().getKeyInButton().setEnabled(false);
            keyResultPage.getKeyResultPanel().getGbc().gridx = 1 ;
            keyResultPage.getKeyResultPanel().getGbc().gridwidth = 1 ;
            keyResultPage.getKeyResultPanel().add(keyResultPage.getKeyResultPanel().getNotificationLabel(),keyResultPage.getKeyResultPanel().getGbc());
            keyResultPage.getKeyResultPanel().getNotificationLabel().setText("No student available.");
        }
    }

    private void removeResultBoxFromPanel (KeyResultPage keyResultPage){
        Component[] componentList = keyResultPage.getKeyResultPanel().getComponents();
        for(Component c : componentList){
            if(c instanceof JResultBox ){
                keyResultPage.getKeyResultPanel().remove(c);
            }
        }
        keyResultPage.getKeyResultPanel().revalidate();
        keyResultPage.getKeyResultPanel().repaint();
    }

    private void removeKeyResultForm (KeyResultPage keyResultPage){
        keyResultPage.getKeyResultPanel().removeAll();
        keyResultPage.getKeyResultPanel().revalidate();
        keyResultPage.getKeyResultPanel().repaint();
        keyResultPage.getKeyResultPanel().getGbc().gridx = 0 ;
        keyResultPage.getKeyResultPanel().getGbc().gridy = 0 ;
        keyResultPage.getKeyResultPanel().getGbc().gridwidth = 3 ;
        keyResultPage.getKeyResultPanel().add(keyResultPage.getKeyResultPanel().getTitleLabel(),keyResultPage.getKeyResultPanel().getGbc());
        keyResultPage.getKeyResultPanel().getGbc().gridy ++;
        keyResultPage.getKeyResultPanel().getNotificationLabel().setText("No module available");
        keyResultPage.getKeyResultPanel().add(keyResultPage.getKeyResultPanel().getNotificationLabel(),keyResultPage.getKeyResultPanel().getGbc());
    }

    private void keyInResult(KeyResultPage keyResultPage){
        try{
            for(JResultBox jResultBox : keyResultPage.getKeyResultPanel().getResultBoxList()){
                Student student = studentResultHashMap.keySet().stream()
                        .filter(stud->jResultBox.getStudentIDField().getText().equals(stud.getId().toString()))
                        .findFirst().orElse(null);
                Result result = studentResultHashMap.get(student);

                String test = jResultBox.getTestMarkField().getText();
                String exam = jResultBox.getExamMarkField().getText();
                String ass = jResultBox.getAssignmentMarkField().getText();
                String comment = jResultBox.getCommentField().getText();
                if(test.equals("") || exam.equals("") || ass.equals("") || comment.equals("")) throw new NullValueException();
                result.setTestMark(Integer.parseInt(test));
                result.setExamMark(Integer.parseInt(exam));
                result.setAssignmentMark(Integer.parseInt(ass));
                result.setTotalMark(result.getTestMark()+result.getExamMark()+result.getAssignmentMark());
                result.setComment(comment);
                studentResultHashMap.put(student,result);
            }
            if(keyResultService.saveResult(new ArrayList<>(studentResultHashMap.values()))==1) {
                JOptionPane.showMessageDialog(keyResultPage,"Result save successfully");
                keyResultPage.getKeyResultPanel().getSearchField().setText("");
            } else{JOptionPane.showMessageDialog(keyResultPage,"Please enter valid mark.");}
        } catch (NullValueException e) {
            JOptionPane.showMessageDialog(keyResultPage,e.toString());
        }
    }

    public KeyResultPage getKeyResultPage() {
        return keyResultPage;
    }

    public static KeyResultController getInstance(){
        keyResultController = new KeyResultController(new KeyResultPage(),KeyResultService.getInstance());
        return keyResultController;
    }

}
