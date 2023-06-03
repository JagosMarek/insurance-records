package ForFun;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UI ui = new UI();
        String choice = "";
        ui.welcomeScreen();
        while (!choice.equals("7")) {
            System.out.println();
            System.out.println("==============================");
            System.out.println("Select action: ");
            System.out.println("1 - Add person");
            System.out.println("2 - Find all people");
            System.out.println("3 - Find people");
            System.out.println("4 - Delete all people");
            System.out.println("5 - Delete person");
            System.out.println("6 - Edit person");
            System.out.println("7 - End");
            System.out.println("==============================");
            choice = sc.nextLine().trim();
            System.out.println();

            switch (choice) {
                case "1":
                    ui.addPerson();
                    break;
                case "2":
                    ui.findAllPeople();
                    break;
                case "3":
                    ui.findPeople();
                    break;
                case "4":
                    ui.deleteAllPeople();
                    break;
                case "5":
                    ui.deletePerson();
                    break;
                case "6":
                    ui.editPerson();
                    break;
                case "7":
                    break;
                default:
                    System.out.println("Incorrect entry, please re-enter.");
                    break;
            }
        }
        System.out.println("Goodbye :)");
    }
}