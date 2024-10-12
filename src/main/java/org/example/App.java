package org.example;


import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class App {
    public static void main( String[] args ) {

        try {
            // Создание URL и соединения
            URL url = new URL("https://api.weather.yandex.ru/v2/forecast?lat=55.75396&lon=37.620393");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-Yandex-Weather-Key", "cc63e37e-bbf5-437d-9189-0aa9be2aa911"); // Передача ключа

            // Проверка кода ответа
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Чтение ответа
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Преобразование JSON-ответа в объект
                Gson gson = new Gson();
                WeatherResponse responseObject = gson.fromJson(response.toString(), WeatherResponse.class);

                // Используйте ваш объект
                System.out.println(responseObject);
                System.out.println( responseObject.getFact().getDaytime());
            } else {
                System.out.println("Ошибка в соединении: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    }

