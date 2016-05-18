import view.CameraPreview;
import utility.FileUtils;
import utility.FrameUtility;
import utility.GlobalData;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by TheKing on 05.02.2016.
 */
public class Project {
    private static Project ourInstance;
    private CameraPreview testowy;
    public static ProjectPreviewMain currentProjectPreview;

    public static Project getInstance() {
        return ourInstance;
    }

    public Project(CameraPreview testowy) {
        if (testowy != null) {
            this.testowy = testowy;
            ourInstance = this;
            addListeners();
        }
    }

    private void addListeners() {
        JTree tree = testowy.getProjectsTree();

        testowy.getAddProject().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewProjectMain(NewProjectMain.CREATE_NEW_PROJECT);
            }
        });

        tree.addMouseListener(new JTreeMouseAdapter(tree) {
            @Override
            public void myRightClick(int selRow, TreePath selPath, Rectangle pathBounds) {
                TreeNodeObject child = (TreeNodeObject) ((DefaultMutableTreeNode) selPath.getLastPathComponent()).getUserObject();
                FileUtils fileUtils = new FileUtils(child.getData());
                JPopupMenu menu = new JPopupMenu();

                JMenuItem delete = new JMenuItem("Usuń '" + child.getLabel() + "'");
                delete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        removeProjectByPath(child.getData());
                    }
                });

                JMenuItem manageInfo = new JMenuItem("Edytuj dane");
                manageInfo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        editProjectDataByName(fileUtils.getNameWithouExt());
                    }
                });
                if (fileUtils.isDirectory()) {
                    menu.add(manageInfo);
                }

                JMenuItem manageProject = new JMenuItem("Edytuj projekt");
                manageProject.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        editProjectByName(fileUtils.getNameWithouExt());
                    }
                });
                if (fileUtils.isDirectory()) {
                    menu.add(manageProject);
                }

                JMenuItem openDir = new JMenuItem("Otwórz systemowo");
                openDir.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new FileUtils(child.getData()).openExplorerByPath();
                    }
                });

                menu.add(delete);
                menu.add(openDir);
                menu.show(tree, pathBounds.x, pathBounds.y + pathBounds.height);
            }

            @Override
            public void mySingleClick(int selRow, TreePath selPath) {
                super.mySingleClick(selRow, selPath);
                System.out.println(selRow + " " + selPath);
                TreeNodeObject parent = (TreeNodeObject) ((DefaultMutableTreeNode) selPath.getPathComponent(1)).getUserObject();
                System.out.println(parent.getLabel());

                if (selPath.getPathCount() > 2) {
                    TreeNodeObject child = (TreeNodeObject) ((DefaultMutableTreeNode) selPath.getPathComponent(2)).getUserObject();
                    System.out.println(child.getLabel());
                }
            }

            @Override
            public void myDoubleClick(int selRow, TreePath selPath) {
                super.myDoubleClick(selRow, selPath);
                System.out.println(selRow + " " + selPath);
                TreeNodeObject parent = (TreeNodeObject) ((DefaultMutableTreeNode) selPath.getPathComponent(1)).getUserObject();

                GlobalData.setCurrentProjectDir(parent.getLabel());
                imitateOpenProject(parent.getLabel());
            }
        });
    }

    public void removeProjectByName(String projectName) {
        String path = GlobalData.CURRENT_DIR_FILE.getAbsolutePath() + "\\" + projectName;
        removeProjectByPath(path);
    }

    public void removeProjectByPath(String path) {
        JDialog.setDefaultLookAndFeelDecorated(true);
        int response = JOptionPane.showConfirmDialog(null, "Operacja usuwania jest nieodwracalna. Czy na pewno chcesz usunąc projekt '" + path + "'?", "Potwierdź",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            System.out.println("No button clicked");
        } else if (response == JOptionPane.YES_OPTION) {
            LogsSender.send(LogsIntCode.USUNIETO_PROJEKT, LogsString.USUNIETO_PROJEKT + " " + path);
            removeFileFromPath(path);
            reloadProjectTree();
            imitateCloseProject();
        }
    }

    public void editProjectDataByName(String projectName) {
        NewProjectMain newProjectMain = new NewProjectMain(NewProjectMain.EDIT_EXIST_PROJECT);
        newProjectMain.setProjectByName(projectName);
    }

    public void editProjectByName(String projectName) {
        currentProjectPreview = new ProjectPreviewMain(projectName);
    }

    public void imitateCloseProject() {
        testowy.getLeftSplitPane().setVisible(true);
        Project.getInstance().hideProjectEditor();
        testowy.setTitle("Otwórz projekt");
        testowy.getMenuProject().setVisible(false);
        testowy.setSize(500, 500);

        FrameUtility.centreWindow(testowy);
    }

    public void imitateOpenProject(String label) {
        testowy.setTitle("Projekt: " + label);
        testowy.getLeftSplitPane().setVisible(false);
        Project.getInstance().showProjectEditor();
        testowy.getMenuProject().setVisible(true);

        FrameUtility.maximize(testowy);
    }


    public void hideProjectEditor() {
        testowy.getRightSplitPane().setVisible(false);
    }

    public void showProjectEditor() {
        testowy.getRightSplitPane().setVisible(true);
    }


    public static String createNewProject(String projectName, String info) {
        File dir = GlobalData.CURRENT_DIR_FILE;
        dir.mkdirs();

        GlobalData.setCurrentProjectDir(projectName);
        dir = GlobalData.getCurrentProjectFile();
        dir.mkdirs();
        try {
            File infoFile = new File(dir + "\\" + "info.txt");
            infoFile.createNewFile();
            Files.write(Paths.get(infoFile.getAbsolutePath()), info.getBytes(), StandardOpenOption.WRITE);
        } catch (IOException e) {
        }

        Project.getInstance().imitateOpenProject(projectName);
        return dir.getAbsolutePath();
    }

    public static String getProjectInfoFile(String projectName) {
        File dir = GlobalData.CURRENT_DIR_FILE;
        File projectInfo = new File(dir.getAbsolutePath() + "\\" + projectName + "\\" + "info.txt");
        return projectInfo.getAbsolutePath();
    }

    public static void removeProject(String projectName) {
        File dir = getDirOfProject(projectName);
        dir.delete();
    }

    public static void removeFileFromPath(String filePath) {
        File f = new File(filePath);
        if (f.isDirectory()) {
            for (File c : f.listFiles())
                removeFileFromPath(c.getAbsolutePath());
        }
        f.delete();
    }

    public static File getDirOfProject(String projectName) {
        return new File(System.getProperty("user.home"), "Videos" + "\\" + projectName);
    }

    public void reloadProjectTree() {
        DefaultTreeModel model = (DefaultTreeModel) testowy.getProjectsTree().getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        root.removeAllChildren();

        try {
            reloadFolders(root, GlobalData.CURRENT_DIR_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.reload(root);
        testowy.getProjectsTree().setRootVisible(false);
        testowy.getProjectsTree().setCellRenderer(new TreeNodeObjectRenderer());
    }

    private void reloadFolders(DefaultMutableTreeNode root, File CURRENT_DIR) throws IOException {
        Files.walk(Paths.get(CURRENT_DIR.getAbsolutePath())).forEach(filePath -> {
            if (Files.isDirectory(filePath)) {
                try {
                    String name = new FileUtils(filePath.toString()).getNameWithouExt();
                    if (!name.equals("Videos"))
                        reloadFiles(root, filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void reloadFiles(DefaultMutableTreeNode root, Path filePath) throws IOException {
        TreeNodeObject obj = new TreeNodeObject(new FileUtils(filePath.toString()).getNameWithouExt(), filePath.toString());
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(obj);

        Files.walk(Paths.get(String.valueOf(filePath))).forEach(filePath2 -> {
            if (Files.isRegularFile(filePath2) && filePath2.toString().contains(".mp4")) {
                TreeNodeObject obj2 = new TreeNodeObject(new FileUtils(filePath2.toString()).getNameWithouExt(), filePath2.toString());
                node.add(new DefaultMutableTreeNode(obj2));
            }
        });
        root.add(node);
    }
}
