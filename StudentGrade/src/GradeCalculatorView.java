import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GradeCalculatorView extends JFrame {
    public GradeCalculatorView() {
        setTitle("Grade Average Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // เพิ่มช่องว่างระหว่างส่วนต่าง ๆ

        // ส่วนแสดงคำแนะนำ
//        JLabel lblInstructions = new JLabel("Enter your grades (0-100):");
//        lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);

        // ส่วนกลาง (GridLayout)
        JPanel centerPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        centerPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // เพิ่ม Padding

        JLabel lblPrelim = new JLabel("Prelim:");
        JTextField txtPrelim = new JTextField();

        JLabel lblMidterm = new JLabel("Midterm:");
        JTextField txtMidterm = new JTextField();

        JLabel lblFinal = new JLabel("Final:");
        JTextField txtFinal = new JTextField();

        JLabel lblAverage = new JLabel("Average:");
        JTextField txtAverage = new JTextField();
        txtAverage.setEditable(false);

        JLabel lblStatus = new JLabel("Status:");
        JTextField txtStatus = new JTextField();
        txtStatus.setEditable(false);

        centerPanel.add(lblPrelim); centerPanel.add(txtPrelim);
        centerPanel.add(lblMidterm); centerPanel.add(txtMidterm);
        centerPanel.add(lblFinal); centerPanel.add(txtFinal);
        centerPanel.add(lblAverage); centerPanel.add(txtAverage);
        centerPanel.add(lblStatus); centerPanel.add(txtStatus);

        // Button Calculator
        JButton btnCalculate = new JButton("Calculate");
        btnCalculate.addActionListener(e -> {
            try {
                // รับค่าเกรดจากผู้ใช้
                double prelim = Double.parseDouble(txtPrelim.getText());
                double midterm = Double.parseDouble(txtMidterm.getText());
                double finalExam = Double.parseDouble(txtFinal.getText());

                // ตรวจสอบคะแนนว่าอยู่ในช่วง 0-100
                if (prelim < 0 || prelim > 100 || midterm < 0 || midterm > 100 || finalExam < 0 || finalExam > 100) {
                    JOptionPane.showMessageDialog(this, "Grades must be between 0 and 100!", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // คำนวณค่าเฉลี่ย
                double average = (prelim + midterm + finalExam) / 3;
                txtAverage.setText(String.format("%.2f", average));

                // แสดงสถานะ (ผ่านหรือไม่ผ่าน)
                String status = average >= 60 ? "Passed" : "Failed";
                txtStatus.setText(status);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Button back
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            dispose(); // ปิดหน้าปัจจุบัน
            Main.main(null); // กลับไปหน้าหลัก
        });

        // ส่วนล่าง (ปุ่ม)
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bottomPanel.add(btnCalculate);
        bottomPanel.add(btnBack);

        // เพิ่มส่วนต่าง ๆ ลงใน JFrame
//        add(lblInstructions, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
