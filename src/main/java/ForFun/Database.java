package ForFun;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Database {

    private List<Person> people;

    public Database() {
        people = new ArrayList<>();
    }

    public void addPerson(String firstName, String lastName, String phoneNumber, LocalDateTime birthDate) {
        people.add(new Person(firstName, lastName, phoneNumber, birthDate));
    }

    public List<Person> findPeople(String firstName, String lastName) {
        List<Person> foundPeople = people.stream()
                .filter(person -> person.getFirstName().equals(firstName))
                .filter(person -> person.getLastName().equals(lastName))
                .sorted(Comparator.comparing(Person::getId))
                .collect(Collectors.toUnmodifiableList());
        return (foundPeople);

    }

    public List<Person> findAllPeople() {
        List<Person> foundPeople = people.stream()
                .sorted(Comparator.comparing(Person::getId))
                .collect(Collectors.toUnmodifiableList());
        return (foundPeople);
    }

    public void deleteAllPeople(boolean deleteAll) {
        if (deleteAll) {
            people.clear();
        }
    }

    public void deletePerson(String firstName, String lastName, int id) {
        people.removeIf(person -> person.getFirstName().equals(firstName) &&
                person.getLastName().equals(lastName) &&
                person.getId() == id);
    }
}
