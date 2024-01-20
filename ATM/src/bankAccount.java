
public abstract class bankAccount {

    public String firstName;
    public String lastName;
    public int pin;
    public double balance;

    bankAccount(String firstName, String lastName, int pin, double balance){
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.pin = pin;
    }


    public void deposit(double amount){
        balance += amount;
    }

    public void withdraw(double amount){
        balance -= amount;
    }

    public void transfer(double amount, bankAccount otherAccount){
        this.withdraw(amount);
        otherAccount.deposit(amount);
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    public void setPassCode(String newPasscode, String oldPasscode) {
//
//        if (oldPasscode.equals(passCode)){
//            this.passCode = newPasscode;
//        }else{
//            System.out.println("Invalid passcode");
//        }
//
//
//    }

    public double getBalance() {
        return balance;
    }


}

class cheqAccount extends bankAccount{
    private int accountNumber;
    private int FREE_TRANSACTIONS = 30;
    private final double TRANSACTION_FEE = 0.25;
    private final double MIN_BALANCE = 10.00;
    private final double lowBALANCE_FEE = 0.50;
    private final double MAX_WITHDRAWAL = 1000.00;

    cheqAccount(String firstName, String lastName, int pin, double balance) {
        super(firstName, lastName, pin, balance);
    }

    cheqAccount(String firstName, String lastName, int pin, double balance, int accountNumber){
        super(firstName, lastName, pin, balance);

        // Create account number
        this.accountNumber = accountNumber;
    }

    @Override
    public void deposit(double amount) {
        if (amount < 0){
            throw new IllegalArgumentException("Invalid deposit request");
        }else{
            if(FREE_TRANSACTIONS <= 0){
                super.deposit(amount-TRANSACTION_FEE);
            }else{
                super.deposit(amount);
            }
            FREE_TRANSACTIONS--;
        }

    }

    @Override
    public void withdraw(double amount){
        if (amount < 0 || amount>balance){
            throw new IllegalArgumentException("Invalid withdraw request");
        }else if(amount > MAX_WITHDRAWAL){
            throw new IllegalArgumentException("Exceeds maximum withdrawal amount");
        }else{
            if(FREE_TRANSACTIONS <= 0){
                super.withdraw(amount+TRANSACTION_FEE);
            }else{
                super.withdraw(amount);
            }
            FREE_TRANSACTIONS--;
        }
    }

    @Override
    public void transfer(double amount, bankAccount otherAccount){
        if (amount < 0 || amount > balance){
            throw new IllegalArgumentException("Invalid transfer request");
        }else{
            if(FREE_TRANSACTIONS <= 0){
                super.transfer(amount+TRANSACTION_FEE,otherAccount);
            }else{
                super.transfer(amount, otherAccount);
            }
            FREE_TRANSACTIONS--;
        }
    }

}




class savingAccount extends bankAccount{
    private int accountNumber;
    private int FREE_TRANSACTIONS = 10;
    private final double TRANSACTION_FEE = .75;
    private final double INTEREST_RATE = 1.85;
    private final double MAX_WITHDRAWAL = 1000;

    savingAccount(String firstName, String lastName, int pin, double balance) {
        super(firstName, lastName, pin, balance);
    }

    savingAccount(String firstName, String lastName, int pin, double balance, int accountNumber) {
        super(firstName, lastName, pin, balance);
        this.accountNumber = accountNumber;
    }

    @Override
    public void deposit(double amount) {
        if (amount < 0){
            throw new IllegalArgumentException("Invalid deposit request");
        }else{
            if(FREE_TRANSACTIONS <= 0){
                super.deposit(amount-TRANSACTION_FEE);
            }else{
                super.deposit(amount);
            }
            FREE_TRANSACTIONS--;
        }

    }

    @Override
    public void withdraw(double amount){
        if (amount < 0 || amount>balance){
            throw new IllegalArgumentException("Invalid withdraw request");
        }else if(amount > MAX_WITHDRAWAL){
            throw new IllegalArgumentException("Exceeds maximum withdrawal amount");
        }else{
            if(FREE_TRANSACTIONS <= 0){
                super.withdraw(amount+TRANSACTION_FEE);
            }else{
                super.withdraw(amount);
            }
            FREE_TRANSACTIONS--;
        }
    }

    @Override
    public void transfer(double amount, bankAccount otherAccount){
        if (amount < 0 || amount > balance){
            throw new IllegalArgumentException("Invalid transfer request");
        }else{
            if(FREE_TRANSACTIONS <= 0){
                super.transfer(amount,otherAccount);
                super.withdraw(TRANSACTION_FEE);
            }else{
                super.transfer(amount, otherAccount);
            }
            FREE_TRANSACTIONS--;
        }
    }

    public void addInterest(){

    }




}