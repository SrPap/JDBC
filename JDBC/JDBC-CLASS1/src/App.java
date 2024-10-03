
import persistencia.DAO;

public class App extends DAO {

    public static void main(String[] args) {
        App mainApp = new App();
        try {
            mainApp.connectarDataBase();




        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                mainApp.desconectarDataBase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
