import java.util.List;

public class StartingDataContainer<E> {

    private List<E> data;
    private boolean hasHeader;

    public StartingDataContainer(List<E> data, boolean hasHeader) {
        this.data = data;
        this.hasHeader = hasHeader;
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }

    public boolean getHeader() {
        return hasHeader;
    }

    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }


}
