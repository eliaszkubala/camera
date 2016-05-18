import view.CameraPreview;
import view.NewProject;
import utility.RenderInfoFile;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;


public class NewProjectMain {
    public static int CREATE_NEW_PROJECT = 1;
    public static int EDIT_EXIST_PROJECT = 2;
    private static int status;
    private String projectName;

    private NewProject newProject;

    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NewProjectMain(status);
            }
        });
    }

    public NewProjectMain(int status) {
        this.status = status;
        if(status == CREATE_NEW_PROJECT){
            openWindow();
            newProject.setTitle("Nowy projekt");
            LogsSender.send(LogsIntCode.DODANO_PROJEKT, LogsString.DODANO_PROJEKT);
        }
        if(status == EDIT_EXIST_PROJECT){
        }
    }
    public void setProjectByName(String projectName){
        this.projectName = projectName;

        if(status == EDIT_EXIST_PROJECT){
            LogsSender.send(LogsIntCode.EDYTOWANO_PROJEKT, LogsString.EDYTOWANO_PROJEKT + " " + projectName);
        }

        openWindow();
    }

    private void openWindow() {
        createWindow();
        addListeners();
        addFormatter();
    }

    private void addFormatter() {
        try {
            MaskFormatter dateMask = new MaskFormatter("####/##/##");
            MaskFormatter dateMask2 = new MaskFormatter("####/##/##");

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void addListeners() {
        newProject.getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String info = new CustomSerialize(newProject.getContentPanel()).get();
                String newProjectPath = Project.createNewProject(newProject.getNazwaProjektu().getText(), info);
                Project.getInstance().reloadProjectTree();
                newProject.setVisible(false);
            }
        });

        newProject.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newProject.setVisible(false);
            }
        });
    }



    private void createWindow() {
        newProject = new NewProject(CameraPreview.getFrames()[0]);
        newProject.setVisible(true);

        if(status == EDIT_EXIST_PROJECT){
            newProject.getNazwaProjektu().setText(projectName);
            newProject.getNazwaProjektu().disable();

            try {
                String newProjectPath = Project.getProjectInfoFile(projectName);
                System.out.println(projectName);
                System.out.println(newProjectPath);
                String info = new RenderInfoFile(newProjectPath).getString();
                new CustomSerialize(newProject.getContentPanel()).set(info);
            }catch(Exception e){

            }
            newProject.setTitle("Edycja projektu " + projectName);
        }
    }
}
