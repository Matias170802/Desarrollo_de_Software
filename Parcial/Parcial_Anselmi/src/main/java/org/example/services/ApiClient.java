package org.example.services;

import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApiClient {
    private static final String BASE_URL = "http://localhost:5000/predict"; // Cambia si es necesario
    private final OkHttpClient client;

    public ApiClient() {
        this.client = new OkHttpClient();
    }

    public boolean isMutant(String[][] secuencia) throws IOException {
        // Convertir la matriz a JSON
        String json = "{\"secuencia\":" + convertToJson(secuencia) + "}";

        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(BASE_URL)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                // Aquí puedes parsear el JSON de la respuesta
                return responseData.contains("\"mutante\":1"); // Asumiendo que 1 es mutante
            } else {
                throw new IOException("Unexpected code " + response);
            }
        }
    }

    private String convertToJson(String[][] secuencia) {
        StringBuilder jsonBuilder = new StringBuilder("[");
        for (int i = 0; i < secuencia.length; i++) {
            jsonBuilder.append("[");
            for (int j = 0; j < secuencia[i].length; j++) {
                jsonBuilder.append("\"").append(secuencia[i][j]).append("\"");
                if (j < secuencia[i].length - 1) {
                    jsonBuilder.append(",");
                }
            }
            jsonBuilder.append("]");
            if (i < secuencia.length - 1) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }
}
