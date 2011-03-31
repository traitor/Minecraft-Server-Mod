import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

class OServerGuiCommandListener implements ActionListener {
    OServerGuiCommandListener(OServerGUI paramOServerGUI, JTextField paramJTextField) {
    }

    public void actionPerformed(ActionEvent paramActionEvent) {
        String str = a.getText().trim();
        if (str.length() > 0)
            OServerGUI.a(b).a(str, b);
        a.setText("");
    }
}