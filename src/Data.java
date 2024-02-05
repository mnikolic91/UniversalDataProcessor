public class Data {

    private String userName;
    private int yearsActive;
    private int numberOfTotalPurchases;
    private double totalSpendEUR;
    private boolean isInEU;
    private String preferredPaymentMethod;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getYearsActive() {
        return yearsActive;
    }

    public void setYearsActive(int yearsActive) {
        this.yearsActive = yearsActive;
    }

    public int getNumberOfTotalPurchases() {
        return numberOfTotalPurchases;
    }

    public void setNumberOfTotalPurchases(int numberOfTotalPurchases) {
        this.numberOfTotalPurchases = numberOfTotalPurchases;
    }

    public double getTotalSpendEUR() {
        return totalSpendEUR;
    }

    public void setTotalSpendEUR(double totalSpendEUR) {
        this.totalSpendEUR = totalSpendEUR;
    }

    public boolean isInEU() {
        return isInEU;
    }

    public void setInEU(boolean inEU) {
        isInEU = inEU;
    }

    public String getPreferredPaymentMethod() {
        return preferredPaymentMethod;
    }

    public void setPreferredPaymentMethod(String preferredPaymentMethod) {
        this.preferredPaymentMethod = preferredPaymentMethod;
    }

    @Override
    public String toString() {
        return "Data{" +
                "userName='" + userName + '\'' +
                ", yearsActive=" + yearsActive +
                ", numberOfTotalPurchases=" + numberOfTotalPurchases +
                ", totalSpendEUR=" + totalSpendEUR +
                ", isInEU=" + isInEU +
                ", preferredPaymentMethod='" + preferredPaymentMethod + '\'' +
                '}';
    }
}
