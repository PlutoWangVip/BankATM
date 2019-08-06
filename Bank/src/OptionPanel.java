import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionPanel extends JPanel implements ActionListener {

    JButton withdraw = new JButton("Withdraw");
    JButton deposit = new JButton("Deposit");
    JButton trans = new JButton("Transaction");
    JButton loan = new JButton("Loan");

    String aid;

    OptionPanel(String aid){
        this.aid = aid;
        this.setLayout(new GridLayout(2,2,2,1));

        withdraw.addActionListener(this);
        deposit.addActionListener(this);
        trans.addActionListener(this);
        loan.addActionListener(this);


        this.add(withdraw);
        this.add(deposit);
        this.add(trans);
        this.add(loan);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==withdraw){

            ((JFrame)this.getParent().getParent().getParent().getParent().getParent().getParent().getParent()).dispose();
            new WithdrawFrame(aid);
        }
        if (e.getSource()==deposit){
            ((JFrame)this.getParent().getParent().getParent().getParent().getParent().getParent().getParent()).dispose();
            new DepositFrame(aid);
        }
        if (e.getSource()==trans){
            ((JFrame)this.getParent().getParent().getParent().getParent().getParent().getParent().getParent()).dispose();
            new TransFrame(aid);
        }
        if (e.getSource()==loan){
            ((JFrame)this.getParent().getParent().getParent().getParent().getParent().getParent().getParent()).dispose();
            new LoanFrame(aid);
        }

    }
}
