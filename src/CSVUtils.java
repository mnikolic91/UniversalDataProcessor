

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class CSVUtils<E> implements DataProcessor<Data> {


    @Override
    public StartingDataContainer<Data> readFromFile(String pathToFile, boolean hasHeader, Class<Data> type) {

        List<Data> dataList = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(pathToFile))) {
            List<String[]> csvData = reader.readAll();

            int startRow = hasHeader ? 1 : 0;

            for (int i = startRow; i < csvData.size(); i++) {
                String[] row = csvData.get(i);

                Data dataInstance = new Data();
                dataInstance.setUserName(row[0]);
                dataInstance.setYearsActive(Integer.parseInt(row[1]));
                dataInstance.setNumberOfTotalPurchases(Integer.parseInt(row[2]));
                dataInstance.setTotalSpendEUR(Double.parseDouble(row[3]));
                dataInstance.setInEU("Yes".equalsIgnoreCase(row[4]));
                dataInstance.setPreferredPaymentMethod(row[5]);

                dataList.add(dataInstance);
            }

            return new StartingDataContainer<>(dataList, hasHeader);

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return new StartingDataContainer<>(List.of(), hasHeader);
    }

    @Override
    public void saveDataToFile(List<Data> data, String pathToFile) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(pathToFile))) {
            String[] headers = getHeaders();
            writer.writeNext(headers);

            for (Data dataInstance : data) {
                writer.writeNext(toStringArray(dataInstance));
            }

            System.out.println("Data saved to file: " + pathToFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listAllRecords(List<Data> dataRec, String[] headers) {
        System.out.println("=========================== ALL RECORDS ===========================");

        for (String header : headers) {
            System.out.printf("%-15s | ", header);
        }

        System.out.println();

        for (Data dataInstance : dataRec) {
            System.out.printf("%-15s | ", dataInstance.getUserName());
            System.out.printf("%-15s | ", dataInstance.getYearsActive());
            System.out.printf("%-22s | ", dataInstance.getNumberOfTotalPurchases());
            System.out.printf("%-15s | ", dataInstance.getTotalSpendEUR());
            System.out.printf("%-15s | ", dataInstance.isInEU() ? "Yes" : "No");
            System.out.printf("%-15s | ", dataInstance.getPreferredPaymentMethod());
            System.out.println();
        }

        System.out.println("===================================================================");
    }




    @Override
    public void listParticularRecord(List<Data> dataRec, int index) {
        int maxIndex = dataRec.size() - 1;

        if (index >= 0 && index <= maxIndex) {
            System.out.println("=========================== PARTICULAR RECORD ===========================");

            String[] headers = getHeaders();

            System.out.println(String.join(" | ", headers));

            Data dataInstance = dataRec.get(index);

            System.out.print(dataInstance.getUserName() + " | ");
            System.out.print(dataInstance.getYearsActive() + " | ");
            System.out.print(dataInstance.getNumberOfTotalPurchases() + " | ");
            System.out.print(dataInstance.getTotalSpendEUR() + " | ");
            System.out.print(dataInstance.isInEU() + " | ");
            System.out.print(dataInstance.getPreferredPaymentMethod() + " | ");



            System.out.println();
            System.out.println("===========================================================================");
        } else {
            System.out.println("Index out of bounds -> max index is: " + maxIndex + " and you tried to access index: " + index + "!");
        }
    }



    @Override
    public Map<String, Integer> findAllDoubleFields(List<Data> data) {
        Map<String, Integer> doubleFields = new HashMap<>();
        Field[] fields = Data.class.getDeclaredFields();

        for (Field field : fields) {
            String fieldName = field.getName();
            boolean isDoubleField = true;

            for (Data dataInstance : data) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(dataInstance);

                    if (value != null) {
                        if (!(value instanceof Double || value instanceof Float)) {
                            isDoubleField = false;
                            break;
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (isDoubleField) {
                doubleFields.put(fieldName, 0);
            }
        }

        if (!doubleFields.isEmpty()) {
            System.out.println("Double fields: " + doubleFields.keySet());
        } else {
            System.out.println("No double fields detected!");
        }

        return doubleFields;
    }


    @Override
    public Map<String, Double> calculateAvgValues(List<Data> data, Map<String, Integer> doubleFields) {
        Map<String, Double> avgValues = new HashMap<>();

        for (Map.Entry<String, Integer> entry : doubleFields.entrySet()) {
            String fieldName = entry.getKey();

            double sum = 0;
            int count = 0;

            for (Data dataInstance : data) {
                try {
                    Field field = Data.class.getDeclaredField(fieldName);
                    field.setAccessible(true);

                    if (field.getType() == double.class || field.getType() == Double.class) {
                        double fieldValue = (double) field.get(dataInstance);
                        sum += fieldValue;
                        count++;
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (count > 0) {
                double avg = sum / count;
                avgValues.put(fieldName + "_AVG", avg);
            }
        }

        return avgValues;
    }

    public static String[] getHeaders() {
        Field[] fields = Data.class.getDeclaredFields();
        List<String> headers = new ArrayList<>();

        for (Field field : fields) {
            headers.add(field.getName());
        }

        return headers.toArray(new String[0]);
    }

    public String[] toStringArray(Data dataInstance) {
        List<String> values = new ArrayList<>();
        Field[] fields = Data.class.getDeclaredFields();

        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(dataInstance);

                if (value instanceof Boolean) {
                    values.add((Boolean) value ? "Yes" : "No");
                } else {
                    values.add(String.valueOf(value));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return values.toArray(new String[0]);
    }
}
