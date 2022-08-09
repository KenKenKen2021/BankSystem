import java.util.Date;

/**
 * @author laoji
 */
public class BankDetails {
    static int MAX_SIZE = 999;
    private final String UserName;

    private final Transaction[] transcation;

    private final Account[] account;

    private int accountCounter=0;
    private int transcationCounter=0;
    public BankDetails(String userName) {
        transcation=new Transaction[MAX_SIZE];
        UserName = userName;
        account = new Account[3];
    }

    public boolean checkAc(int type)
    {
        String Currency=getType(type);
            
        for(int i=0;i<accountCounter;i++)
        {
            if(account[i].getCurrency().equals(Currency))
            {
                return true;
            }
        }
        return false;
    }

    public void createAC(int type)
    {
        account[accountCounter]=new Account(type);
        System.out.print("Your account ID: "+accountCounter);
        accountCounter++;

    }



    public void showAccount() {
        System.out.print(UserName);
    }
    public void showBalance(int type) {
        int num=0;
        for(int i=0;i<accountCounter;i++)
        {
            if(account[i].getCurrency().equals(getType(type)))
            {
                num=i;
            }
        }
        double balance=account[num].getBalance();
        System.out.print("Your account Balance is : "+balance);
    }

    public void showTransaction() {
        if(transcationCounter==0)
        {
            System.out.println("No transaction");
        }
        for (int i=0;i<transcationCounter;i++)
        {
            transcation[i].showTranscation();
        }
        System.out.println("===========================================================================================================");
    }

    public void deposit(double amount, int type,String operation) {
        int ac=searchAc(type);
        Date date = new Date();
        String Currency=getType(type);
        if(ac!=-1)
        {
        account[ac].setBalance(amount*0.99);
        transcation[transcationCounter]= new Transaction(UserName,date.toString(),Currency,operation,amount*0.99);
        transcationCounter++;
        transcation[transcationCounter]= new Transaction(UserName,date.toString(),Currency,"Deposit Fee",amount*0.01);
        transcationCounter++;
        }
        System.out.println("Despite succeed!Now you balance is : "+account[ac].getBalance()+" in "+account[ac].getCurrency());

    }
    public void transactionFee(double amount, int type, String operation) {
        int ac=searchAc(type);
        Date date = new Date();
        String Currency=getType(type);
        if(ac!=-1)
        {
            account[ac].setBalance(amount*0.01);
            transcation[transcationCounter]= new Transaction(UserName,date.toString(),Currency,operation,amount*0.01);
            transcationCounter++;
        }

    }
    public void withdrawal(double amount, int type,String operation) {

        int ac=searchAc(type);
        Date date = new Date();
        String Currency=getType(type);
        if(ac!=-1) {
            if (account[ac].getBalance() - amount*1.01 >= 0) {
                account[ac].setBalance(-amount*1.01);
                transcation[transcationCounter] = new Transaction(UserName, date.toString(), Currency, operation, amount);
                transcationCounter++;
                transcation[transcationCounter] = new Transaction(UserName, date.toString(), Currency, "Withdraw Fee", amount*0.01);
                transcationCounter++;
            }
            else
            {
                System.out.println("Withdraw failed! Account doesn't enough balance..!!");
            }
        }
        System.out.print("Now you balance is : "+account[ac].getBalance()+" in "+account[ac].getCurrency());
    }

    public int searchAc(int type)
    {
        String Currency=getType(type);

        for(int i=0;i<accountCounter;i++)
        {
            if(account[i].getCurrency().equals(Currency))
            {
                return i;
            }
        }
     return -1;
    }

    public boolean searchUser(String name) {
        return name.equals(UserName);
    }
    public String getType(int type)
    {
        String  Currency = null;
        if(type==0)
        {
            Currency="HKD";
        }
        if(type==1)
        {
            Currency="USD";
        }
        if(type==2)
        {
            Currency="SGD";
        }
        return  Currency;
    }


}
