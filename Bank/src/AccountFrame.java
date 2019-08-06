import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountFrame extends JFrame implements ActionListener {

    CheckingAccount checkingAccount;
    CardLayout cardLayout = new CardLayout();
    JPanel jPanelTop = new JPanel();
    JPanel jPanelContent = new JPanel();



    JLabel uid;
    JLabel name;
    JMenuBar jMenuBar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    JMenuItem check = new JMenuItem("Transaction");
    JMenuItem logout = new JMenuItem("Logout");
    JButton button = new JButton("Saving Account");



    JButton createSaving = new JButton("Create Saving Account");

    JPanel jPanelChecking = new JPanel();
    JPanel jPanelSaving = new JPanel();



    AccountFrame(CheckingAccount checkingAccount){

        this.checkingAccount = checkingAccount;
        initialTop(jPanelTop);
        initialContent(jPanelContent);

        this.add(jPanelTop);
        this.add(jPanelContent, BorderLayout.SOUTH);

        this.setTitle("Account");
        this.setLocation(500,300);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void initialTop(JPanel jPanelTop){

        jPanelTop.setLayout(new GridLayout(2,2,2,1));

        uid = new JLabel("User ID: "+ checkingAccount.Uid);
        name = new JLabel("Name: " + checkingAccount.name);

        menu.add(check);
        menu.add(logout);
        button.addActionListener(this);
        createSaving.addActionListener(this);
        logout.addActionListener(this);
        check.addActionListener(this);

        jMenuBar.add(menu);

        jPanelTop.add(uid);
        jPanelTop.add(jMenuBar);
        jPanelTop.add(name);
        jPanelTop.add(button);


    }

    private void initialContent(JPanel jPanelContent){

        initialChecking();
        initialSaving();

        jPanelContent.setLayout(cardLayout);
        jPanelContent.add(jPanelChecking);
        jPanelContent.add(jPanelSaving);


    }

    private void initialChecking(){

        jPanelChecking.setLayout(new GridLayout(1,2,2,1));

        JPanel jPanelLeft = new JPanel();
        jPanelLeft.setLayout(new GridLayout(4,1,2,1));
        JPanel jPanelRight = new JPanel();
        jPanelRight.setLayout(new GridLayout(1,1,2,1));

        jPanelLeft.add(new JLabel("Balance of Checking Account: " + checkingAccount.Aid));
        jPanelLeft.add(new JLabel("CNY: " + checkingAccount.balances.get("CNY")));
        jPanelLeft.add(new JLabel("USD: " + checkingAccount.balances.get("USD")));
        jPanelLeft.add(new JLabel("GBD: " + checkingAccount.balances.get("GBD")));


        OptionPanel panel = new OptionPanel(checkingAccount.Aid);
        jPanelRight.add(panel);


        jPanelChecking.add(jPanelLeft);
        jPanelChecking.add(jPanelRight);

    }

    private void initialSaving(){

        if (checkingAccount.hasSaving()){
            jPanelSaving.setLayout(new GridLayout(1,2,2,1));

            JPanel jPanelLeft = new JPanel();
            jPanelLeft.setLayout(new GridLayout(4,1,2,1));
            JPanel jPanelRight = new JPanel();
            jPanelRight.setLayout(new GridLayout(1,1,2,1));

            jPanelLeft.add(new JLabel("Balance of Saving Account: " + checkingAccount.getSavingAccount().Aid));
            jPanelLeft.add(new JLabel("CNY: " + checkingAccount.getSavingAccount().balances.get("CNY")));
            jPanelLeft.add(new JLabel("USD: " + checkingAccount.getSavingAccount().balances.get("USD")));
            jPanelLeft.add(new JLabel("GBD: " + checkingAccount.getSavingAccount().balances.get("GBD")));

            OptionPanel panel = new OptionPanel(checkingAccount.getSavingAccount().Aid);
            jPanelRight.add(panel);

            jPanelSaving.add(jPanelLeft);
            jPanelSaving.add(jPanelRight);
        }else {
            jPanelSaving.add(createSaving);
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==button){
            cardLayout.next(jPanelContent);
            if (button.getText().equals("Checking Account")){
                button.setText("Saving Account");
            }else {
                button.setText("Checking Account");
            }
        }
        if (e.getSource()==createSaving){
            checkingAccount.createSaving();
            this.dispose();
            new AccountFrame(checkingAccount);
        }

        if (e.getSource()==logout){
            this.dispose();
            new SignFrame();
        }

        if (e.getSource()==check){
            this.dispose();
            new RecordFrame(checkingAccount);
        }





    }
}
