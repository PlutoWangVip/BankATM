import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class DepositFrame extends JFrame implements ActionListener {

    String aid;
    String cur = "CNY";

    JLabel amount = new JLabel("Amount");
    JTextField textAmount = new JTextField();
    JLabel currency = new JLabel("Currency");
    JComboBox curr = new JComboBox();
    JButton submit = new JButton("Submit");
    JButton cancel = new JButton("Cancel");

    DepositFrame(String aid){
        this.aid = aid;
        this.setLayout(new GridLayout(3,2,2,1));

        curr.addItem("CNY");
        curr.addItem("USD");
        curr.addItem("GBD");

        this.add(amount);
        this.add(textAmount);
        this.add(currency);
        this.add(curr);
        this.add(cancel);
        this.add(submit);

        submit.addActionListener(this);
        cancel.addActionListener(this);

        curr.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                cur = (String) curr.getSelectedItem();
            }
        });

        this.setTitle("Deposit");
        this.setLocation(500,300);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submit){
            if (textAmount.getText().isEmpty()){
                JOptionPane.showMessageDialog(getParent(), "Amount");
            }else {

                double am = Double.valueOf(textAmount.getText());
                String uid = aid.split("-")[0];
                String a = aid.split("-")[1];

                CheckingAccount checkingAccount = Bank.accounts.get(uid);

                if (a.equals("0")){
                    checkingAccount.deposit(cur,am);
                }else {
                    SavingAccount savingAccount = checkingAccount.getSavingAccount();
                    savingAccount.deposit(cur,am);
                }

                this.dispose();
                new AccountFrame(checkingAccount);
            }
        }else {
            String uid = aid.split("-")[0];
            CheckingAccount checkingAccount = Bank.accounts.get(uid);
            this.dispose();
            new AccountFrame(checkingAccount);
        }

    }
}
