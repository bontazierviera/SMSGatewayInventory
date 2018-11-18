
package main;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Main {
    static form.MenuUtama mUtama;
    /** Creates a new instance of Main */
    public Main() {   }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
                LookAndFeel lnf = new com.jtattoo.plaf.mint.MintLookAndFeel();
                UIManager.setLookAndFeel(lnf);

        } catch(UnsupportedLookAndFeelException err) {
            JOptionPane.showMessageDialog(null,"Look And Feel Gagal Di Tampilkan "+err,"ERROR",
                                        JOptionPane.ERROR_MESSAGE);
        }

        try {
             mUtama = new form.MenuUtama();

             mUtama.setExtendedState(Frame.MAXIMIZED_BOTH);
             Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(),
                       frameSize  = screenSize.getSize();

             mUtama.setLocation((screenSize.width - frameSize.width)/2,
                                (screenSize.height - frameSize.height/2));
             mUtama.setVisible(true);

        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Failed When Loading Main Menu ! \n Error @ : "+e.getMessage());
        }
    }

}
