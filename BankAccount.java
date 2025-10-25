/**
 * Project 4 Bank Account
 * bankAccount class that holds an account number and a balance. It determines
 * if the amount of money the user has can "survive" 3 withdrawals set by the tester
 * @author Lane woods
 */
public class BankAccount {
    //Variables for our bank account
    final private int acctNumber;
    private double balance;

    /**
     * Constructor for BankAccount class that also defaults the balance to 0.
     * @param AccountNumber Account number of the bank account
     */
    public BankAccount(int AccountNumber) {
        this.acctNumber = AccountNumber;
        this.balance = 0;
    }

    /**
     * Gets the account number of the bank account.
     * @return Account number of the bank account
     */
    public int getAccountNumber() {
        return acctNumber;
    }

    /**
     * Gets the balance of the bank account.
     * @return Balance of the bank account.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Deposits money into the bank account. Checks if the amount being deposited
     * is not negative.
     * @param money Money to deposit
     */
    public void deposit(double money){
        if (money < 0) {
            //Throws as exception if negative money is deposited
            throw new IllegalArgumentException("Cannot deposit negative money!");
        } else {
            balance = getBalance() + money;
        }
    }

    /**
     * Withdraws money from the bank account. Checks if the amount being withdrawn
     * is negative or if there is not a sufficient balance.
     * @param money Money to withdraw
     * @throws InadequateMoneyException If money is negative or more than amount
     * in balance
     */
    public void withdraw(double money) throws InadequateMoneyException {
        if (money < 0){
            //Throws as exception if negative money is withdrawn
            throw new IllegalArgumentException("Cannot withdraw negative money!");
        }
        else if (money > getBalance()){
            //Was not working at first due to not throwing InadequateMoneyException
            throw new InadequateMoneyException(money, "You need more money!");
        }
        else {
            balance = getBalance() - money;
        }
    }
}
