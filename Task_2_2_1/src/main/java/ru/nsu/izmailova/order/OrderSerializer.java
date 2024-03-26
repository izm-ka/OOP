package ru.nsu.izmailova.order;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//import ru.nsu.izmailova.order.Order;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class OrderSerializer {
    private final String fileName;

    public OrderSerializer(String fileName) {
        this.fileName = fileName;
    }

    public void saveOrders(List<Order> orders) {
        try (Writer writer = new FileWriter(fileName)) {
            Gson gson = new Gson();
            gson.toJson(orders, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Order> loadOrders() {
        List<Order> orders = null;
        try (Reader reader = new FileReader(fileName)) {
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
