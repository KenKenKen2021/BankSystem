public class Account {
    public Account(int type) {
        if (type == 0) {
            Currency = "HKD";
        }
        if (type == 1) {
            Currency = "USD";
        }
        if (type == 2) {
            Currency = "SGD";
        }
    }

    public String getCurrency() {
        return Currency;
    }


    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance += balance;
    }

    private String Currency;
    private double Balance = 0;
}
