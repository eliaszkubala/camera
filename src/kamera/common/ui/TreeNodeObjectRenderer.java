package kamera.common.ui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

/**
 * Created by TheKing on 06.02.2016.
 */
public class TreeNodeObjectRenderer extends DefaultTreeCellRenderer {
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        if(node.getUserObject() instanceof TreeNodeObject) {
            TreeNodeObject obj = (TreeNodeObject) node.getUserObject();
            return super.getTreeCellRendererComponent(tree, obj.getLabel(), selected, expanded, leaf, row, hasFocus);
        }

        return super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
    }
}
