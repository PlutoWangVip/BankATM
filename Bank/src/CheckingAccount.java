import java.time.LocalDate;

public class CheckingAccount extends Account {
    protected String Aid;
    private SavingAccount saving;

    CheckingAccount(String u, String n, String p) {
        super(u, n, p);
        saving = null;
        setAid();
    }

    protected void setAid() {
        Aid = this.getUid() + "-0";
    }

    public boolean hasSaving() {
        return saving == null ? false : true;
    }

    public SavingAccount getSavingAccount() {
        return saving;
    }

    @Override
    public boolean createSaving() {
        if(hasSaving()) {
            return false;
        }
        saving = new SavingAccount(getUid(), getName(), getPassword());
        return true;
    }

    @Override
    public int transaction(String aid, String cur, double money){

        if (aid.split("-").length<2){
            return -3;
        }

        String uid = aid.split("-")[0];
        String a = aid.split("-")[1];
        if (!Bank.accounts.containsKey(uid)){
            return -1;
        }else if ((!(Bank.accounts.get(uid)).hasSaving())&&a.equals("1")){
            System.out.println((Bank.accounts.get(uid)).hasSaving());
            System.out.println(uid);
            System.out.println(a);
            return -1;
        }
        if (balances.get(cur)<money){
            return -2;
        }
        double temp = balances.get(cur) - money;
        balances.put(cur, temp);

        CheckingAccount checkingAccount = Bank.accounts.get(uid);
        if (a.equals("0")){
            temp = checkingAccount.balances.get(cur) + (money * (1-FEE));
            checkingAccount.balances.put(cur, temp);

            LocalDate date = LocalDate.now();
            Transaction transaction = new Transaction(Aid, aid,money,cur, date);
            this.transactions.add(transaction);
            checkingAccount.transactions.add(transaction);
            Bank.addDaily(transaction);

        }else {
            SavingAccount savingAccount = checkingAccount.getSavingAccount();
            temp = savingAccount.balances.get(cur) + (money * (1-FEE));
            savingAccount.balances.put(cur, temp);

            LocalDate date = LocalDate.now();
            Transaction transaction = new Transaction(Aid, aid,money,cur, date);
            this.transactions.add(transaction);
            savingAccount.transactions.add(transaction);
            Bank.addDaily(transaction);

        }

        return 1;
    }

}