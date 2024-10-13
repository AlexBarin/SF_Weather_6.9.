package org.example;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class App {
    private static final String API_URL = "https://api.weather.yandex.ru/v2/forecast";
    private static final String API_KEY = "cc63e37e-bbf5-437d-9189-0aa9be2aa911"; // Укажите свой API-ключ

    public static void main(String[] args) {
        // Пример координат
        double lat = 55.75;
        double lon = 37.62;
        int limit = 3; // Количество дней, за которые нужно рассчитать среднюю температуру

        try {
            String jsonResponse = getWeatherData(lat, lon, limit);
            System.out.println("JSON ответ от сервера:");
            System.out.println(jsonResponse);

            Gson gson = new Gson();
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            int currentTemp = jsonObject.getAsJsonObject("fact").get("temp").getAsInt();
            System.out.println("Текущая температура: " + currentTemp + "°C");

            double averageTemp = calculateAverageTemperature(jsonObject, limit);
            System.out.printf("Средняя температура за последние %d дня(ей): %.2f°C%n", limit, averageTemp);

        } catch (Exception e) {
            System.err.println("Произошла ошибка при получении данных: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String getWeatherData(double lat, double lon, int limit) throws Exception {
        String urlString = API_URL + "?lat=" + lat + "&lon=" + lon + "&limit=" + limit;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Yandex-Weather-Key", API_KEY);

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            return content.toString();
        } else {
            throw new RuntimeException("Ошибка: код ответа сервера " + responseCode);
        }
    }

    private static double calculateAverageTemperature(JsonObject jsonObject, int limit) {
        double totalTemp = 0;
        int count = 0;
        for (int i = 0; i < limit; i++) {
            if (jsonObject.getAsJsonArray("forecasts").size() > i) {
                int dayTemp = jsonObject.getAsJsonArray("forecasts").get(i)
                        .getAsJsonObject().getAsJsonObject("parts")
                        .getAsJsonObject("day").get("temp_avg").getAsInt();
                totalTemp += dayTemp;
                count++;
            }
        }
        return count > 0 ? totalTemp / count : 0;
    }
}


