package kamera.splash;

import kamera.project.request.ProjectRequest;
import kamera.common.LogsSender;
import kamera.common.ui.CustomSerialize;
import kamera.project.ui.CameraPreview;
import kamera.splash.ui.NewProject;
import kamera.common.RenderInfoFile;

import javax.swing.*;


public class SplashWindow{
    public static int CREATE_NEW_PROJECT = 1;
    public static int EDIT_EXIST_PROJECT = 2;
    private static int status;
    private String projectName;

    private NewProject newProject;

    public static void main(String args[]){
        SwingUtilities.invokeLater(() -> new SplashWindow(status));
    }

    public SplashWindow(int status) {
        this.status = status;
        if(status == CREATE_NEW_PROJECT){
            openWindow();
            newProject.setTitle("Nowy projekt");
            LogsSender.send(LogsSender.LogsIntCode.DODANO_PROJEKT, LogsSender.LogsString.DODANO_PROJEKT);
        }
        if(status == EDIT_EXIST_PROJECT){
        }
    }
    public void setProjectByName(String projectName){
        this.projectName = projectName;

        if(status == EDIT_EXIST_PROJECT){
            LogsSender.send(LogsSender.LogsIntCode.EDYTOWANO_PROJEKT, LogsSender.LogsString.EDYTOWANO_PROJEKT + " " + projectName);
        }

        openWindow();
    }

    private void openWindow() {
        createWindow();
        addListeners();
    }

    private void addListeners() {
        newProject.getOkButton().addActionListener(e -> {
            String info = new CustomSerialize(newProject.getContentPanel()).get();
            ProjectRequest.createNewProject(newProject.getNazwaProjektu().getText(), info);
            ProjectRequest.getInstance().reloadProjectTree();
            newProject.setVisible(false);
        });

        newProject.getCancelButton().addActionListener(e -> newProject.setVisible(false));
    }

    private void createWindow() {
        newProject = new NewProject(CameraPreview.getFrames()[0]);
        newProject.setVisible(true);

        if(status == EDIT_EXIST_PROJECT){
            newProject.getNazwaProjektu().setText(projectName);
            newProject.getNazwaProjektu().disable();

            try {
                String newProjectPath = ProjectRequest.getProjectInfoFile(projectName);
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
