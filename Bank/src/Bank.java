import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Bank {
    private static int count;
    static HashMap<String, CheckingAccount> accounts;
    static HashMap<LocalDate, ArrayList<Transaction>> daily_trans;

    public Bank(){
        count = 0;
        accounts = new HashMap<>();
        daily_trans = new HashMap<>();
    }

    private static String generateUid() {
        StringBuilder sb = new StringBuilder();
        sb.append(count++);
        return sb.toString();
    }

    public static CheckingAccount addAccount(String name, String password) {
        String Uid = generateUid();
        CheckingAccount a = new CheckingAccount(Uid, name, password);
        accounts.put(Uid, a);
        return a;
    }

    public static boolean signIn(String Uid, String password) {
        if(!accounts.containsKey(Uid)) {
            System.out.println("123");
            return false;
        }

        if(!accounts.get(Uid).getPassword().equals(password)) {
            return false;
        }
        return true;
    }

    public static void addDaily(Transaction transaction){
        LocalDate localDate = transaction.date;
        if (Bank.daily_trans.containsKey(localDate)){
            Bank.daily_trans.get(localDate).add(transaction);
        }else {
            ArrayList<Transaction> tran_list = new ArrayList<>();
            tran_list.add(transaction);
            Bank.daily_trans.put(localDate,tran_list);
        }
    }


}