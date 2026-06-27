import auth.AuthService;
import cli.MenuRenderer;
import model.User;
public class Main {
    public static void main(String[] args) {
        
        System.out.println("\n Welcome to the DSATM Automated Examination Ticketing System \n ");
        System.out.println("Please log in to continue.\n");

        AuthService auth = new AuthService();
        MenuRenderer menu = new MenuRenderer();

        User user = null;

        while (user == null) {
            user = auth.login();
        }

        menu.showMenu(user);

        System.out.println("Thank you for using the DSATM Automated Examination Ticketing System");
    }
}
