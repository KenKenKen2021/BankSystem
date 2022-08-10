import java.text.ParseException;
import java.util.Scanner;

/**
 * @author laoji
 */
public class BankSystem {
    static int MAX_SIZE = 999;
    static int accountCounter = 1;

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        //create initial accounts

        BankDetails[] bankDetails = new BankDetails[MAX_SIZE];

        // loop runs until number 5 is not pressed to exit
        int ch;
        bankDetails[0] = new BankDetails("OSL_FEE");
        bankDetails[0].createAC(0);
        bankDetails[0].createAC(1);
        bankDetails[0].createAC(2);
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
                        if (bankDetails[i].searchUser(name)) {
                            haveUser = true;
                            id = i;

                        }
                    }
                    int type=-1;
                    if (haveUser) {
                        bankDetails[accountCounter-1].showAC();
                        System.out.println("Enter type of Currency (0 is HKD, 1 is USD, 2 is SGD) : ");
                        type = sc.nextInt();
                        if (type < 0 || type > 2) {
                            System.out.println("Wrong type,Please input again: ");
                            break;
                        }
                        if (bankDetails[id].checkAc(type)) {
                            System.out.println("You already have this type of currency sub account,Please try again: ");
                            break;
                        }
                        bankDetails[id].createAC(type);
                        System.out.println("Create sub account succeed!!");
                        bankDetails[accountCounter-1].showAC();

                    } else {
                        System.out.println("Enter type of Currency (0 is HKD, 1 is USD, 2 is SGD) : ");
                        type = sc.nextInt();
                        bankDetails[accountCounter] = new BankDetails(name);
                        bankDetails[accountCounter].createAC(type);
                        System.out.println("Create account succeed,Please remember your ID" + "：" + accountCounter);
                        bankDetails[accountCounter].showAC();
                        accountCounter++;

                    }


                    break;
                case 2:
                    System.out.print("Enter Your ID: ");
                    int acNo = sc.nextInt();
                    int acNoOther = 0;
                    bankDetails[acNo].showAC();
                    System.out.print("Enter Your type of Currency (0 is HKD, 1 is USD, 2 is SGD): ");
                    type = sc.nextInt();
                    System.out.print("Enter the amount of money : ");
                    int amount = sc.nextInt();
                    boolean exit = false;
                    if (acNo < 0 || acNo >= accountCounter) {
                        System.out.println("Deposit failed! Account doesn't exist..!!");
                    } else {
                        exit = bankDetails[acNo].checkAc(type);
                    }
                    if (exit) {
                        bankDetails[acNo].deposit(amount, type, "Deposit");


                    } else {
                        System.out.println("Deposit failed! You do no have this type of Currency's account..!!");
                    }

                    break;
                case 3:
                    System.out.print("Enter Your ID.: ");
                    acNo = sc.nextInt();
                    bankDetails[acNo].showAC();
                    System.out.print("Enter Your type of Currency (0 is HKD, 1 is USD, 2 is SGD): ");
                    type = sc.nextInt();
                    System.out.print("Enter the amount of money : ");
                    amount = sc.nextInt();
                    exit = false;
                    if (acNo < 0 || acNo >= accountCounter) {
                        System.out.println("Withdraw failed! Account doesn't exist..!!");
                    } else {
                        exit = bankDetails[acNo].checkAc(type);
                    }
                    if (exit) {
                        if (bankDetails[acNo].withdrawal(amount, type, "Withdraw")) {
                            bankDetails[0].transactionFee(amount, type, "Withdraw Fee");
                        }


                    } else {
                        System.out.println("Deposit failed! You do no have this type of Currency's account..!!");
                    }
                    break;
                case 4:
                    boolean exitMe = false;
                    boolean exitYou = false;
                    System.out.print("Enter your Account No : ");
                    acNo = sc.nextInt();
                    bankDetails[acNo].showAC();
                    System.out.print("Enter the Account you want to transfer : ");
                    acNoOther = sc.nextInt();
                    System.out.print("Enter the type of Currency (0 is HKD, 1 is USD, 2 is SGD): ");
                    type = sc.nextInt();
                    System.out.print("Enter the amount of money : ");
                    amount = sc.nextInt();
                    if (acNo < 0 || acNo >= accountCounter) {
                        System.out.println("Transfer failed! Account doesn't exist..!!");
                    } else {
                        exitMe = bankDetails[acNo].checkAc(type);
                        exitYou = bankDetails[acNoOther].checkAc(type);
                    }
                    if (exitMe && exitYou) {
                        if (bankDetails[acNo].withdrawal(amount, type, "Transfer out")) {
                            bankDetails[acNoOther].deposit(amount, type, "Transfer in");
                            bankDetails[0].transactionFee(amount, type, "Transfer Fee");
                        }


                    } else {
                        System.out.println("Transfer failed! You or you target did not match the type of Currency..!!");
                    }
                    break;
                case 5:
                    System.out.println("Enter Account No... : ");
                    acNo = sc.nextInt();
                    bankDetails[acNo].showAC();
                    System.out.println("Enter Account Currency... (0 is HKD, 1 is USD, 2 is SGD): ");
                    type = sc.nextInt();
                    exit = false;
                    if (acNo < 0 || acNo >= accountCounter) {
                        System.out.println("Checking the Balance failed! ID doesn't exist..!!");
                    } else {
                        exit = bankDetails[acNo].checkAc(type);
                    }
                    if (exit) {
                        bankDetails[acNo].showBalance(type);
                    } else {
                        System.out.println("Checking the Balance failed! Account doesn't exist..!!");
                    }
                    break;
                case 6:
                    System.out.println("Enter Account ID... : ");
                    acNo = sc.nextInt();

                    if (acNo < 0 || acNo >= accountCounter) {
                        System.out.println("Checking the Transaction failed! ID doesn't exist..!! ID doesn't exist..!!");
                    } else {
                        bankDetails[acNo].showTransaction();

                    }

                    break;
                case 7:
                    System.out.println("See you soon...");
                    break;
                default:
                    System.out.println("Default~~~~");
            }
        } while (ch != 7);
    }
}

