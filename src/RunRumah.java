import Util.Formatting;
import Util.InputUtilities;

import java.util.Date;

public class RunRumah {
    public static void main(String[] args) {
        DiRumah.init();
        RumahSalesApp app = new RumahSalesApp();
        app.run();
    }
}
