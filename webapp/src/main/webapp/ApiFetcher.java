package main.webapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ApiFetcher {
    public static String fetchApiData() {
        String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=-29.90&longitude=-50.27&current_weather=true";

        try {
            URL url = new URL(apiUrl);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String apiResponse = response.toString();
            Map<String, Object> data = parseJson(apiResponse);

            Map<String, Object> currentWeather = (Map<String, Object>) data.get("current_weather");
            double temperature = (double) currentWeather.get("temperature");
            int weatherCode = (int) currentWeather.get("weathercode");
            String time = (String) currentWeather.get("time");
            double windSpeed = (double) currentWeather.get("windspeed");
            double windDirection = (double) currentWeather.get("winddirection");

            StringBuilder weatherInfo = new StringBuilder();
            weatherInfo.append("Temperatura: ").append(temperature).append("C\n");
            weatherInfo.append("Clima: ").append(getWeatherDescription(weatherCode)).append("\n");
            weatherInfo.append("Localidade: ").append(location).append(" (às ").append(time).append(")\n");
            weatherInfo.append("Velocidade do vento: ").append(windSpeed).append(" km/h, ").append(getWindDirection(windDirection));
            
            return weatherInfo.toString();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return "Erro";
    }

    private static Map<String, Object> parseJson(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        return gson.fromJson(json, type);
    }

    private static String getWeatherDescription(int weatherCode) {
        switch (weatherCode) {
            case 0:
                return "Céu limpo";
            case 1:
                return "Principalmente limpo";
            case 2:
                return "Parcialmente nublado";
            case 3:
                return "Nublado";
            case 45:
                return "Neblina";
            case 48:
                return "Neblina com rime";
            case 51:
                return "Chuvisco: Fraco";
            case 53:
                return "Chuvisco: Moderado";
            case 55:
                return "Chuvisco: Intenso";
            case 56:
                return "Chuvisco congelante: Fraco";
            case 57:
                return "Chuvisco congelante: Intenso";
            case 61:
                return "Chuva: Fraca";
            case 63:
                return "Chuva: Moderada";
            case 65:
                return "Chuva: Forte";
            case 66:
                return "Chuva congelante: Fraca";
            case 67:
                return "Chuva congelante: Forte";
            case 71:
                return "Neve: Fraca";
            case 73:
                return "Neve: Moderada";
            case 75:
                return "Neve: Forte";
            case 77:
                return "Grãos de neve";
            case 80:
                return "Chuvas: Fracas";
            case 81:
                return "Chuvas: Moderadas";
            case 82:
                return "Chuvas: Violentas";
            case 85:
                return "Nevascas: Fracas";
            case 86:
                return "Nevascas: Fortes";
            case 95:
                return "Tempestade: Fraca ou moderada";
            case 96:
                return "Tempestade com granizo fraco";
            case 99:
                return "Tempestade com granizo forte";
            default:
                return "Desconhecido";
        }
    }

    private static String getWindDirection(double degrees) {
        String[] directions = {"N", "NNL", "NL", "LNL", "L", "LSL", "LE", "SSL", "S", "SSO", "SO", "OSO", "O", "ONO", "NO", "NNO"};
        int index = (int) Math.round(degrees / 22.5) % 16;
        return directions[index];
    }
}
