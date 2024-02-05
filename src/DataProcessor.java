import java.util.List;
import java.util.Map;

/**
 * Utility interfae for reading, saving, and processing data using CSV files.
 * @param <E> the type of objects to be processed (e.g., a data model class).
 */
public interface DataProcessor<E>{



    /**
     * Reads data from a CSV file and returns it as a StartingDataContainer.
     *
     * @param pathToFile the path to the CSV file.
     * @param hasHeader  true if the CSV file has a header, false otherwise.
     * @param type       the class type of the objects to be read.
     * @return a StartingDataContainer containing the read data and header.
     */
    StartingDataContainer<E> readFromFile(String pathToFile, boolean hasHeader, Class<E> type);

    /**
     * Saves data to a CSV file.
     *
     * @param data       the list of objects to be saved.
     * @param pathToFile the path to the CSV file.
     */
    void saveDataToFile(List<E> data, String pathToFile);

    /**
     * Lists all records in the console.
     *
     * @param dataRec  the list of objects to be listed.
     * @param headers       the headers for the columns.
     */
    void listAllRecords(List<E> dataRec, String[] headers);

    /**
     * Lists a particular record based on the specified index.
     *
     * @param dataRec  the list of objects.
     * @param index the index of the record to be listed.
     */
    void listParticularRecord(List<E> dataRec, int index);

    /**
     * Finds all double fields in the data's class and returns their names along with their index.
     *
     * @param data the list of objects.
     * @return a Map containing the names and indices of double fields.
     */
    Map<String, Integer> findAllDoubleFields(List<E> data);

    /**
     * Calculates the average values of double fields in the data.
     *
     * @param data         the list of objects.
     * @param doubleFields a Map containing the names and indices of double fields.
     * @return a Map containing the names and average values of double fields.
     */
    Map<String, Double> calculateAvgValues(List<E> data, Map<String, Integer> doubleFields);
}