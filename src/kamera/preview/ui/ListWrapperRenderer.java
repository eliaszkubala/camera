package kamera.preview.ui;

import kamera.common.model.ListWrapper;

import javax.swing.*;
import java.awt.*;

/**
 * Created by TheKing on 03.02.2016.
 */
public class ListWrapperRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean hasFocus) {
        if (value instanceof ListWrapper) {
            return super.getListCellRendererComponent(
                    list, ((ListWrapper) value).getName(), index,
                    isSelected, hasFocus);
        }
        return super.getListCellRendererComponent(list, value, index,
                isSelected, hasFocus);
    }
}
