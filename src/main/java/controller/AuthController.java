package controller;

import dto.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.AuthService;
import util.ScannerUtil;
import java.util.Scanner;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private  Profile profile;
    public void start() {
        boolean game = true;
        while (game) {
            menu();
            int action = ScannerUtil.getAction();
            switch (action) {
                case 1:
                    login();
                    break;
                case 2:
                    registration();
                    break;
                case 0:
                    game = false;
                default:
                    System.out.println("Mazgi nima bu");
            }
        }
    }
    public void menu() {
        System.out.println("********************Menu***********************");
        System.out.println("1. Login > ");
        System.out.println("2. Registration > ");
        System.out.println("0. Exit > ");
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter phone:");
        String phone = scanner.nextLine();

        System.out.print("Enter pswd:");
        String password = scanner.next();

        authService.login(phone, password);
    }

    private void registration() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name:");
        String name = scanner.next();

        System.out.print("Enter surname:");
        String surname = scanner.next();

        System.out.print("Enter phone:");
        String phone = scanner.next();

        System.out.print("Enter pswd:");
        String password = scanner.next();

        profile.setName(name);
        profile.setSurname(surname);
        profile.setPhone(phone);
        profile.setPassword(password);

        authService.registration(profile);
    }


}
