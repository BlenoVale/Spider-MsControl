package util;

import java.awt.Container;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 * @author Dan jhonatan
 */
public class Internal {

    public static void retiraBotao(JInternalFrame internal) {
        BasicInternalFrameUI ui = (BasicInternalFrameUI) internal.getUI();
        Container north = (Container) ui.getNorthPane();
        north.remove(0);
        north.validate();
        north.repaint();
    }
}
