package ru.nsu.izmailova.order;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Class for serializing and deserializing order data to and from JSON format.
 */
public class OrderSerializer {
    private final String filePath;

    /**
     * Constructor of the OrderSerializer object with the file path.
     *
     * @param filePath path to the file where the order data will be saved/loaded
     */
    public OrderSerializer(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves a list of orders to the JSON file.
     *
     * @param orders the list of orders to be saved
     */
    public void saveOrders(List<Order> orders) {
        try (Writer writer = new FileWriter(filePath)) {
            Gson gson = new Gson();
            gson.toJson(orders, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a list of orders from the JSON file.
     *
     * @return the list of orders loaded from the file
     */
    public List<Order> loadOrders() {
        List<Order> orders = null;
        try (Reader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Order>>() {}.getType();
            orders = gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (orders == null) {
            orders = new ArrayList<>();
        }
        return orders;
    }
}
