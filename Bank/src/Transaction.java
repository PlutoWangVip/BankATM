import java.time.LocalDate;

public class Transaction {
    String sender;
    String receiver;
    double money;
    String currency;
    LocalDate date;

    Transaction(String s, String r, double m, String cur, LocalDate d) {
        sender = s;
        receiver = r;
        money = m;
        currency = cur;
        date = d;
    }
}