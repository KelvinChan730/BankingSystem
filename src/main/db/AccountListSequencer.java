package main.db;

public class AccountListSequencer extends Sequencer {
    private static AccountListSequencer instance = null;

    private AccountListSequencer() {

    }

    public static AccountListSequencer getInstance() {
        if (instance == null)
            instance = new AccountListSequencer();
        return instance;
    }
}
