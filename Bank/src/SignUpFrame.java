import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpFrame extends JFrame implements ActionListener {

    JLabel name = new JLabel("Name");
    JTextField textName = new JTextField();
    JLabel password = new JLabel("Password");
    JPasswordField textPassword = new JPasswordField();
    JLabel confirmPwd = new JLabel("Confirm Password");
    JPasswordField textConfirmPwd = new JPasswordField();
    JButton submit = new JButton("Submit");
    JButton cancel = new JButton("Cancel");

    SignUpFrame(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(4,2,2,1));

        name.setHorizontalAlignment(SwingConstants.RIGHT);
        password.setHorizontalAlignment(SwingConstants.RIGHT);

        submit.addActionListener(this);
        cancel.addActionListener(this);

        jPanel.add(name);
        jPanel.add(textName);
        jPanel.add(password);
        jPanel.add(textPassword);
        jPanel.add(confirmPwd);
        jPanel.add(textConfirmPwd);
        jPanel.add(cancel);
        jPanel.add(submit);

        this.add(jPanel,BorderLayout.CENTER);
        this.setTitle("Sign Up");
        this.setLocation(500,300);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==submit){
            String tempName = textName.getText();
            String tempPwd = String.valueOf(textPassword.getPassword());
            String tempConPwd = String.valueOf(textConfirmPwd.getPassword());

            if (tempName.isEmpty()||tempPwd.isEmpty()||tempConPwd.isEmpty()){
                JOptionPane.showMessageDialog(getParent(), "Name and Password");
            }else if(!tempPwd.equals(tempConPwd)){
                JOptionPane.showMessageDialog(getParent(), "Different Password");
            }else {
                CheckingAccount checkingAccount = Bank.addAccount(tempName,tempConPwd);
                this.dispose();
                new AccountFrame(checkingAccount);
            }
        }else {
            this.dispose();
            new SignFrame();
        }


    }
}
