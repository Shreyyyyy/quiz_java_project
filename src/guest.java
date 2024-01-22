import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class guest {

    SQLoperations manage;
    int[] opt;
    int k;

    public void guestView(String surveyCode) throws SQLException {

        manage = new SQLoperations();
        ResultSet rst = manage.getQuestions(surveyCode);
        opt = new int[50];

        Font options = new Font("Arial", Font.BOLD, 18);  // Changed font to Arial

        JFrame frame = new JFrame();
        frame.setSize(1000, 800);  // Increased size for a bigger appearance
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        JLabel start = new JLabel("ATTENDING THE SURVEY");
        start.setBounds(0, 50, 1000, 50);
        start.setHorizontalAlignment(SwingConstants.CENTER);
        start.setFont(new Font("Arial", Font.BOLD, 40));  // Changed font to Arial
        start.setForeground(java.awt.Color.WHITE);  // Set text color to white
        frame.add(start);

        JLabel ques = new JLabel("Question Here!!!");
        ques.setBounds(150, 200, 700, 40);  // Increased width for better visibility
        ques.setFont(new Font("Arial", Font.BOLD, 24));  // Changed font to Arial
        ques.setForeground(java.awt.Color.WHITE);  // Set text color to white
        frame.add(ques);

        JRadioButton op1 = new JRadioButton("Option1");
        JRadioButton op2 = new JRadioButton("Option2");
        JRadioButton op3 = new JRadioButton("Option3");
        JRadioButton op4 = new JRadioButton("Option4");

        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(op1);
        bgroup.add(op2);
        bgroup.add(op3);
        bgroup.add(op4);

        op1.setBounds(150, 300, 700, 30);  // Increased width for better visibility
        op2.setBounds(150, 350, 700, 30);  // Increased width for better visibility
        op3.setBounds(150, 400, 700, 30);  // Increased width for better visibility
        op4.setBounds(150, 450, 700, 30);  // Increased width for better visibility

        op1.setFont(options);
        op2.setFont(options);
        op3.setFont(options);
        op4.setFont(options);

        if (rst.next()) {
            ques.setText(rst.getString("question"));
            op1.setText(rst.getString("option1"));
            op2.setText(rst.getString("option2"));
            op3.setText(rst.getString("option3"));
            op4.setText(rst.getString("option4"));
        }

        frame.add(op1);
        frame.add(op2);
        frame.add(op3);
        frame.add(op4);
        k = 0;

        JButton nextButton = new JButton("NEXT");
        nextButton.setBounds(300, 550, 400, 50);  // Adjusted size for better visibility
        frame.add(nextButton);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x;
                if (op1.isSelected()) {
                    x = 1;
                } else if (op2.isSelected()) {
                    x = 2;
                } else if (op3.isSelected()) {
                    x = 3;
                } else if (op4.isSelected()) {
                    x = 4;
                } else
                    x = 0;

                if (x != 0) {
                    opt[k] = x;
                    k++;
                    try {
                        if (rst.next()) {
                            ques.setText(rst.getString("question"));
                            op1.setText(rst.getString("option1"));
                            op2.setText(rst.getString("option2"));
                            op3.setText(rst.getString("option3"));
                            op4.setText(rst.getString("option4"));
                        } else {
                            for (int j = 0; j < k; j++) {
                                manage.answerUpdt(surveyCode, j + 1, opt[j]);
                            }
                            JOptionPane.showMessageDialog(frame, "Survey Completed. Thank You.", "Congratulations", JOptionPane.PLAIN_MESSAGE);
                            frame.dispose();
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Select an option!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                bgroup.clearSelection();
            }
        });

        // Set button style to black with white text
        nextButton.setBackground(java.awt.Color.BLACK);
        nextButton.setForeground(java.awt.Color.RED);
        nextButton.setFocusPainted(false);

        frame.getContentPane().setBackground(java.awt.Color.BLACK);  // Set background to black
        frame.setVisible(true);
    }
}
