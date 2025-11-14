public class Main {
    public static void main(String[] args) {
        CityDistanceApp app = new CityDistanceApp();
        testExample();
        app.run();
    }

    public static void testExample() {
        System.out.println("=== ТЕСТОВЫЙ ПРИМЕР ===");

        CityDistanceManager manager = new CityDistanceManager();

        // Добавляем города
        manager.addCity("Москва");
        manager.addCity("Санкт-Петербург");
        manager.addCity("Казань");
        manager.addCity("Новосибирск");

        // Добавляем расстояния
        manager.addDistance("Москва", "Санкт-Петербург", 710);
        manager.addDistance("Москва", "Казань", 815);
        manager.addDistance("Казань", "Новосибирск", 2150);

        // Показываем всю информацию
        manager.displayAllInfo();

        // Получаем расстояние между городами
        Integer distance = manager.getDistance("Москва", "Санкт-Петербург");
        System.out.println("Расстояние Москва-СПб: " + distance + " км");
    }
}