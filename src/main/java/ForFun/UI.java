package ForFun;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class UI {

    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("d.M.y");
    private final LocalDate LAST_DATE = LocalDate.of(1900, 1, 1);

    private Database database;
    private Scanner sc = new Scanner(System.in);

    public UI() {
        database = new Database();
    }

    public void welcomeScreen() {
        System.out.println("==================================");
        System.out.println("Welcome to the insurance database!");
        System.out.println("Choose an option from the list:");
    }

    public void addPerson() {
        System.out.println("Add a new person.");
        System.out.print("Enter the person's first name: ");
        String firstName = parseName();
        System.out.print("Enter the person's last name: ");
        String lastName = parseName();
        LocalDateTime date = parseDate();
        String phone = parsePhone();
        database.addPerson(firstName, lastName, phone, date);
        System.out.println("Person was added.");
    }

    public void findPeople() {
        System.out.print("Enter the person's first name: ");
        String firstName = parseName();
        System.out.print("Enter the person's last name: ");
        String lastName = parseName();
        List<Person> people = database.findPeople(firstName, lastName);
        if (people.isEmpty()) {
            System.out.println("No person found with the specified name.");
        } else {
            System.out.println("The following people were found:");
            for (Person person : people) {
                System.out.println(person);
            }
        }
    }

    public void findAllPeople() {
        List<Person> people = database.findAllPeople();
        if (people.isEmpty()) {
            System.out.println("No person found.");
        } else {
            System.out.println("The following people were found:");
            for (Person person : people) {
                System.out.println(person);
            }
        }
    }

    public void deleteAllPeople() {
        List<Person> people = database.findAllPeople();
        if (people.isEmpty()) {
            System.out.println("No person found.");
            return;
        } else {
            System.out.println("The following people were found:");
            for (Person person : people) {
                System.out.println(person);
            }
        }
        System.out.print("Do you want to delete all people? [Yes/No]: ");
        String choice = "";
        int delete = 0;
        while (delete == 0) {
            choice = sc.nextLine().toLowerCase().trim();
            if (choice.equals("yes") || choice.equals("y")) {
                database.deleteAllPeople(true);
                System.out.println("All people were deleted!");
                delete++;
            } else if (choice.equals("no") || choice.equals("n")) {
                System.out.println("Okey people won't be deleted.");
                delete++;
            } else {
                System.out.print("Incorrect entry, please re-enter: ");
            }
        }
    }

    public void deletePerson() {
        System.out.print("Enter the person's first name: ");
        String firstName = parseName();
        System.out.print("Enter the person's last name: ");
        String lastName = parseName();
        List<Person> people = database.findPeople(firstName, lastName);
        if (people.isEmpty()) {
            System.out.println("No person found with the specified name.");
            return;
        } else {
            System.out.println("The following people were found:");
            for (Person person : people) {
                System.out.println(person);
            }
        }

        int id = parseIdDelete();
        boolean found = false;
        Person personToDelete = null;

        while (!found) {
            for (Person person : people) {
                if (person.getId() == id) {
                    found = true;
                    personToDelete = person;
                    break;
                }
            }

            if (!found) {
                System.out.println("Person not found with the specified ID.");

                System.out.print("Enter a ID or type 'cancel' to cancel the deletion: ");
                String input = sc.nextLine().toLowerCase().trim();

                if (input.equals("cancel")) {
                    System.out.println("Deletion canceled.");
                    return;
                }

                try {
                    id = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid ID or 'cancel'.");
                }
            }
        }

        System.out.println("Person selected:");
        System.out.println(personToDelete);

        System.out.print("Are you sure you want to delete this person? (yes/no): ");
        String choice = "";
        int delete = 0;
        while (delete == 0) {
            choice = sc.nextLine().toLowerCase().trim();
            if (choice.equals("yes") || choice.equals("y")) {
                database.deletePerson(firstName, lastName, id);
                System.out.println("Person deleted.");
                delete++;
            } else if (choice.equals("no") || choice.equals("n")) {
                System.out.println("Deletion canceled.");
                delete++;
            } else {
                System.out.print("Incorrect entry, please re-enter: ");
            }
        }
    }

    public void editPerson() {
        System.out.print("Enter the person's first name: ");
        String firstName = parseName();
        System.out.print("Enter the person's last name: ");
        String lastName = parseName();
        List<Person> people = database.findPeople(firstName, lastName);
        if (people.isEmpty()) {
            System.out.println("No person found with the specified name.");
            return;
        } else {
            System.out.println("The following people were found:");
            for (Person person : people) {
                System.out.println(person);
            }
        }

        int id = parseIdEdit();
        boolean found = false;
        Person personToEdit = null;

        while (!found) {
            for (Person person : people) {
                if (person.getId() == id) {
                    found = true;
                    personToEdit = person;
                    break;
                }
            }

            if (!found) {
                System.out.println("Person not found with the specified ID.");

                System.out.print("Enter a ID or type 'cancel' to cancel the edit: ");
                String input = sc.nextLine().toLowerCase().trim();

                if (input.equals("cancel")) {
                    System.out.println("Edit canceled.");
                    return;
                }

                try {
                    id = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid ID or 'cancel'.");
                }
            }
        }

        System.out.println("Person selected:");
        System.out.println(personToEdit);

        System.out.println("Enter new information for the person (press Enter to keep the current value):");

        System.out.print("First name [" + personToEdit.getFirstName() + "]: ");
        String newFirstName = parseName();
        if (!newFirstName.isEmpty()) {
            personToEdit.setFirstName(newFirstName);
        }

        System.out.print("Last name [" + personToEdit.getLastName() + "]: ");
        String newLastName = parseName();
        if (!newLastName.isEmpty()) {
            personToEdit.setLastName(newLastName);
        }

        System.out.print("Phone number [" + personToEdit.getPhoneNumber() + "]: ");
        String newPhoneNumber = parsePhone();
        if (!newPhoneNumber.isEmpty()) {
            personToEdit.setPhoneNumber(newPhoneNumber);
        }

        System.out.print("Birth date [" + personToEdit.getBirthDate().format(DATE) + "]: ");
        LocalDateTime newBirthDate = parseDate();
        if (!newBirthDate.equals(personToEdit.getBirthDate())) {
            personToEdit.setBirthDate(newBirthDate);
        }

        System.out.println("Person updated:");
        System.out.println(personToEdit);
    }

    private LocalDateTime parseDate() {

        System.out.print("Enter the person's date of birth in the form (format: d.m.y) [" + LocalDate.now().format(DATE) + "]: ");

        while (true) {
            try {
                LocalDateTime date = LocalDate.parse(sc.nextLine(), DATE).atStartOfDay();
                if (date.isAfter(LocalDateTime.now()) || (date.toLocalDate().isBefore(LAST_DATE))) {
                    System.out.print("Wrong date, please re-enter: ");
                } else {
                    return date;
                }
            } catch (Exception ex) {
                System.out.print("Incorrect entry, please re-enter: ");
            }
        }
    }

    private String parsePhone() {

        final int PHONE_LENGTH = 9;
        boolean isValid = false;
        String phone = "";
        System.out.print("Enter the person's phone number in the form [425548452]: ");

        while (!isValid) {
            String imputPhone = sc.nextLine().trim();
            if (imputPhone.length() != PHONE_LENGTH) {
                System.out.print("Incorrect entry, please re-enter: ");
                continue;
            }

            isValid = true;
            for (char x : imputPhone.toCharArray()) {
                int i = (int) x;
                if (i > 47 && i < 58) {
                    phone += (char) i;
                    if (phone.length() == 3) {
                        phone += " ";
                    }
                    if (phone.length() == 7) {
                        phone += " ";
                    }
                } else {
                    System.out.print("Incorrect symbols, please re-enter: ");
                    isValid = false;
                    phone = "";
                    break;
                }
            }
        }
        return phone;
    }

    private String parseName() {

        final String allowedChars = "abcdefghijklmnopqrstuvwxyzóěščřžýáíéůúďťň"; //ABCDEFGHIJKLMNOPQRSTUVWXYZĚŠČŘŽÝÁÍÓÉŮÚĎŤŇ
        final int MAX_NAME_LENGTH = 70;
        boolean isValid = false;
        String name = "";

        while (!isValid) {
            String imputName = sc.nextLine().toLowerCase().trim();
            if ((imputName.length() > MAX_NAME_LENGTH)) {
                System.out.println("Too long name. Max is 70 and min is 0 characters. please re-enter: ");
                continue;
            }

            isValid = true;
            for (char x : imputName.toCharArray()) {
                if (!allowedChars.contains(String.valueOf(x))) {
                    System.out.print("Incorrect symbols, please re-enter: ");
                    isValid = false;
                    name = "";
                    break;
                }
                name += x;
            }
        }
        return name;
    }

    private int parseIdDelete() {

        System.out.print("Enter the person's ID you want to delete: ");

        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception ex) {
                System.out.print("Incorrect entry, please re-enter: ");
            }
        }
    }

    private int parseIdEdit() {

        System.out.print("Enter the person's ID you want to edit: ");

        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception ex) {
                System.out.print("Incorrect entry, please re-enter: ");
            }
        }
    }
}