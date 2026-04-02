import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class ReadObject {
    private static final String FILE = "people";
    private static final String CITIES_FILE = "cities";
    private static Scanner sc = new Scanner(System.in);

    static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Найти города");
            System.out.println("2. Найти людей в городе");
            System.out.println("3. Показать всех");
            System.out.println("4. Выход");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.println("Введите имя по которому хотите найти города");
                findCitiesByPersonName(sc.nextLine());
            } else if (choice.equals("2")) {
                findPeopleByCityName();
            } else if (choice.equals("3")) {
                showAll();
            } else if (choice.equals("4")) {
                break;
            }
        }
        sc.close();
    }

    private static void findCitiesByPersonName(String searchName) {
        ArrayList<Person> people = loadAll();
        ArrayList<City> cities = loadCities();

        System.out.println("[DEBUG] Загружено людей: " + people.size());
        System.out.println("[DEBUG] Загружено городов: " + cities.size());

        Set<String> result = new HashSet<>();
        System.out.println("\n=== Поиск: " + searchName + "===");

        for (Person person : people) {
            System.out.println("[DEBUG] Проверяем человека: '" + person.getName() + "' (cityId: " + person.getCityId() + ")");
            for (City city : cities) {
                if (person.getCityId() == city.getId()) {
                    if (person.getName().trim().equals(searchName)) {
                        result.add(city.getName());
                        System.out.println("Найдено " + person.getName() + "-" + city.getName());
                    }
                }
            }
        }
        System.out.println("Результат: " + result);
    }

    private static void findPeopleByCityName() {
        System.out.println("Введите название города: ");
        String searchCity = sc.nextLine().trim();

        ArrayList<Person> people = loadAll();
        ArrayList<City> cities = loadCities();
        ArrayList<String> result = new ArrayList<>();

        System.out.println("\n=== Поиск: " + searchCity + "===");

        for (Person person : people) {
            for (City city : cities) {
                if (person.getCityId() == city.getId()) {
                    if (city.getName().equalsIgnoreCase(searchCity)) {
                        result.add(person.getName());
                        System.out.println("Найдено: " + city.getName() + "-" + person.getName());
                    }
                }
            }
        }
        System.out.println("Результат: " + result);
    }

    private static void showAll() {
        System.out.println("\n=== Города ===");
        for (City c : loadCities()) {
            System.out.println("ID: " + c.getId() + " | Название: " + c.getName());
        }
        System.out.println("\n=== Люди ===");
        for (Person p : loadAll()) {
            System.out.println("Имя: " + p.getName() + " | cityId: " + p.getCityId());
        }
    }

    private static ArrayList<Person> loadAll() {
        ArrayList<Person> list = new ArrayList<>();
        File file = new File(FILE);

        if (!file.exists()) {
            return list;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                Person p = (Person) ois.readObject();
                list.add(p);
            }
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка чтения: " + e.getMessage());
        }
        return list;
    }

    private static ArrayList<City> loadCities() {
        ArrayList<City> list = new ArrayList<>();
        File file = new File(CITIES_FILE);
        if (!file.exists()) {
            return list;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                list.add((City) ois.readObject());
            }
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка чтения городов: " + e.getMessage());
        }
        return list;
    }
}

