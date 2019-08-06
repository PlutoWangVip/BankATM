import java.util.ArrayList;
import java.util.HashMap;

public class Account {
    public static final double FEE = 0.15;
    protected String Uid;
    protected String name;
    protected String password;
    protected HashMap<String,Double> balances;

    protected ArrayList<Transaction> transactions;

    public static final String[] currency = {"CNY" , "USD", "GBD"};

    public Account(String u, String n, String p) {
        setUid(u);
        setName(n);
        setPassword(p);

        transactions = new ArrayList<>();
        balances = new HashMap<>();

        for(String s : currency) {
            balances.put(s, 0.0);
        }
    }

    public boolean deposit(String cur, double money){
        double temp = balances.get(cur) + money;
        balances.put(cur, temp);
        return true;
    }

    public boolean withdraw(String cur, double money) {
        money = money * (1 + FEE);
        if(!balances.containsKey(cur) || balances.get(cur) < money) {
            return false;
        }
        double temp = balances.get(cur) - money;
        balances.put(cur, temp);
        return true;
    }

    public int transaction(String aid, String cur, double money){

        return 0;
    }

    public void loan(String cur, double money) {
        double temp = balances.get(cur) - mortgage(money);
        balances.put(cur, temp);
    }

    public double mortgage(double money) {
        return 1.2 * money;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        this.Uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean createSaving() {
        return true;
    }

}