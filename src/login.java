import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class login {

    int id;

    public void loginView() throws SQLException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SQLoperations manage = new SQLoperations();
        JFrame frame = new JFrame();
        frame.setSize(700, 550);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0, 0, 0)); // Black background
        frame.setLocationRelativeTo(null);

        JLabel heading = new JLabel("Quiz Management System");
        heading.setBounds(0, 50, 700, 50);
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 40)); // Modern font
        heading.setForeground(new Color(255, 255, 255)); // White text
        frame.add(heading);

        JLabel uname = new JLabel("Username: ");
        uname.setBounds(175, 130, 150, 50);
        uname.setForeground(new Color(255, 255, 255));
        frame.add(uname);

        JTextField name = new JTextField();
        name.setBounds(175, 170, 350, 30);
        frame.add(name);

        JLabel upass = new JLabel("Password: ");
        upass.setBounds(175, 200, 150, 50);
        upass.setForeground(new Color(255, 255, 255));
        frame.add(upass);

        JPasswordField pass = new JPasswordField();
        pass.setBounds(175, 240, 350, 30);
        frame.add(pass);

        JButton login = new JButton("LOGIN");
        login.setBounds(225, 300, 100, 40);
        frame.add(login);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = name.getText();
                String password = new String(pass.getPassword());
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please Enter All Information.", "Warning Message",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        SQLoperations manage = new SQLoperations();
                        id = manage.authUser(username, password);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    if (id == -1) {
                        JOptionPane.showMessageDialog(frame, "User Does Not Exist.", "Warning Message",
                                JOptionPane.WARNING_MESSAGE);
                    } else if (id == 0) {
                        JOptionPane.showMessageDialog(frame, "Incorrect Password, please try again.",
                                "Warning Message", JOptionPane.WARNING_MESSAGE);
                    } else {
                        mainpage mainPage = new mainpage();
                        try {
                            mainPage.mainPageView(id);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        frame.dispose();
                    }
                }
            }
        });

        JButton signUp = new JButton("SIGNUP");
        signUp.setBounds(375, 300, 100, 40);
        frame.add(signUp);
        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signup signup = new signup();
                signup.signUpView();
            }
        });

        JButton attend = new JButton("Complete Quiz (Guest)");
        attend.setBounds(225, 350, 250, 40);
        frame.add(attend);
        attend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String surveyCode = JOptionPane.showInputDialog("Please Enter the Unique Survey Code: ");
                try {
                    if (!surveyCode.isEmpty() && surveyCode.length() == 5) {
                        if (manage.check(surveyCode)) {
                            guest guest = new guest();
                            guest.guestView(surveyCode);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Incorrect Survey Code, please try again.",
                                    "Warning Message", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } catch (Exception e2) {

                }
            }
        });

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the frame is closed
    }

    public static void main(String[] args) throws SQLException {
        login login = new login();
        login.loginView();
    }
}
