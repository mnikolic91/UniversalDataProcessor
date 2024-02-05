import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
//
//        System.out.println("CSV Data:");
//        System.out.println("--------------------------------------------------");
//
//        String pathToFile = "DATA/csvData.csv";
//        CSVUtils<Data> csvUtils = new CSVUtils<>();
//        StartingDataContainer<Data> startingDataContainer = csvUtils.readFromFile(pathToFile, true, Data.class);
//        String[] headers = {"userName", "yearsActive", "numberOfTotalPurchases", "totalSpendEUR", "isInEU", "preferredPaymentMethod"};
//        csvUtils.listAllRecords(startingDataContainer.getData(), headers);
//        csvUtils.listParticularRecord(startingDataContainer.getData(), 30);
//        HashMap<String, Integer> doubleFields = (HashMap<String, Integer>) csvUtils.findAllDoubleFields(startingDataContainer.getData());
//        System.out.println("Double Fields: " + doubleFields);
//        HashMap<String, Double> avgValues = (HashMap<String, Double>) csvUtils.calculateAvgValues(startingDataContainer.getData(), doubleFields);
//        System.out.println("Average Values: " + avgValues);
//        csvUtils.saveDataToFile(startingDataContainer.getData(), "DATA/someNewData2.csv");
//
//        System.out.println("--------------------------------------------------");

        System.out.println("JSON Data:");
        System.out.println("--------------------------------------------------");

        String pathToFile = "DATA/jsonData.json";
        JSONAdapter jsonAdapter = new JSONAdapter();
        jsonAdapter.setJsonUtils(new JSONUtils<>());
        StartingDataContainer<JSONData> startingDataContainer = jsonAdapter.readFromFile(pathToFile, false, JSONData.class);
        String[] headers = {"userName", "isInEU", "activeTotal", "totalPurchases", "totalSpendEUR", "payment", "usedWebBrowser"};
        jsonAdapter.listAllRecords(startingDataContainer.getData(), headers);
        jsonAdapter.listParticularRecord(startingDataContainer.getData(), 30);
        Map<String, Integer> doubleFields = jsonAdapter.findAllDoubleFields(startingDataContainer.getData());
        System.out.println("Double Fields: " + doubleFields);
        Map<String, Double> avgValues = jsonAdapter.calculateAvgValues(startingDataContainer.getData(), doubleFields);
        System.out.println("Average Values: " + avgValues);
        jsonAdapter.saveDataToFile(startingDataContainer.getData(), "DATA/someNewData2.json");

        System.out.println("--------------------------------------------------");

    }
}
