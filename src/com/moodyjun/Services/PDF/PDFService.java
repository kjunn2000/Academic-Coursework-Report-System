package com.moodyjun.Services.PDF;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import com.moodyjun.Dao.Module.ModuleDao;
import com.moodyjun.Dao.Module.ResultDao;
import com.moodyjun.Dao.System.LogDao;
import com.moodyjun.Dao.User.AdminDao;
import com.moodyjun.Dao.User.LecturerDao;
import com.moodyjun.Dao.User.StudentDao;
import com.moodyjun.Dao.User.UserDao;
import com.moodyjun.Model.Module.Module;
import com.moodyjun.Model.Module.Result;
import com.moodyjun.Model.System.LogData;
import com.moodyjun.Model.User.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PDFService {

    private static PDFService pdfService;
    private Document document ;
    private PdfWriter writer;
    private AdminDao adminDao;
    private LecturerDao lecturerDao;
    private StudentDao studentDao;
    private ModuleDao moduleDao;
    private ResultDao resultDao;
    private LogDao logDao;
    private Font font1 ;
    private Font font2 ;
    private Font font3 ;
    private Font font4;
    private Font font5;
    private DateTimeFormatter dateTimeFormatter;

    public PDFService(){
        adminDao = AdminDao.getInstance();
        lecturerDao = LecturerDao.getInstance();
        studentDao = StudentDao.getInstance();
        moduleDao = ModuleDao.getInstance();
        resultDao = ResultDao.getInstance();
        logDao = LogDao.getInstance();
        font1 = FontFactory.getFont(FontFactory.HELVETICA, 22, Font.BOLD, Color.BLACK);
        font2 = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.ITALIC, Color.BLACK);
        font3 = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.ITALIC, Color.BLACK);
        font4 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.TIMES_ROMAN, Color.darkGray);
        font5 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.TIMES_ROMAN, Color.black);
        dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE yyyy-MM-dd HH:mm:ss a");
    }

    public int generateLogFile(){
        List<LogData> logDataList = logDao.getLogDataList().stream().filter(logData -> logData.getLogOutTime()!=null).collect(Collectors.toList());
        try {
            document = new Document();
            writer = PdfWriter.getInstance(document, new FileOutputStream("LogFile.pdf"));
            document.open();
            document.add(new Phrase("Log File", font1));
            document.add(Chunk.NEWLINE);
            document.add( new Chunk("Log File generation time: "+dateTimeFormatter.format(LocalDateTime.now())
                    , font4));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            PdfPTable logTable = new PdfPTable(5);
            logTable.setWidthPercentage(90);
            logTable.setSpacingBefore(10f);
            logTable.setSpacingAfter(10f);
            logTable.setWidths(new float[]{2f,1f,2f,2f,2f});
            List<String> logTableData = new ArrayList<>();
            logTableData.add("LogID");
            logTableData.add("UserID");
            logTableData.add("User Name");
            logTableData.add("Log In Time");
            logTableData.add("Log Out Time");


            for(LogData logData : logDataList){
                logTableData.add(logData.getLogID().toString());
                logTableData.add(logData.getUser().getId().toString());
                logTableData.add(logData.getUser().getName());
                logTableData.add(dateTimeFormatter.format(logData.getLogInTime()));
                logTableData.add(dateTimeFormatter.format(logData.getLogOutTime()));
            }

            for(String data : logTableData){
                PdfPCell cell ;
                cell = new PdfPCell(new Phrase(data, font5));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setUseBorderPadding(true);
                logTable.addCell(cell);
            }

            document.add(logTable);
            document.close();
            return 1;
        } catch (FileNotFoundException | DocumentException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return 0 ;
    }

    public int generateAgeReport(){
        List<User> adminList = adminDao.getUserList() ;
        List<User> lecturerList = lecturerDao.getUserList();
        List<User> studentList = studentDao.getUserList();
        long totalBelow20 = adminList.stream().filter(admin->admin.getAge()<20).count()+lecturerList.stream().filter(lec->lec.getAge()<20).count()
                +studentList.stream().filter(stud->stud.getAge()<20).count();
        long total20_25 = adminList.stream().filter(admin->admin.getAge()>=20).filter(admin->admin.getAge()<=25).count()
                +lecturerList.stream().filter(lec->lec.getAge()>=20).filter(lec->lec.getAge()<=25).count()
                +studentList.stream().filter(stud->stud.getAge()>=20).filter(stud->stud.getAge()<=25).count();
        long total25_30 = adminList.stream().filter(admin->admin.getAge()>=25).filter(admin->admin.getAge()<=30).count()
                +lecturerList.stream().filter(lec->lec.getAge()>=25).filter(lec->lec.getAge()<=30).count()
                +studentList.stream().filter(stud->stud.getAge()>=25).filter(stud->stud.getAge()<=30).count();
        long totalAbove30 = adminList.stream().filter(admin->admin.getAge()>30).count()+lecturerList.stream().filter(lec->lec.getAge()>30).count()
                +studentList.stream().filter(stud->stud.getAge()>30).count();
        float totalUser = totalBelow20+total20_25+total25_30+totalAbove30;
        try{
            document = new Document();
            writer = PdfWriter.getInstance(document, new FileOutputStream("AgeReport.pdf"));
            document.open();
            document.add(new Phrase("Age Report", font1));
            document.add(Chunk.NEWLINE);
            document.add( new Chunk("Report generation time: "+dateTimeFormatter.format(LocalDateTime.now())
                    , font4));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);


            PdfPTable adminAgeTable = new PdfPTable(5);
            adminAgeTable.setWidthPercentage(60);
            adminAgeTable.setSpacingBefore(10f);
            adminAgeTable.setSpacingAfter(10f);
            adminAgeTable.setWidths(new float[]{1f,1f,1f,1f,1f});
            ArrayList<String> adminAgeData = new ArrayList<>();
            adminAgeData.add("Age");
            adminAgeData.add("Below 20");
            adminAgeData.add("20-25");
            adminAgeData.add("26-30");
            adminAgeData.add("Above 30");
            adminAgeData.add("Count");
            adminAgeData.add(String.valueOf(adminList.stream().filter(admin->admin.getAge()<20).count()));
            adminAgeData.add(String.valueOf(adminList.stream().filter(admin->admin.getAge()>=20).filter(admin->admin.getAge()<=25).count()));
            adminAgeData.add(String.valueOf(adminList.stream().filter(admin->admin.getAge()>=25).filter(admin->admin.getAge()<=30).count()));
            adminAgeData.add(String.valueOf(adminList.stream().filter(admin->admin.getAge()>30).count()));

            PdfPTable lecturerAgeTable = new PdfPTable(5);
            lecturerAgeTable.setWidthPercentage(60);
            lecturerAgeTable.setSpacingBefore(10f);
            lecturerAgeTable.setSpacingAfter(10f);
            lecturerAgeTable.setWidths(new float[]{1f,1f,1f,1f,1f});
            ArrayList<String> lecturerAgeData = new ArrayList<>();
            lecturerAgeData.add("Age");
            lecturerAgeData.add("Below 20");
            lecturerAgeData.add("20-25");
            lecturerAgeData.add("26-30");
            lecturerAgeData.add("Above 30");
            lecturerAgeData.add("Count");
            lecturerAgeData.add(String.valueOf(lecturerList.stream().filter(lec->lec.getAge()<20).count()));
            lecturerAgeData.add(String.valueOf(lecturerList.stream().filter(lec->lec.getAge()>=20).filter(lec->lec.getAge()<=25).count()));
            lecturerAgeData.add(String.valueOf(lecturerList.stream().filter(lec->lec.getAge()>=25).filter(lec->lec.getAge()<=30).count()));
            lecturerAgeData.add(String.valueOf(lecturerList.stream().filter(lec->lec.getAge()>30).count()));

            PdfPTable studentAgeTable = new PdfPTable(5);
            studentAgeTable.setWidthPercentage(60);
            studentAgeTable.setSpacingBefore(10f);
            studentAgeTable.setSpacingAfter(10f);
            studentAgeTable.setWidths(new float[]{1f,1f,1f,1f,1f});
            ArrayList<String> studentAgeData = new ArrayList<>();
            studentAgeData.add("Age");
            studentAgeData.add("Below 20");
            studentAgeData.add("20-25");
            studentAgeData.add("26-30");
            studentAgeData.add("Above 30");
            studentAgeData.add("Count");
            studentAgeData.add(String.valueOf(studentList.stream().filter(stud->stud.getAge()<20).count()));
            studentAgeData.add(String.valueOf(studentList.stream().filter(stud->stud.getAge()>=20).filter(stud->stud.getAge()<=25).count()));
            studentAgeData.add(String.valueOf(studentList.stream().filter(stud->stud.getAge()>=25).filter(stud->stud.getAge()<=30).count()));
            studentAgeData.add(String.valueOf(studentList.stream().filter(stud->stud.getAge()>30).count()));

            for(String data : adminAgeData){
                PdfPCell cell ;
                cell = new PdfPCell(new Phrase(data, font5));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setUseBorderPadding(true);
                adminAgeTable.addCell(cell);
            }

            for(String data : lecturerAgeData){
                PdfPCell cell ;
                cell = new PdfPCell(new Phrase(data, font5));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setUseBorderPadding(true);
                lecturerAgeTable.addCell(cell);
            }

            for(String data : studentAgeData){
                PdfPCell cell ;
                cell = new PdfPCell(new Phrase(data, font5));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setUseBorderPadding(true);
                studentAgeTable.addCell(cell);
            }


            document.add(new Phrase("Admin Age",font2));
            document.add(adminAgeTable);
            document.add(Chunk.NEWLINE);

            document.add(new Phrase("Lecturer Age",font2));
            document.add(lecturerAgeTable);
            document.add(Chunk.NEWLINE);
            document.add(new Phrase("Student Age",font2));
            document.add(studentAgeTable);
            document.add(Chunk.NEWLINE);
            document.add( Chunk.NEWLINE );

            document.add(new Phrase("Age below 20 Percentage (%) : "+new DecimalFormat("0.00").format(totalBelow20/totalUser*100),font2));
            document.add( Chunk.NEWLINE );
            document.add(new Phrase("Age between 20-25 Percentage (%) : "+new DecimalFormat("0.00").format(total20_25/totalUser*100),font2));
            document.add( Chunk.NEWLINE );
            document.add(new Phrase("Age between 25-30 Percentage (%) : "+new DecimalFormat("0.00").format(total25_30/totalUser*100),font2));
            document.add( Chunk.NEWLINE );
            document.add(new Phrase("Age above 30 Percentage (%) : "+new DecimalFormat("0.00").format(totalAbove30/totalUser*100),font2));
            document.close();
            return 1;
        } catch (Exception e){
            System.out.println(e);
        }


        return 0;
    }

    public int generateGenderReport(){

        int maleAdminCount = (int) adminDao.getUserList().stream().filter(user -> user.getGender() == Gender.MALE).count();
        int femaleAdminCount = (int) adminDao.getUserList().stream().filter(user -> user.getGender() == Gender.FEMALE).count();
        int maleLecturerCount = (int) lecturerDao.getUserList().stream().filter(user -> user.getGender() == Gender.MALE).count();
        int femaleLecturerCount = (int) lecturerDao.getUserList().stream().filter(user -> user.getGender() == Gender.FEMALE).count();
        int maleStudentCount = (int) studentDao.getUserList().stream().filter(user -> user.getGender() == Gender.MALE).count();
        int femaleStudentCount = (int) studentDao.getUserList().stream().filter(user -> user.getGender() == Gender.FEMALE).count();
        int totalMale =maleAdminCount+maleLecturerCount+maleStudentCount;
        int totalFemale = femaleAdminCount+femaleLecturerCount+femaleStudentCount;
        float totalUser = totalMale+totalFemale;

        try{
            document = new Document();
            writer = PdfWriter.getInstance(document, new FileOutputStream("GenderReport.pdf"));
            document.open();


            document.add(new Phrase("Gender Report", font1));

            PdfPTable adminTable = new PdfPTable(2);
            adminTable.setWidthPercentage(60);
            adminTable.setSpacingBefore(10f);
            adminTable.setSpacingAfter(10f);
            adminTable.setWidths(new float[]{1f,2f});
            ArrayList<String> adminData = new ArrayList<>();
            adminData.add("Gender");
            adminData.add("Count");
            adminData.add("Male");
            adminData.add(String.valueOf(maleAdminCount));
            adminData.add("Female");
            adminData.add(String.valueOf(femaleAdminCount));
            adminData.add("Total");
            adminData.add(String.valueOf(maleAdminCount+femaleAdminCount));

            PdfPTable lecturerTable = new PdfPTable(2);
            lecturerTable.setWidthPercentage(60);
            lecturerTable.setSpacingBefore(10f);
            lecturerTable.setSpacingAfter(10f);
            lecturerTable.setWidths(new float[]{1f,2f});
            ArrayList<String> lecData = new ArrayList<>();
            lecData.add("Gender");
            lecData.add("Count");
            lecData.add("Male");
            lecData.add(String.valueOf(maleLecturerCount));
            lecData.add("Female");
            lecData.add(String.valueOf(femaleLecturerCount));
            lecData.add("Total");
            lecData.add(String.valueOf(maleLecturerCount+femaleLecturerCount));

            PdfPTable studTable = new PdfPTable(2);
            studTable.setWidthPercentage(60);
            studTable.setSpacingBefore(10f);
            studTable.setSpacingAfter(10f);
            studTable.setWidths(new float[]{1f,2f});
            ArrayList<String> studData = new ArrayList<>();
            studData.add("Gender");
            studData.add("Count");
            studData.add("Male");
            studData.add(Integer.toString(maleStudentCount));
            studData.add("Female");
            studData.add(Integer.toString(femaleStudentCount));
            studData.add("Total");
            studData.add(String.valueOf(maleStudentCount+femaleStudentCount));

            PdfPTable totalTable = new PdfPTable(2);
            totalTable.setWidthPercentage(60);
            totalTable.setSpacingBefore(10f);
            totalTable.setSpacingAfter(10f);
            totalTable.setWidths(new float[]{1f,2f});
            ArrayList<String> allData = new ArrayList<>();
            allData.add("Gender");
            allData.add("Count");
            allData.add("Male");
            allData.add(Integer.toString(totalMale));
            allData.add("Female");
            allData.add(Integer.toString(totalFemale));
            allData.add("Total");
            allData.add(String.valueOf(totalMale+totalFemale));

            for(String data : adminData){
                PdfPCell cell ;
                cell = new PdfPCell(new Phrase(data, font5));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setUseBorderPadding(true);
                adminTable.addCell(cell);
            }
            for(String data : lecData){
                PdfPCell cell ;
                cell = new PdfPCell(new Phrase(data, font5));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setUseBorderPadding(true);
                lecturerTable.addCell(cell);
            }
            for(String data : studData){
                PdfPCell cell ;
                cell = new PdfPCell(new Phrase(data, font5));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setUseBorderPadding(true);
                studTable.addCell(cell);
            }
            for(String data : allData){
                PdfPCell cell ;
                cell = new PdfPCell(new Phrase(data, font5));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setUseBorderPadding(true);
                totalTable.addCell(cell);
            }

            document.add( Chunk.NEWLINE );
            document.add( new Chunk("Report generation time: "+dateTimeFormatter.format(LocalDateTime.now())
            , font4));
            document.add( Chunk.NEWLINE );
            document.add( Chunk.NEWLINE );
            document.add(new Phrase("Admin",font2));
            document.add(adminTable);
            document.add(new Phrase("Lecturer",font2));
            document.add(lecturerTable);
            document.add(new Phrase("Student",font2));
            document.add(studTable);
            document.add(new Phrase("Summary",font2));
            document.add(totalTable);
            document.add( Chunk.NEWLINE );
            String malePct = new DecimalFormat("0.00").format(totalMale/totalUser*100);
            String femalePct = new DecimalFormat("0.00").format(totalFemale/totalUser*100);
            document.add(new Phrase("Male Percentage (%) : "+malePct,font2));
            document.add( Chunk.NEWLINE );
            document.add( Chunk.NEWLINE );
            document.add(new Phrase("Female Percentage (%) : "+femalePct,font2));
            document.close();
            return 1;
        } catch (Exception e){
            System.out.println(e);
        }
      return 0;
    }

    public int generateModuleReport(){

        List<Module> level1ModuleList = moduleDao.getModuleList().stream().filter(module->module.getLevel()==1).collect(Collectors.toList());
        List<Module> level2ModuleList = moduleDao.getModuleList().stream().filter(module->module.getLevel()==2).collect(Collectors.toList());
        List<Module> level3ModuleList = moduleDao.getModuleList().stream().filter(module->module.getLevel()==3).collect(Collectors.toList());
        List<Module> level4ModuleList = moduleDao.getModuleList().stream().filter(module->module.getLevel()==4).collect(Collectors.toList());


        try {
            document = new Document();
            writer = PdfWriter.getInstance(document, new FileOutputStream("ModuleReport.pdf"));
            document.open();
            document.add(new Phrase("Module Report", font1));
            document.add( Chunk.NEWLINE );
            document.add( new Chunk("Report generation time: "+dateTimeFormatter.format(LocalDateTime.now())
                    , font4));
            document.add( Chunk.NEWLINE );
            document.add( Chunk.NEWLINE );
            document.add(new Phrase("Level 1",font2));
            document.add( Chunk.NEWLINE );

            PdfPTable level1ModuleTable = new PdfPTable(4);
            level1ModuleTable.setWidthPercentage(80);
            level1ModuleTable.setSpacingBefore(10f);
            level1ModuleTable.setSpacingAfter(10f);
            level1ModuleTable.setWidths(new float[]{1f,2f,2f,1f});

            List<String> level1Data = new ArrayList<>();
            level1Data.add("Module ID");
            level1Data.add("Module Name");
            level1Data.add("Intake Codes");
            level1Data.add("Num Of Student");

            for(Module module : level1ModuleList){
                level1Data.add(module.getModuleId());
                level1Data.add(module.getModuleName());
                level1Data.add(module.getIntakeCodeList().toString());
                level1Data.add(Integer.toString(module.getNumOfStud()));
            }

            PdfPTable level2ModuleTable = new PdfPTable(4);
            level2ModuleTable.setWidthPercentage(80);
            level2ModuleTable.setSpacingBefore(10f);
            level2ModuleTable.setSpacingAfter(10f);
            level2ModuleTable.setWidths(new float[]{1f,2f,2f,1f});

            List<String> level2Data = new ArrayList<>();
            level2Data.add("Module ID");
            level2Data.add("Module Name");
            level2Data.add("Intake Codes");
            level2Data.add("Num Of Student");

            for(Module module : level2ModuleList){
                level2Data.add(module.getModuleId());
                level2Data.add(module.getModuleName());
                level2Data.add(module.getIntakeCodeList().toString());
                level2Data.add(Integer.toString(module.getNumOfStud()));
            }

            PdfPTable level3ModuleTable = new PdfPTable(4);
            level3ModuleTable.setWidthPercentage(80);
            level3ModuleTable.setSpacingBefore(10f);
            level3ModuleTable.setSpacingAfter(10f);
            level3ModuleTable.setWidths(new float[]{1f,2f,2f,1f});

            List<String> level3Data = new ArrayList<>();
            level3Data.add("Module ID");
            level3Data.add("Module Name");
            level3Data.add("Intake Codes");
            level3Data.add("Num Of Student");

            for(Module module : level3ModuleList){
                level3Data.add(module.getModuleId());
                level3Data.add(module.getModuleName());
                level3Data.add(module.getIntakeCodeList().toString());
                level3Data.add(Integer.toString(module.getNumOfStud()));
            }

            for(String data : level1Data){
                PdfPCell cell;
                cell = new PdfPCell(new Phrase(data, font5));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setUseBorderPadding(true);
                level1ModuleTable.addCell(cell);
            }

            for(String data : level2Data){
                PdfPCell cell;
                cell = new PdfPCell(new Phrase(data, font5));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setUseBorderPadding(true);
                level2ModuleTable.addCell(cell);
            }

            for(String data : level3Data){
                PdfPCell cell;
                cell = new PdfPCell(new Phrase(data, font5));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setUseBorderPadding(true);
                level3ModuleTable.addCell(cell);
            }

            document.add(new Chunk("Number of module in level 1: "+level1ModuleList.size(),font3));
            document.add( Chunk.NEWLINE );
            document.add(level1ModuleTable);
            document.add( Chunk.NEWLINE );
            document.add(new Phrase("Level 2",font2));
            document.add( Chunk.NEWLINE );
            document.add(new Chunk("Number of module in level 2: "+level2ModuleList.size(),font3));
            document.add( Chunk.NEWLINE );
            document.add(level2ModuleTable);
            document.add( Chunk.NEWLINE );
            document.add(new Phrase("Level 3",font2));
            document.add( Chunk.NEWLINE );
            document.add(new Chunk("Number of module in level 3: "+level3ModuleList.size(),font3));
            document.add( Chunk.NEWLINE );
            document.add(level3ModuleTable);
            document.add( Chunk.NEWLINE );

            document.close();
            return 1;
        } catch (FileNotFoundException | DocumentException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        return 0;
    }

    public int generateUserTypeReport(){


        try {
            document = new Document();
            writer = PdfWriter.getInstance(document, new FileOutputStream("UserTypeReport.pdf"));
            document.open();
            document.add(new Phrase("User Type Report", font1));
            document.add( Chunk.NEWLINE );
            document.add( new Chunk("Report generation time: "+dateTimeFormatter.format(LocalDateTime.now())
                    , font4));
            document.add( Chunk.NEWLINE );
            document.add( Chunk.NEWLINE );
            document.add(new Phrase("User Type",font2));
            document.add( Chunk.NEWLINE );

            PdfPTable userTypeTable = new PdfPTable(2);
            userTypeTable.setWidthPercentage(60);
            userTypeTable.setSpacingBefore(10f);
            userTypeTable.setSpacingAfter(10f);
            userTypeTable.setWidths(new float[]{2f,1f});

            List<String> dataList = new ArrayList<>();
            dataList.add("Role");
            dataList.add("Count");
            dataList.add("Admin");
            dataList.add(String.valueOf((long) adminDao.getUserList().size()));
            dataList.add("Lecturer");
            dataList.add(String.valueOf((long) lecturerDao.getUserList().size()));
            dataList.add("Student Level1");
            dataList.add(String.valueOf(studentDao.getUserList().stream().filter(user->((Student)user).getLevel()==1).count()));
            dataList.add("Student Level2");
            dataList.add(String.valueOf(studentDao.getUserList().stream().filter(user->((Student)user).getLevel()==2).count()));
            dataList.add("Student Level3");
            dataList.add(String.valueOf(studentDao.getUserList().stream().filter(user->((Student)user).getLevel()==3).count()));

            for(String data : dataList){
                PdfPCell cell;
                cell = new PdfPCell(new Phrase(data, font5));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setUseBorderPadding(true);
                userTypeTable.addCell(cell);
            }
            document.add(userTypeTable);
            document.close();
            return 1;
        } catch (FileNotFoundException | DocumentException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return 0;
    }



    public int generateStudentResultReport(){

        try{
            document = new Document();
            writer = PdfWriter.getInstance(document, new FileOutputStream("StudentResultReport.pdf"));
            document.open();
            document.add(new Phrase("Result Report", font1));
            document.add( Chunk.NEWLINE );
            document.add( new Chunk("Report generation time: "+dateTimeFormatter.format(LocalDateTime.now())
                    , font4));
            document.add( Chunk.NEWLINE );
            document.add( Chunk.NEWLINE );
            document.add(new Phrase("Pass/Fail Result",font2));
            document.add( Chunk.NEWLINE );
            PdfPTable resultTable = new PdfPTable(5);
            resultTable.setWidthPercentage(80);
            resultTable.setSpacingBefore(10f);
            resultTable.setSpacingAfter(10f);
            resultTable.setWidths(new float[]{1f,3f,1f,1f,1f});

            List<String> resultData = new ArrayList<>();
            resultData.add("Module ID");
            resultData.add("Module Name");
            resultData.add("Pass");
            resultData.add("Fail");
            resultData.add("Total");

            float totalPass = 0 ;
            float totalFail = 0 ;
            for(Module module : moduleDao.getModuleList()){
                resultData.add(module.getModuleId());
                resultData.add(module.getModuleName());
                List<Result> resultList = resultDao.getResultList().stream()
                        .filter(result->result.getModule().getModuleName().equals(module.getModuleName()))
                        .filter(result->result.getTotalMark()!=0)
                        .collect(Collectors.toList());
                int totalNumOfStud = resultList.size();
                int numOfStudPass = (int) resultList.stream().filter(result -> result.getTotalMark()>=50).count();
                totalPass+=numOfStudPass;
                int numOfStudFail = totalNumOfStud-numOfStudPass;
                totalFail+=numOfStudFail;
                resultData.add(Integer.toString(numOfStudPass));
                resultData.add(Integer.toString(numOfStudFail));
                resultData.add(Integer.toString(totalNumOfStud));
                resultData.add("");
                resultData.add("");
                resultData.add("");
                resultData.add("");
                resultData.add("");
            }

            for(String data : resultData){
                PdfPCell cell;
                cell = new PdfPCell(new Phrase(data, font5));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setUseBorderPadding(true);
                resultTable.addCell(cell);
            }
            PdfPTable summaryTable = new PdfPTable(3);
            summaryTable.setWidthPercentage(60);
            summaryTable.setSpacingBefore(10f);
            summaryTable.setSpacingAfter(10f);
            summaryTable.setWidths(new float[]{2f,1f,1f});
            List<String> summaryData = new ArrayList<>();
            summaryData.add("");
            summaryData.add("Pass");
            summaryData.add("Fail");
            summaryData.add("Count");
            summaryData.add(String.valueOf((int)totalPass));
            summaryData.add(String.valueOf((int)totalFail));
            summaryData.add("Percentage (%)");
            summaryData.add(String.valueOf(new DecimalFormat("0.00").format(totalPass/(totalPass+totalFail)*100)));
            summaryData.add(String.valueOf(new DecimalFormat("0.00").format(totalFail/(totalPass+totalFail)*100)));

            for(String data : summaryData){
                PdfPCell cell;
                cell = new PdfPCell(new Phrase(data, font5));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setUseBorderPadding(true);
                summaryTable.addCell(cell);
            }

            document.add(resultTable);
            document.add(Chunk.NEWLINE);
            document.add(new Phrase("Summary Result",font2));
            document.add( Chunk.NEWLINE );
            document.add(summaryTable);
            document.close();
            return 1;
        } catch (IOException | DocumentException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }


       return 0 ;
    }




    public static PDFService getInstance(){
        if(pdfService == null){
            synchronized (PDFService.class){
                if(pdfService ==null) pdfService =new PDFService();

            }
        }
        return pdfService;
    }

}
