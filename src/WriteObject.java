import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WriteObject {
    private static final String FILE = "people";
    private static final String CITIES_FILE = "cities";
    private static Scanner sc = new Scanner(System.in);

    static void main(String[] args) {
        ArrayList<City> cities = loadCities();
        if(cities.isEmpty()) {
            cities.add(new City(1, "Москва", "101000"));
            cities.add(new City(2, "Ростов", "344000"));
            cities.add(new City(3, "Казань", "420000"));

            saveCities(cities);

        }

       System.out.println("Введите ID: ");
       int id =  Integer.parseInt(sc.nextLine());

       System.out.println("Введите Имя: ");
       String name =  sc.nextLine();

        System.out.println("\nДоступные города:");
        for(City city : cities) {
            System.out.println(city);
        }
        System.out.println("Введите ID города: ");
        int cityId = Integer.parseInt(sc.nextLine());

       Person newPerson = new Person(id, name, cityId);
       ArrayList<Person> people = loadAll();
       people.add(newPerson);
       saveAll(people);
       System.out.println("Пользователь сохранен");
       sc.close();
    }

    private static ArrayList<Person> loadAll() {
       ArrayList<Person> list = new ArrayList<>();
       File file = new File(FILE);

       if(!file.exists()) {
           return list;
       }
       try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
           while(true) {
               Person p = (Person) ois.readObject();
               list.add(p);
           }
       } catch (EOFException e) {
       } catch (IOException | ClassNotFoundException e) {
           System.out.println("Ошибка чтения: " + e.getMessage());
       }
       return list;
    }

    private static void saveAll(ArrayList<Person> people) {
       try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
           for(Person p : people) {
               oos.writeObject(p);
           }
       } catch (IOException e) {
           System.out.println("Ошибка записи: " + e.getMessage());
       }
    }

    private static ArrayList<City> loadCities() {
        ArrayList<City> list = new ArrayList<>();
        File file = new File(CITIES_FILE);
        if(!file.exists()) {
            return list;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while(true) {
                list.add((City) ois.readObject());
            }
        }catch (EOFException e) {
        }catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка чтения городов: " + e.getMessage());
        }
        return list;
    }

    private static void saveCities(ArrayList<City> cities) {
        try (ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(CITIES_FILE))) {
            for(City c : cities) {
                oss.writeObject(c);
            }
        }catch (IOException e) {
            System.out.println("Ошибка записи городов: " + e.getMessage());
        }
    }
}
