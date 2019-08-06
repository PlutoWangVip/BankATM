import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class RecordFrame extends JFrame implements ActionListener {

    JTable jTable;
    JButton jButton = new JButton("Back");
    CheckingAccount checkingAccount;

    RecordFrame(CheckingAccount checkingAccount){
        this.checkingAccount = checkingAccount;

        Vector<String> col = new Vector<>();

        col.add("Sender");
        col.add("Receiver");
        col.add("Currency");
        col.add("Amount");
        col.add("Date");

        Vector<Vector<Object>> tableData = new Vector<>();

        for (int i=0;i<checkingAccount.transactions.size();i++){
            Vector<Object> list = new Vector<>();
            list.add(checkingAccount.transactions.get(i).sender);
            list.add(checkingAccount.transactions.get(i).receiver);
            list.add(checkingAccount.transactions.get(i).currency);
            list.add(checkingAccount.transactions.get(i).money);
            list.add(checkingAccount.transactions.get(i).date);
            tableData.add(list);
        }

        jButton.addActionListener(this);

        jTable = new JTable(tableData,col);
        JScrollPane jScrollPane = new JScrollPane(jTable);

        this.setTitle("Transaction List");
        this.add(jScrollPane);
        this.add(jButton, BorderLayout.SOUTH);
        this.setLocation(500,300);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        new AccountFrame(checkingAccount);
    }
}
