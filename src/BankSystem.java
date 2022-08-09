import java.util.Scanner;

/**
 * @author laoji
 */
public class BankSystem {
    static int MAX_SIZE = 999;
    static int accountCounter = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //create initial accounts

        BankDetails C[] = new BankDetails[MAX_SIZE];

        // loop runs until number 5 is not pressed to exit
        int ch;
        C[0] = new BankDetails("OSL_FEE");
        C[0].createAC(0);
        C[0].createAC(1);
        C[0].createAC(2);
        do {
            System.out.println("\n ***Banking System Application***");
            System.out.println("***********************************************************************************************************");
            System.out.println(" 1.Create Account \n 2.Money deposit\n 3.Money withdrawal \n 4.Money transfer \n 5.List bank account balance \n 6.Display transaction statement  \n 7.Exit ");
            System.out.println("Enter your choice: ");
            ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("Enter UserName ");
                    String name = sc.next();
                    boolean haveUser = false;
                    int id = 0;
                    for (int i = 0; i < accountCounter; i++) {
                        if (C[i].searchUser(name)) {
                            haveUser = true;
                            id = i;

                        }
                    }
                    int type;
                    System.out.println("Enter type of Currency (0 is HKD, 1 is USD, 2 is SGD) : ");
                    type = sc.nextInt();

                    if (type < 0 || type > 2) {
                        System.out.println("Wrong type,Please input again: ");
                        break;
                    }


                    if (haveUser) {
                        if (C[id].checkAc(type)) {
                            System.out.println("You already have this type of account,Please try again: ");
                            break;
                        }
                        C[id].createAC(type);


                    } else {
                        C[accountCounter] = new BankDetails(name);
                        C[accountCounter].createAC(type);
                        System.out.println("Create Account Succeed,Please remember your ID" + "：" + accountCounter);
                        C[accountCounter].showAccount();
                        accountCounter++;

                    }


                    break;
                case 2:
                    System.out.print("Enter Your ID: ");
                    int ac_no = sc.nextInt();
                    int ac_no_other =0;
                    System.out.print("Enter Your type of Currency : ");
                    type = sc.nextInt();
                    System.out.print("Enter the amount of money : ");
                    int amount = sc.nextInt();
                    boolean exit=false;
                    if(ac_no<0||ac_no>=accountCounter)
                    {
                        System.out.println("Deposit failed! Account doesn't exist..!!");
                    }
                    else
                    {
                        exit=C[ac_no].checkAc(type);
                    }
                    if(exit)
                    {
                        C[ac_no].deposit(amount,type,"Deposit");
                        C[0].transcationFee(amount*0.01,type,"Deposit Fee");

                    }
                    else{
                        System.out.println("Deposit failed! You do no have this type of Currency's account..!!");
                    }

                    break;
                case 3:
                    System.out.print("Enter Your ID.: ");
                    ac_no = sc.nextInt();
                    System.out.print("Enter Your type of Currency : ");
                    type = sc.nextInt();
                    System.out.print("Enter the amount of money : ");
                    amount = sc.nextInt();
                    exit=false;
                    if(ac_no<0||ac_no>=accountCounter)
                    {
                        System.out.println("Withdraw failed! Account doesn't exist..!!");
                    }
                    else
                    {
                        exit=C[ac_no].checkAc(type);
                    }
                    if(exit)
                    {  C[ac_no].withdrawal(amount*0.99,type,"Withdraw");
                        C[0].transcationFee(amount*0.01,type,"Withdraw Fee");

                    }
                    else{
                        System.out.println("Deposit failed! You do no have this type of Currency's account..!!");
                    }
                    break;
                case 4:
                    int action=0;
                    System.out.print("Enter your Account No : ");
                    ac_no = sc.nextInt();
                    System.out.print("Enter the Account you want to transfer : ");
                    ac_no_other= sc.nextInt();
                    System.out.print("Enter the type of Currency : ");
                    type = sc.nextInt();
                    System.out.print("Enter the amount of money : ");
                    amount = sc.nextInt();
                    System.out.print("Select action\n1.Create Account \n2.Money deposit");
                    action = sc.nextInt();
                    break;
                case 5:
                    System.out.println("Enter Account No... : ");
                    ac_no = sc.nextInt();
                    System.out.println("Enter Account Currency... : ");
                    type=sc.nextInt();
                    exit=false;
                    if(ac_no<0||ac_no>=accountCounter)
                    {
                        System.out.println("Checking the Balance failed! ID doesn't exist..!!");
                    }
                    else
                    {
                        exit=C[ac_no].checkAc(type);
                    }
                    if(exit)
                    {
                        C[ac_no].showBalance(type);
                    }
                    else{
                        System.out.println("Checking the Balance failed! Account doesn't exist..!!");
                    }
                    break;
                case 6:
                    System.out.println("Enter Account ID... : ");
                    ac_no = sc.nextInt();

                    if(ac_no<0||ac_no>=accountCounter)
                    {
                        System.out.println("Checking the Transaction failed! ID doesn't exist..!! ID doesn't exist..!!");
                    }
                    else
                    {
                        C[ac_no].showTransaction();

                    }

                    break;
                case 7:
                    System.out.println("See you soon...");
                    break;
                default:
                    System.out.println("Default~~~~");
            }
        }
        while (ch != 7);
    }
}

