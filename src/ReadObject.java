import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ReadObject {
    private static final String PEOPLE_STORE_PATH = "people";
    private static final String CITIES_STORE_PATH = "cities";

    public static void findCitiesByPersonName(String searchName) {
        List<Person> people = loadAllPeople();
        List<City> cities = loadCities();
        Set<String> result = new HashSet<>();

        for (Person person : people) {
            if (person.getName().trim().equalsIgnoreCase(searchName.trim())) {
                for (City city : cities) {
                    if (person.getCityId() == city.getId()) {
                        result.add(city.getName());
                    }
                }
            }
        }
        System.out.println("Результат поиска городов: " + result);
    }

    public static void findPeopleByCityName(String searchCity) {

        List<Person> people = loadAllPeople();
        List<City> cities = loadCities();
        List<String> result = new ArrayList<>();

        for (City city : cities) {
            if (city.getName().equalsIgnoreCase(searchCity.trim())) {
                for (Person person : people) {
                    if (person.getCityId() == city.getId()) {
                        result.add(person.getName());
                    }
                }
            }
        }

        System.out.println("Результат поиска людей: " + result);
    }

    public static void showAll() {
        System.out.println("\n=== Список городов ===");
        loadCities().forEach(System.out::println);
        System.out.println("\n=== Список людей ===");
        loadAllPeople().forEach(p -> System.out.println("Имя: " + p.getName() + " City ID: " + p.getCityId()));
    }

    public static List<Person> loadAllPeople() {
        return loadEntitiesFromFile(Person.class, PEOPLE_STORE_PATH);
    }

    public static List<City> loadCities() {
        return loadEntitiesFromFile(City.class, CITIES_STORE_PATH);
    }

    public static <T> List<T> loadEntitiesFromFile(Class<T> clazz, String filePath) {
        List<T> list = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return list;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                list.add(clazz.cast(ois.readObject()));
            }
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка чтения файла: " + filePath + ": " + e.getMessage());
        }
        return list;
    }
}

