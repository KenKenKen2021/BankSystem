public class Transaction {
    private final String Name;

    public String getDate() {
        return Date;
    }

    private final String Date;
    private final String Currency;
    private final String Operation;
    private final double Amount;

    public Transaction(String name, String date, String currency, String operation, double amount) {
        Name = name;
        Date = date;
        Currency = currency;
        Operation = operation;
        Amount = amount;
    }
    public void showTranscation()
    {


        System.out.println(Date+"              "+Currency+"                "+Operation+"             "+Amount);

    }
}
