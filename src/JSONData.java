public class JSONData {

    private String userName;
    private String isInEU;
    private int activeTotal;
    private int totalPurchases;
    private double totalSpendEUR;
    private String payment;
    private String usedWebBrowser;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIsInEU() {
        return isInEU;
    }

    public void setIsInEU(String isInEU) {
        this.isInEU = isInEU;
    }

    public int getActiveTotal() {
        return activeTotal;
    }

    public void setActiveTotal(int activeTotal) {
        this.activeTotal = activeTotal;
    }

    public int getTotalPurchases() {
        return totalPurchases;
    }

    public void setTotalPurchases(int totalPurchases) {
        this.totalPurchases = totalPurchases;
    }

    public double getTotalSpendEUR() {
        return totalSpendEUR;
    }

    public void setTotalSpendEUR(double totalSpendEUR) {
        this.totalSpendEUR = totalSpendEUR;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getUsedWebBrowser() {
        return usedWebBrowser;
    }

    public void setUsedWebBrowser(String usedWebBrowser) {
        this.usedWebBrowser = usedWebBrowser;
    }

    @Override
    public String toString() {
        return "JSONData{" +
                "userName='" + userName + '\'' +
                ", isInEU=" + isInEU +
                ", activeTotal=" + activeTotal +
                ", totalPurchases=" + totalPurchases +
                ", totalSpendEUR=" + totalSpendEUR +
                ", payment='" + payment + '\'' +
                ", usedWebBrowser='" + usedWebBrowser + '\'' +
                '}';
    }
}
