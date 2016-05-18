import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by TheKing on 06.02.2016.
 */
interface JTreeMouseAdapterInterface{
    void myRightClick(int selRow, TreePath selPath, Rectangle pathBounds);
    void myDoubleClick(int selRow, TreePath selPath);
    void mySingleClick(int selRow, TreePath selPath);
}

public class JTreeMouseAdapter extends MouseAdapter implements JTreeMouseAdapterInterface {

    private JTree tree = null;

    public JTreeMouseAdapter(JTree tree) {
        this.tree = tree;
    }

    public void mousePressed(MouseEvent e) {

        int selRow = tree.getRowForLocation(e.getX(), e.getY());
        TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());

        if (SwingUtilities.isRightMouseButton(e)) {
            TreePath path = tree.getPathForLocation(e.getX(), e.getY());
            Rectangle pathBounds = tree.getUI().getPathBounds(tree, path);
            if (pathBounds != null && pathBounds.contains(e.getX(), e.getY())) {
                myRightClick(selRow, selPath, pathBounds);
            }
        }

        if (selRow != -1) {
            if (e.getClickCount() == 1) {
                mySingleClick(selRow, selPath);
            } else if (e.getClickCount() == 2) {
                myDoubleClick(selRow, selPath);
            }
        }
    }

    @Override
    public void myDoubleClick(int selRow, TreePath selPath) {

    }

    @Override
    public void myRightClick(int selRow, TreePath selPath, Rectangle pathBounds) {
        TreeNodeObject child = (TreeNodeObject) ((DefaultMutableTreeNode) selPath.getLastPathComponent()).getUserObject();
    }

    @Override
    public void mySingleClick(int selRow, TreePath selPath) {
        //System.out.println(selRow + " " + selPath);
        TreeNodeObject parent = (TreeNodeObject) ((DefaultMutableTreeNode) selPath.getPathComponent(1)).getUserObject();
        //System.out.println(parent.getLabel());

        if (selPath.getPathCount() > 2) {
            TreeNodeObject child = (TreeNodeObject) ((DefaultMutableTreeNode) selPath.getPathComponent(2)).getUserObject();
            System.out.println(child.getLabel());
        }
    }
}
