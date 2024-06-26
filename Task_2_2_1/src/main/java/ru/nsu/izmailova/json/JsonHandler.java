package ru.nsu.izmailova.json;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class for working with json.
 */
public class JsonHandler {
    /**
     * Find "PizzeriaDara.json" file and takes data from it.
     *
     * @return pizzeria structure that contains all necessary parameters to make the pizzeria work
     * @throws IOException if an I/O error occurs while reading the file
     */
    public JsonPizzeria jsonHandle() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/PizzeriaData.json"));
        JsonPizzeria pizzeria = gson.fromJson(reader, JsonPizzeria.class);
        reader.close();
        return pizzeria;
    }
}
