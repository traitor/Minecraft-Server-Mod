import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

class OServerGuiCommandListener implements ActionListener {
    final JTextField a;
    final OServerGUI b;

    OServerGuiCommandListener(OServerGUI paramOServerGUI, JTextField paramJTextField) {
        a = paramJTextField;
        b = paramOServerGUI;
    }

    public void actionPerformed(ActionEvent paramActionEvent) {
        String str = a.getText().trim();
        if (str.length() > 0 && etc.getMCServer() != null)
            if (!etc.getInstance().parseConsoleCommand(str, etc.getMCServer()))
                etc.getMCServer().a(str, b);
        a.setText("");
    }
}