import java.io.*;
import java.util.List;

public class WriteObject {
    private static final String PEOPLE_FILE = "people";
    private static final String CITIES_FILE = "cities";

    public static void initDefaultCities() {
        List<City> cities = ReadObject.loadCities();
        if (cities.isEmpty()) {
            cities.add(new City(1, "Москва", "101000"));
            cities.add(new City(2, "Ростов", "344000"));
            cities.add(new City(3, "Казань", "420000"));
            saveEntitiesToFile(cities, CITIES_FILE);
        }
    }

    public static void savePerson(Person person) {
        List<Person> people = ReadObject.loadAllPeople();
        people.add(person);
        saveEntitiesToFile(people, PEOPLE_FILE);
    }

    public static void saveCity(City city) {
        List<City> cities = ReadObject.loadCities();
        cities.add(city);
        saveEntitiesToFile(cities, CITIES_FILE);
    }

    public static <T> void saveEntitiesToFile(List<T> entities, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (T entity : entities) {
                oos.writeObject(entity);
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи " + entities.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}
