import java.util.List;
import java.util.Scanner;

public class Runner {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        WriteObject.initDefaultCities();

        while (true) {
            String MENU_MESSAGE = "\n1. Найти города" +
                    "\n2. Найти людей в городе" +
                    "\n3. Показать всех" +
                    "\n4. Добавить человека" +
                    "\n5. Создать новый город" +
                    "\n6. Создать нового пользователя" +
                    "\n0. Выход";
            System.out.println(MENU_MESSAGE);

            String command = sc.nextLine();

            switch (command) {
                case "1":
                    System.out.println("Введите имя:");
                    ReadObject.findCitiesByPersonName(sc.nextLine());
                    break;
                case "2":
                    System.out.println("Введите название города:");
                    ReadObject.findPeopleByCityName(sc.nextLine());
                    break;
                case "3":
                    ReadObject.showAll();
                    break;
                case "4":
                    addNewPersonLegacy();
                    break;
                case "5":
                    addNewCity();
                    break;
                case "6":
                    addNewPersonSimplified();
                    break;
                case "0":
                    sc.close();
                    return;
                default:
                    System.out.println("Неверная команда");
            }
        }
    }

    private static void addNewPersonLegacy() {
        System.out.println("Введите ID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Введите Имя: ");
        String name = sc.nextLine();

        System.out.println("\nДоступные города: ");
        for (City city : ReadObject.loadCities()) {
            System.out.println(city);
        }
        System.out.println("Введите ID города: ");
        int cityId = Integer.parseInt(sc.nextLine());

        WriteObject.savePerson(new Person(id, name, cityId));
        System.out.println("Пользователь сохранен");
    }

    private static void addNewCity() {
        System.out.println("Введите ID города:");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Введите название:");
        String name = sc.nextLine();
        System.out.println("Введите индекс:");
        String index = sc.nextLine();

        WriteObject.saveCity(new City(id, name, index));
        System.out.println("Город добавлен");
    }

    private static void addNewPersonSimplified() {
        System.out.println("Введите ID:");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Введите Имя:");
        String name = sc.nextLine();
        System.out.println("Введите ID города:");
        int cityId = Integer.parseInt(sc.nextLine());

        WriteObject.savePerson(new Person(id, name, cityId));
        System.out.println("Пользователь добавлен");
    }
}
