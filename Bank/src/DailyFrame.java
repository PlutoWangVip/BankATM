import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Vector;

public class DailyFrame extends JFrame implements ActionListener {

    JTable jTable;
    JButton close = new JButton("Back");

    DailyFrame(){

        Vector<String> col = new Vector<>();

        col.add("Sender");
        col.add("Receiver");
        col.add("Currency");
        col.add("Amount");
        col.add("Date");

        Vector<Vector<Object>> tableData = new Vector<>();

        LocalDate localDate = LocalDate.now();

        for (int i=0;i<Bank.daily_trans.get(localDate).size();i++){
            Vector<Object> list = new Vector<>();
            list.add(Bank.daily_trans.get(localDate).get(i).sender);
            list.add(Bank.daily_trans.get(localDate).get(i).receiver);
            list.add(Bank.daily_trans.get(localDate).get(i).currency);
            list.add(Bank.daily_trans.get(localDate).get(i).money);
            list.add(Bank.daily_trans.get(localDate).get(i).date);
            tableData.add(list);
        }

        close.addActionListener(this);
        jTable = new JTable(tableData,col);
        JScrollPane jScrollPane = new JScrollPane(jTable);

        this.setTitle("Daily Transaction");
        this.add(jScrollPane);
        this.add(close, BorderLayout.SOUTH);
        this.setLocation(500,300);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        new ManagerFrame();

    }
}
