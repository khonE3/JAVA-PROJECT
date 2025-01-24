import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class StudentGradeView extends JFrame {
    private final ArrayList<Student> students;

    public StudentGradeView() {
        students = new ArrayList<>();

        setTitle("Student Grading System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ส่วนฟอร์ม (Form Panel)
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // เพิ่ม Padding รอบฟอร์ม

        JLabel lblStudentID = new JLabel("Student ID:");
        JTextField txtStudentID = new JTextField();

        JLabel lblStudentName = new JLabel("Student Name:");
        JTextField txtStudentName = new JTextField();

        JLabel lblHomeworkScore = new JLabel("Homework Score (Max 30%):");
        JTextField txtHomeworkScore = new JTextField();

        JLabel lblTestScore = new JLabel("Test Score (Max 70%):");
        JTextField txtTestScore = new JTextField();

        formPanel.add(lblStudentID);
        formPanel.add(txtStudentID);
        formPanel.add(lblStudentName);
        formPanel.add(txtStudentName);
        formPanel.add(lblHomeworkScore);
        formPanel.add(txtHomeworkScore);
        formPanel.add(lblTestScore);
        formPanel.add(txtTestScore);

        // ส่วนปุ่ม (Button Panel)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnAddStudent = new JButton("Add Student");
        JButton btnUpdateStudent = new JButton("Update Student");
        JButton btnDeleteStudent = new JButton("Delete Student");
        JButton btnViewGrades = new JButton("View Grades");
        JButton btnBack = new JButton("Back");

        // ActionListener สำหรับปุ่ม Add Student
        btnAddStudent.addActionListener(e -> {
            try {
                String studentID = txtStudentID.getText().trim();
                String studentName = txtStudentName.getText().trim();
                double homeworkScore = Double.parseDouble(txtHomeworkScore.getText().trim());
                double testScore = Double.parseDouble(txtTestScore.getText().trim());

                // ตรวจสอบคะแนนไม่เกินค่าที่กำหนด
                if (homeworkScore > 30 || testScore > 70) {
                    JOptionPane.showMessageDialog(this, "Scores exceed maximum values!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // เพิ่มนักเรียนใน List
                students.add(new Student(studentID, studentName, homeworkScore, testScore));
                JOptionPane.showMessageDialog(this, "Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                // ล้างฟิลด์
                txtStudentID.setText("");
                txtStudentName.setText("");
                txtHomeworkScore.setText("");
                txtTestScore.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // ActionListener สำหรับปุ่ม Update Student
        btnUpdateStudent.addActionListener(e -> {
            String studentID = txtStudentID.getText().trim();

            if (studentID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the Student ID to update!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (Student student : students) {
                if (student.getStudentID().equals(studentID)) {
                    try {
                        String studentName = txtStudentName.getText().trim();
                        double homeworkScore = Double.parseDouble(txtHomeworkScore.getText().trim());
                        double testScore = Double.parseDouble(txtTestScore.getText().trim());

                        // ตรวจสอบคะแนนไม่เกินค่าที่กำหนด
                        if (homeworkScore > 30 || testScore > 70) {
                            JOptionPane.showMessageDialog(this, "Scores exceed maximum values!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // อัปเดตข้อมูลนักเรียน
                        student.setStudentName(studentName);
                        student.setHomeworkScore(homeworkScore);
                        student.setTestScore(testScore);

                        JOptionPane.showMessageDialog(this, "Student updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Please enter valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }

            JOptionPane.showMessageDialog(this, "Student ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
        });

        // ActionListener สำหรับปุ่ม Delete Student
        btnDeleteStudent.addActionListener(e -> {
            String studentID = txtStudentID.getText().trim();

            if (studentID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the Student ID to delete!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getStudentID().equals(studentID)) {
                    students.remove(i);
                    JOptionPane.showMessageDialog(this, "Student deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }

            JOptionPane.showMessageDialog(this, "Student ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
        });

        // ActionListener สำหรับปุ่ม View Grades
        btnViewGrades.addActionListener(e -> new StudentTableView(students));

        // ActionListener สำหรับปุ่ม Back
        btnBack.addActionListener(e -> {
            dispose(); // ปิดหน้าต่างปัจจุบัน
            Main.main(null); // กลับไปหน้าหลัก
        });

        buttonPanel.add(btnAddStudent);
        buttonPanel.add(btnUpdateStudent);
        buttonPanel.add(btnDeleteStudent);
        buttonPanel.add(btnViewGrades);
        buttonPanel.add(btnBack);

        // เพิ่มส่วนต่าง ๆ ลงใน JFrame
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentGradeView::new);
    }
}
