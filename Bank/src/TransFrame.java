import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Currency;

public class TransFrame extends JFrame implements ActionListener {

    String aid;
    String cur = "CNY";

    JLabel sendTo = new JLabel("Send to (Account ID)");
    JTextField textSend = new JTextField();
    JLabel amount = new JLabel("Amount");
    JTextField textAmount = new JTextField();
    JLabel currency = new JLabel("Currency");
    JComboBox curr = new JComboBox();
    JButton submit = new JButton("Submit");
    JButton cancel = new JButton("Cancel");

    TransFrame(String aid){
        this.aid = aid;
        this.setLayout(new GridLayout(4,2,2,1));

        curr.addItem("CNY");
        curr.addItem("USD");
        curr.addItem("GBD");

        this.add(sendTo);
        this.add(textSend);
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

        this.setTitle("Transaction");
        this.setLocation(500,300);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==submit){
            if (textAmount.getText().isEmpty()||textSend.getText().isEmpty()){
                JOptionPane.showMessageDialog(getParent(), "Amount and Account");
            }else {

                String Aid = textSend.getText();
                double am = Double.valueOf(textAmount.getText());

                String uid = aid.split("-")[0];
                String a = aid.split("-")[1];

                CheckingAccount checkingAccount = Bank.accounts.get(uid);

                int res;

                if (a.equals("0")){
                    res = checkingAccount.transaction(Aid,cur,am);
                }else {
                    SavingAccount savingAccount = checkingAccount.getSavingAccount();
                    res = savingAccount.transaction(Aid, cur, am);
                }

                if (res==-1){
                    JOptionPane.showMessageDialog(getParent(), "No account");
                    this.dispose();
                    new AccountFrame(checkingAccount);
                }else if (res==-2){
                    JOptionPane.showMessageDialog(getParent(), "You don't have enough money");
                    this.dispose();
                    new AccountFrame(checkingAccount);
                }else if (res==-3){
                    JOptionPane.showMessageDialog(getParent(), "Please input Account ID(xx-xx)");
                    this.dispose();
                    new TransFrame(aid);
                }


            }
        }else {
            String uid = aid.split("-")[0];
            CheckingAccount checkingAccount = Bank.accounts.get(uid);
            this.dispose();
            new AccountFrame(checkingAccount);
        }



    }
}
