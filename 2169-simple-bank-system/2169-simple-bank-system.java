class Bank {
    // data fields
    private long[] account; // instance variable to store balances

    // constructor
    public Bank(long[] balance) {
        // initialize account balances
        account = balance;
    }

    // methods
    private boolean validAccount(int acc) {
        // check if account number is valid (1-indexed)
        return acc >= 1 && acc <= account.length;
    }

    public boolean transfer(int account1, int account2, long money) {
        if (!validAccount(account1) || !validAccount(account2)) return false;
        if (account[account1 - 1] < money) return false;

        // transfer funds
        account[account1 - 1] -= money;
        account[account2 - 1] += money;
        return true;
    }

    public boolean deposit(int accountNumber, long money) {
        if (!validAccount(accountNumber)) return false;

        account[accountNumber - 1] += money;
        return true;
    }

    public boolean withdraw(int accountNumber, long money) {
        if (!validAccount(accountNumber)) return false;
        if (account[accountNumber - 1] < money) return false;

        account[accountNumber - 1] -= money;
        return true;
    }
}
