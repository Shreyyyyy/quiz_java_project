import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class signup {

    public void signUpView() {
        JFrame frame = new JFrame();
        frame.setSize(450, 500);  // Increased height for a better layout
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.BLACK);  // Set background to black
        frame.setLocationRelativeTo(null);

        JLabel heading = new JLabel("Create User");
        heading.setBounds(0, 50, 450, 50);
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 40));  // Changed font to Arial
        heading.setForeground(Color.WHITE);  // Set text color to white
        frame.add(heading);

        // Set labels and fields to white text color
        JLabel fName = new JLabel("Name : ");
        fName.setBounds(50, 120, 150, 50);
        fName.setForeground(Color.WHITE);
        frame.add(fName);

        JTextField fNameField = new JTextField();
        fNameField.setBounds(50, 160, 350, 30);
        frame.add(fNameField);

        JLabel uName = new JLabel("Username : ");
        uName.setBounds(50, 190, 150, 50);
        uName.setForeground(Color.WHITE);
        frame.add(uName);

        JTextField uNameField = new JTextField();
        uNameField.setBounds(50, 230, 350, 30);
        frame.add(uNameField);

        JLabel uPass = new JLabel("Password : ");
        uPass.setBounds(50, 260, 150, 50);
        uPass.setForeground(Color.WHITE);
        frame.add(uPass);

        JPasswordField uPassField = new JPasswordField();
        uPassField.setBounds(50, 300, 350, 30);  // Increased width for better visibility
        frame.add(uPassField);

        JLabel uPass2 = new JLabel("Confirm Password : ");
        uPass2.setBounds(50, 330, 150, 50);
        uPass2.setForeground(Color.WHITE);
        frame.add(uPass2);

        JPasswordField uPassField2 = new JPasswordField();
        uPassField2.setBounds(50, 370, 350, 30);  // Increased width for better visibility
        frame.add(uPassField2);

        JButton submit = new JButton("SUBMIT");
        submit.setBounds(175, 420, 100, 40);
        frame.add(submit);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fname = fNameField.getText();
				String uname = uNameField.getText();
				String pass1 = new String(uPassField.getPassword());
				String pass2 = new String(uPassField2.getPassword());
				if(fname.isEmpty() || uname.isEmpty() || pass1.isEmpty()|| pass2.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please Enter All Information.", "Warning Message", JOptionPane.WARNING_MESSAGE);
				}
				else {
					if(pass1.equals(pass2)) {
						try {
							SQLoperations manage = new SQLoperations();
							manage.newUser(fname, uname, pass1);
							fNameField.setText("");
							uNameField.setText("");
							uPassField.setText("");
							uPassField2.setText("");
							JOptionPane.showMessageDialog(frame, "Success.", "User Created", JOptionPane.PLAIN_MESSAGE);
							frame.dispose();
							
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(frame, "Please Try Again.", "Warning Message", JOptionPane.WARNING_MESSAGE);
						}
						
					}
					else {
						JOptionPane.showMessageDialog(frame, "Password Mismatch", "Warning Message", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		frame.setVisible(true);
    }

    public static void main(String[] args) {
        new signup().signUpView();
    }
}
