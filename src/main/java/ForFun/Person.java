package ForFun;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class Person {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDateTime birthDate;
    private int age;
    private final int ID;
    private static int nextId = 1;

    public Person(String firstName, String lastName, String phoneNumber, LocalDateTime birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        ID = nextId;
        nextId++;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return ID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String capitalizeFirstName(String method) {
        String firstLetter = method.substring(0, 1).toUpperCase();
        String restOfString = method.substring(1).toLowerCase();

        return firstLetter + restOfString;
    }

    public String capitalizeLastName(String method) {
        String firstLetter = method.substring(0, 1).toUpperCase();
        String restOfString = method.substring(1).toLowerCase();

        return firstLetter + restOfString;
    }

    private void calculateAge() {
        LocalDate now = LocalDate.now();
        age = Period.between(birthDate.toLocalDate(), now).getYears();
    }

    public String repeatCharacter(String method) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < method.length(); i++) {
            result.append("-");
        }
        return result.toString();
    }

    @Override
    public String toString() {
        calculateAge();
        String middle = "| Id: " + getId() + " | First name: " + capitalizeFirstName(getFirstName()) + " | Last name: "
                + capitalizeLastName(getLastName()) + " | Age: " + getAge() + " | Phone: " + getPhoneNumber() + " |";
        String top = repeatCharacter(middle);
        String bottom = repeatCharacter(middle);
        String complete = top + "\n" + middle + "\n" + bottom;
        return String.format(complete);
    }
}
