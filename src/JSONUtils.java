import com.google.gson.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class JSONUtils<E> implements WebDataProcessor<JSONData> {

    @Override
    public List<JSONData> readDataFromFile(String pathToFile, Class<JSONData> type) {
        List<JSONData> dataList = new ArrayList<>();
        try {
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(new FileReader(pathToFile));
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonArray dbRecords = jsonObject.getAsJsonArray("DBRecords");

            for (JsonElement element : dbRecords) {
                JSONData jsonData = gson.fromJson(element, JSONData.class);
                dataList.add(jsonData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    @Override
    public void saveDataToFile(List<JSONData> data, String pathToFile) {
        try (FileWriter fileWriter = new FileWriter(pathToFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject jsonObject = new JsonObject();
            JsonArray dbRecords = new JsonArray();

            for (JSONData jsonData : data) {
                JsonObject recordObject = gson.toJsonTree(jsonData).getAsJsonObject();
                dbRecords.add(recordObject);
            }

            jsonObject.add("DBRecords", dbRecords);

            gson.toJson(jsonObject, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listAllFetchRecords(List<JSONData> data) {
        System.out.println("=========================== ALL RECORDS ===========================");
        System.out.printf("%-10s | %-15s | %-12s | %-10s | %-10s | %-15s | %-15s |%n", "userName", "isInEU", "activeTotal", "totalPurchases", "totalSpendEUR", "payment", "usedWebBrowser");
        for (JSONData jsonData : data) {
            System.out.printf("%-10s | %-15s | %-12s | %-15s | %-10s | %-15s | %-15s |%n" ,
                    jsonData.getUserName(),
                    jsonData.getIsInEU().equals("Yes") ? "Yes" : "No",
                    jsonData.getActiveTotal(),
                    jsonData.getTotalPurchases(),
                    jsonData.getTotalSpendEUR(),
                    jsonData.getPayment(),
                    jsonData.getUsedWebBrowser());
        }
        System.out.println("===================================================================");
    }

    @Override
    public void listRecordInPosition(List<JSONData> data, int position) {
        if (position >= 0 && position < data.size()) {
            JSONData jsonData = data.get(position);
            System.out.println(jsonData);
        } else {
            System.out.println("Index out of bounds -> max index is: " + (data.size() - 1) + " and you tried to access index: " + position + "!");
        }
    }

    @Override
    public Map<String, Integer> findDoubleFields(List<JSONData> data) {
        Map<String, Integer> doubleFields = new HashMap<>();
        Field[] fields = JSONData.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == double.class || field.getType() == Double.class) {
                doubleFields.put(field.getName(), Arrays.asList(JSONData.class.getDeclaredFields()).indexOf(field));
            }
        }
        return doubleFields;
    }

    @Override
    public Map<String, Double> calculateAvgValues(List<JSONData> data, Map<String, Integer> doubleFields) {
        Map<String, Double> avgValues = new HashMap<>();
        for (Map.Entry<String, Integer> entry : doubleFields.entrySet()) {
            String fieldName = entry.getKey();
            int fieldIndex = entry.getValue();
            double sum = 0.0;
            for (JSONData jsonData : data) {
                try {
                    Field field = JSONData.class.getDeclaredFields()[fieldIndex];
                    field.setAccessible(true);
                    double fieldValue = field.getDouble(jsonData);
                    sum += fieldValue;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            double avg = sum / data.size();
            avgValues.put(fieldName, avg);
        }
        return avgValues;
    }
}
