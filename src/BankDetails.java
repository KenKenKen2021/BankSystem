import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author laoji
 */
public class BankDetails {
    static int MAX_SIZE = 999;
    private final String UserName;

    private final Transaction[] transaction;

    private final Account[] account;

    private int accountCounter = 0;
    private int transactionCounter = 0;

    private int maxWithdraw = 0;

    public BankDetails(String userName) {
        transaction = new Transaction[MAX_SIZE];
        UserName = userName;
        account = new Account[3];
    }

    public boolean checkAc(int type) {
        String Currency = getType(type);

        for (int i = 0; i < accountCounter; i++) {
            if (account[i].getCurrency().equals(Currency)) {
                return true;
            }
        }
        return false;
    }

    public void createAC(int type) {
        account[accountCounter] = new Account(type);
        accountCounter++;

    }


    public void showBalance(int type) {
        int num = 0;
        for (int i = 0; i < accountCounter; i++) {
            if (account[i].getCurrency().equals(getType(type))) {
                num = i;
            }
        }
        double balance = account[num].getBalance();
        System.out.print("Your account Balance is : " + balance);
    }

    public void showTransaction() {

        if (transactionCounter == 0) {
            System.out.println("No transaction to show !");
            return;
        }
        System.out.println("Client Name:  " + UserName);
        System.out.println("Date" + "                                    " + "Currency" + "        " + "    Operation" + "                " + "Amount");
        for (int i = 0; i < transactionCounter; i++) {
            transaction[i].showTransaction();
        }
        System.out.println("===========================================================================================================");
    }

    public void deposit(double amount, int type, String operation) {
        int ac = searchAc(type);
        Date date = new Date();
        String Currency = getType(type);
        if (ac != -1) {
            account[ac].setBalance(amount);
            transaction[transactionCounter] = new Transaction(UserName, date.toString(), Currency, operation, amount);
            transactionCounter++;
        }
        System.out.println("Despite succeed!Now " + UserName + "'s balance is : " + account[ac].getBalance() + " in " + account[ac].getCurrency());

    }

    public void transactionFee(double amount, int type, String operation) {
        int ac = searchAc(type);
        Date date = new Date();
        String Currency = getType(type);
        if (ac != -1) {
            account[ac].setBalance(amount * 0.01);
            transaction[transactionCounter] = new Transaction(UserName, date.toString(), Currency, operation, amount * 0.01);
            transactionCounter++;
        }

    }

    public boolean withdrawal(double amount, int type, String operation) throws ParseException {

        if (transactionCounter != 0) {
            int ac = searchAc(type);
            Date date = new Date();
            String Currency = getType(type);


            String oldTime = transaction[transactionCounter - 1].getDate();
            SimpleDateFormat sdf3 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
            Date d1 = sdf3.parse(oldTime);


            if (date.getTime() - d1.getTime() > 5 * 1000 * 60) {
                maxWithdraw = 0;

            }
            if (maxWithdraw < 5) {
                if (ac != -1) {
                    if (account[ac].getBalance() - amount * 1.01 >= 0) {
                        account[ac].setBalance(-amount * 1.01);
                        transaction[transactionCounter] = new Transaction(UserName, date.toString(), Currency, operation, amount);
                        transactionCounter++;
                        transaction[transactionCounter] = new Transaction(UserName, date.toString(), Currency, "Withdraw Fee", amount * 0.01);
                        transactionCounter++;
                        System.out.println("Withdraw succeed!Now " + UserName + "'s balance is : " + account[ac].getBalance() + " in " + account[ac].getCurrency());
                        maxWithdraw++;

                        return true;
                    } else {
                        System.out.println("Withdraw failed! Account doesn't enough balance..!!");
                    }
                }

            } else {
                double remainTime = (5 * 1000 * 60 - (date.getTime() - d1.getTime())) / 60000;
                double toPrint = remainTime + 1;
                System.out.println("Withdraw time can not be more than 5 mins.Please wait " + toPrint + " mins. to reset");
                return false;
            }
        } else {
            System.out.println("No Money in this account");
        }

        return false;
    }

    public int searchAc(int type) {
        String Currency = getType(type);

        for (int i = 0; i < accountCounter; i++) {
            if (account[i].getCurrency().equals(Currency)) {
                return i;
            }
        }
        return -1;
    }

    public boolean searchUser(String name) {
        return name.equals(UserName);
    }

    public String getType(int type) {
        String Currency = null;
        if (type == 0) {
            Currency = "HKD";
        }
        if (type == 1) {
            Currency = "USD";
        }
        if (type == 2) {
            Currency = "SGD";
        }
        return Currency;
    }


}
