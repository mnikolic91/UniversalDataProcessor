import java.util.List;
import java.util.Map;

public class JSONAdapter implements DataProcessor<JSONData> {

    private JSONUtils<JSONData> jsonUtils;


    @Override
    public StartingDataContainer<JSONData> readFromFile(String pathToFile, boolean hasHeader, Class<JSONData> type) {
        List<JSONData> dataList = jsonUtils.readDataFromFile(pathToFile, type);
        StartingDataContainer<JSONData> container = new StartingDataContainer<>(dataList, hasHeader);
        return container;
    }

    @Override
    public void saveDataToFile(List<JSONData> data, String pathToFile) {
        jsonUtils.saveDataToFile(data, pathToFile);
    }

    @Override
    public void listAllRecords(List<JSONData> dataRec, String[] headers) {
        jsonUtils.listAllFetchRecords(dataRec);
    }

    @Override
    public void listParticularRecord(List<JSONData> dataRec, int index) {
        jsonUtils.listRecordInPosition(dataRec, index);
    }

    @Override
    public Map<String, Integer> findAllDoubleFields(List<JSONData> data) {
        return jsonUtils.findDoubleFields(data);
    }

    @Override
    public Map<String, Double> calculateAvgValues(List<JSONData> data, Map<String, Integer> doubleFields) {
        return jsonUtils.calculateAvgValues(data, doubleFields);

    }

    public void setJsonUtils(JSONUtils<JSONData> jsonUtils) {
        this.jsonUtils = jsonUtils;
    }

}
