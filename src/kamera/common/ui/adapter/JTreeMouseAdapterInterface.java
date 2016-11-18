package kamera.common.ui.adapter;

import javax.swing.tree.TreePath;
import java.awt.*;

/**
 * Created by TheKing on 17.11.2016.
 */
interface JTreeMouseAdapterInterface{
    void myRightClick(int selRow, TreePath selPath, Rectangle pathBounds);
    void myDoubleClick(int selRow, TreePath selPath);
    void mySingleClick(int selRow, TreePath selPath);
}