import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Vector;

public class ManagerFrame extends JFrame implements ActionListener {

    JTable jTable;
    JPanel jPanel = new JPanel();
    JButton close = new JButton("Back");
    JButton daily = new JButton("Daily Transaction");

    ManagerFrame(){

        Vector<String> col = new Vector<>();
        col.add("Name");
        col.add("User ID");
        col.add("Checking Account_CNY");
        col.add("Checking Account_USD");
        col.add("Checking Account_GBD");
        col.add("Saving Account_CNY");
        col.add("Saving Account_USD");
        col.add("Saving Account_GBD");

        Vector<Vector<Object>> tableData = new Vector<>();

        for (Map.Entry<String, CheckingAccount> entry : Bank.accounts.entrySet()){

            Vector<Object> list = new Vector<>();
            CheckingAccount checkingAccount = entry.getValue();

            list.add(checkingAccount.name);
            list.add(checkingAccount.Uid);
            list.add(checkingAccount.balances.get("CNY"));
            list.add(checkingAccount.balances.get("USD"));
            list.add(checkingAccount.balances.get("GBD"));

            if (checkingAccount.hasSaving()){
                list.add(checkingAccount.getSavingAccount().balances.get("CNY"));
                list.add(checkingAccount.getSavingAccount().balances.get("USD"));
                list.add(checkingAccount.getSavingAccount().balances.get("GBD"));
            }else {
                list.add("NULL");
                list.add("NULL");
                list.add("NULL");
            }

            tableData.add(list);
        }

        close.addActionListener(this);
        daily.addActionListener(this);

        jPanel.add(daily);
        jPanel.add(close);

        jTable = new JTable(tableData,col);
        JScrollPane jScrollPane = new JScrollPane(jTable);

        this.setTitle("Manager");
        this.add(jScrollPane);
        this.add(jPanel, BorderLayout.SOUTH);
        this.setLocation(500,300);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==close){
            this.dispose();
            new SignFrame();
        }
        if (e.getSource()==daily){
            this.dispose();
            new DailyFrame();
        }


    }
}
