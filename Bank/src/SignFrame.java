import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignFrame extends JFrame implements ActionListener{

    JButton signIn = new JButton("Sign in");
    JButton signUp = new JButton("Sign up");
    JLabel name = new JLabel("User ID");
    JLabel password = new JLabel("Password");
    JTextField textName = new JTextField();
    JPasswordField textPassword = new JPasswordField();


    SignFrame(){

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3,2,2,1));

        name.setHorizontalAlignment(SwingConstants.RIGHT);
        password.setHorizontalAlignment(SwingConstants.RIGHT);

        jPanel.add(name);
        jPanel.add(textName);
        jPanel.add(password);
        jPanel.add(textPassword);
        jPanel.add(signUp);
        jPanel.add(signIn);

        signIn.addActionListener(this);
        signUp.addActionListener(this);

        this.add(jPanel,BorderLayout.CENTER);

        this.setTitle("Welcome");
        this.setLocation(500,300);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==signUp){
            this.dispose();
            new SignUpFrame();
        }
        if (e.getSource()==signIn){

            String n = textName.getText();
            String p = String.valueOf(textPassword.getPassword());



            if (n.isEmpty()||p.isEmpty()){
                JOptionPane.showMessageDialog(getParent(), "User ID and Password");
            }else {
                if (n.equals("admin") && p.equals("admin")){
                    this.dispose();
                    new ManagerFrame();
                }else if(!Bank.signIn(n,p)){
                    JOptionPane.showMessageDialog(getParent(), "Check your ID and Password");
                }else {
                    this.dispose();
                    CheckingAccount checkingAccount = Bank.accounts.get(n);
                    new AccountFrame(checkingAccount);
                }
            }

        }
    }






}
