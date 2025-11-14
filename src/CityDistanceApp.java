import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CityDistanceApp {
    private CityDistanceManager manager;
    private Scanner scanner;

    public CityDistanceApp() {
        manager = new CityDistanceManager();
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("=== ПРОГРАММА ДЛЯ УЧЕТА ГОРОДОВ И РАССТОЯНИЙ ===");

        while (true) {
            showMenu();
            int choice = getIntInput("Выберите действие: ");

            switch (choice) {
                case 1:
                    addCity();
                    break;
                case 2:
                    addDistance();
                    break;
                case 3:
                    showDistance();
                    break;
                case 4:
                    showAllCities();
                    break;
                case 5:
                    showCityNeighbors();
                    break;
                case 6:
                    removeCity();
                    break;
                case 7:
                    manager.displayAllInfo();
                    break;
                case 0:
                    System.out.println("Программа завершена.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n=== МЕНЮ ===");
        System.out.println("1. Добавить город");
        System.out.println("2. Добавить расстояние между городами");
        System.out.println("3. Показать расстояние между городами");
        System.out.println("4. Показать все города");
        System.out.println("5. Показать соседние города");
        System.out.println("6. Удалить город");
        System.out.println("7. Показать всю информацию");
        System.out.println("0. Выход");
    }

    private void addCity() {
        System.out.print("Введите название города: ");
        String cityName = scanner.nextLine().trim();
        if (!cityName.isEmpty()) {
            manager.addCity(cityName);
        } else {
            System.out.println("Название города не может быть пустым.");
        }
    }

    private void addDistance() {
        System.out.print("Введите первый город: ");
        String city1 = scanner.nextLine().trim();
        System.out.print("Введите второй город: ");
        String city2 = scanner.nextLine().trim();

        int distance = getIntInput("Введите расстояние (км): ");
        if (distance > 0) {
            manager.addDistance(city1, city2, distance);
        } else {
            System.out.println("Расстояние должно быть положительным числом.");
        }
    }

    private void showDistance() {
        System.out.print("Введите первый город: ");
        String city1 = scanner.nextLine().trim();
        System.out.print("Введите второй город: ");
        String city2 = scanner.nextLine().trim();

        Integer distance = manager.getDistance(city1, city2);
        if (distance != null) {
            System.out.println("Расстояние между '" + city1 + "' и '" + city2 + "': " + distance + " км");
        } else {
            System.out.println("Расстояние между указанными городами не найдено.");
        }
    }

    private void showAllCities() {
        List<String> cities = manager.getAllCities();
        if (cities.isEmpty()) {
            System.out.println("Список городов пуст.");
        } else {
            System.out.println("Список всех городов:");
            for (int i = 0; i < cities.size(); i++) {
                System.out.println((i + 1) + ". " + cities.get(i));
            }
        }
    }

    private void showCityNeighbors() {
        System.out.print("Введите название города: ");
        String city = scanner.nextLine().trim();

        Map<String, Integer> neighbors = manager.getNeighbors(city);
        if (neighbors.isEmpty()) {
            System.out.println("Город не найден или у него нет соседей.");
        } else {
            System.out.println("Соседние города для '" + city + "':");
            for (Map.Entry<String, Integer> entry : neighbors.entrySet()) {
                System.out.println("  " + entry.getKey() + ": " + entry.getValue() + " км");
            }
        }
    }

    private void removeCity() {
        System.out.print("Введите название города для удаления: ");
        String cityName = scanner.nextLine().trim();
        manager.removeCity(cityName);
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите целое число.");
            }
        }
    }

    public static void main(String[] args) {
        CityDistanceApp app = new CityDistanceApp();
        app.run();
    }
}