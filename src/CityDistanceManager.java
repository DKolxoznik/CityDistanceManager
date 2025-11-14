import java.util.*;

public class CityDistanceManager {
    private Map<String, Map<String, Integer>> cityGraph;

    public CityDistanceManager() {
        cityGraph = new HashMap<>();
    }

    public void addCity(String cityName) {
        if (!cityGraph.containsKey(cityName)) {
            cityGraph.put(cityName, new HashMap<>());
            System.out.println("Город '" + cityName + "' добавлен.");
        } else {
            System.out.println("Город '" + cityName + "' уже существует.");
        }
    }

    public void addDistance(String city1, String city2, int distance) {
        if (!cityGraph.containsKey(city1)) {
            System.out.println("Город '" + city1 + "' не найден.");
            return;
        }
        if (!cityGraph.containsKey(city2)) {
            System.out.println("Город '" + city2 + "' не найден.");
            return;
        }

        cityGraph.get(city1).put(city2, distance);
        cityGraph.get(city2).put(city1, distance);
        System.out.println("Расстояние между '" + city1 + "' и '" + city2 + "' установлено: " + distance + " км");
    }

    public Integer getDistance(String city1, String city2) {
        if (!cityGraph.containsKey(city1) || !cityGraph.containsKey(city2)) {
            return null;
        }
        return cityGraph.get(city1).get(city2);
    }

    public List<String> getAllCities() {
        return new ArrayList<>(cityGraph.keySet());
    }

    public Map<String, Integer> getNeighbors(String city) {
        if (!cityGraph.containsKey(city)) {
            return new HashMap<>();
        }
        return new HashMap<>(cityGraph.get(city));
    }

    public void removeCity(String cityName) {
        if (cityGraph.containsKey(cityName)) {
            // Удаляем связи с другими городами
            for (String neighbor : cityGraph.get(cityName).keySet()) {
                cityGraph.get(neighbor).remove(cityName);
            }
            cityGraph.remove(cityName);
            System.out.println("Город '" + cityName + "' удален.");
        } else {
            System.out.println("Город '" + cityName + "' не найден.");
        }
    }

    public void displayAllInfo() {
        System.out.println("\n=== ИНФОРМАЦИЯ О ГОРОДАХ И РАССТОЯНИЯХ ===");
        for (String city : cityGraph.keySet()) {
            System.out.println("Город: " + city);
            Map<String, Integer> neighbors = cityGraph.get(city);
            if (neighbors.isEmpty()) {
                System.out.println("  Нет связей с другими городами");
            } else {
                for (Map.Entry<String, Integer> entry : neighbors.entrySet()) {
                    System.out.println("  -> " + entry.getKey() + ": " + entry.getValue() + " км");
                }
            }
            System.out.println();
        }
    }
}
