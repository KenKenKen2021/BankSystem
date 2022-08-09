import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
    private int transactionCounter =0;

    private int maxWithdraw =0;
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

        if(transactionCounter ==0)
        {
            System.out.println("No transaction to show !");
            return;
        }
        System.out.println("Client Name:  "+UserName);
        System.out.println("Date"+"                                    "+"Currency"+"        "+"    Operation"+"                "+"Amount");
        for (int i = 0; i< transactionCounter; i++)
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
        account[ac].setBalance(amount);
        transcation[transactionCounter]= new Transaction(UserName,date.toString(),Currency,operation,amount);
        transactionCounter++;
        //transcation[transcationCounter]= new Transaction(UserName,date.toString(),Currency,"Deposit Fee",amount*0.01);
       // transcationCounter++;
        }
        System.out.println("Despite succeed!Now "+UserName+"'s balance is : "+account[ac].getBalance()+" in "+account[ac].getCurrency());

    }
    public void transactionFee(double amount, int type, String operation) {
        int ac=searchAc(type);
        Date date = new Date();
        String Currency=getType(type);
        if(ac!=-1)
        {
            account[ac].setBalance(amount*0.01);
            transcation[transactionCounter]= new Transaction(UserName,date.toString(),Currency,operation,amount*0.01);
            transactionCounter++;
        }

    }
    public boolean withdrawal(double amount, int type,String operation) throws ParseException {

        int ac=searchAc(type);
        LocalDateTime date = LocalDateTime.now();
        String Currency=getType(type);
        if(maxWithdraw<1){
        if(ac!=-1) {
            if (account[ac].getBalance() - amount * 1.01 >= 0) {
                account[ac].setBalance(-amount * 1.01);
                transcation[transactionCounter] = new Transaction(UserName, date.toString(), Currency, operation, amount);
                transactionCounter++;
                transcation[transactionCounter] = new Transaction(UserName, date.toString(), Currency, "Withdraw Fee", amount * 0.01);
                transactionCounter++;
                System.out.println("Withdraw succeed!Now " + UserName + "'s balance is : " + account[ac].getBalance() + " in " + account[ac].getCurrency());

                maxWithdraw++;
                System.out.println("counter======"+maxWithdraw);
                return true;
            } else {
                System.out.println("Withdraw failed! Account doesn't enough balance..!!");
            }
        }

        }
        else {
            System.out.println("counter==5");
            String save= transcation[transactionCounter - 1].getDate();
            System.out.println(save); // Tue Dec 31 23:59:59 CST 2019
            //Date oldDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(save);
            SimpleDateFormat sdf3 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            Date d1 = null;

                d1 = sdf3.parse(save);

           // System.out.println(oldDate); // Tue Dec 31 23:59:59 CST 2019
          //  if(date.getTime()-oldDate.getTime()>= 20*60*1000)
           //// {
          //      maxWithdraw=0;
          //  }
        }

        return  false;
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
