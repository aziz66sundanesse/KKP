package gegana.app;

import chumbucket.widget.message.Pesan;
import com.jtattoo.plaf.luna.LunaLookAndFeel;
import gegana.view.ViewLogin;
import java.util.Properties;
import javax.swing.UnsupportedLookAndFeelException;
//import tampilan.FrameLogin;

public class GeganaApp {

    public static void main(String[] args) {
        try {
            java.util.Locale.setDefault(new java.util.Locale("ID", "id"));
            Properties properties = new Properties();
            properties.put("logoString", ".");
            LunaLookAndFeel.setCurrentTheme(properties);
            javax.swing.UIManager.setLookAndFeel(new LunaLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Pesan.tampilkanPesanError(null, "Tema tampilan tidak support\n" + ex.getMessage(), "Error");
        }
        new ViewLogin().setVisible(true);
    }
    
}
