import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame("Student Grading System");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(400, 300);
            mainFrame.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10, 10, 10, 10);

            // Button
            JButton btnGradeCalc = new JButton("Grade Calculator");
            JButton btnStudentSystem = new JButton("Student Grade System");
            JButton btnStudentTable = new JButton("View Student Table");

            // Action Listener สำหรับแต่ละปุ่ม
            btnGradeCalc.addActionListener(e -> {
                mainFrame.dispose(); // ปิดหน้าหลัก
                new GradeCalculatorView(); // เปิดหน้าคำนวณเกรด
            });

            btnStudentSystem.addActionListener(e -> {
                mainFrame.dispose(); // ปิดหน้าหลัก
                new StudentGradeView(); // เปิดระบบจัดการนักเรียน
            });

            btnStudentTable.addActionListener(e -> {
                mainFrame.dispose(); // ปิดหน้าหลัก
                ArrayList<Student> students = getStudentList();
                new StudentTableView(students); // เปิดหน้าตารางนักเรียน
            });

            // เพิ่มปุ่มลงใน Layout
            gbc.gridx = 0;
            gbc.gridy = 0;
            mainFrame.add(btnGradeCalc, gbc);

            gbc.gridy = 1;
            mainFrame.add(btnStudentSystem, gbc);

            gbc.gridy = 2;
            mainFrame.add(btnStudentTable, gbc);

            // ทำให้หน้าต่างปรับขนาดได้
            mainFrame.setMinimumSize(new Dimension(300, 200));
            mainFrame.setVisible(true);
        });
    }

    // ฟังก์ชันสำหรับดึงข้อมูลนักเรียน
    private static ArrayList<Student> getStudentList() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("S001", "John Doe", 85, 90));
        students.add(new Student("S002", "Jane Smith", 88, 92));
        students.add(new Student("S003", "Alice Brown", 75, 80));
        students.add(new Student("S004", "Bob Johnson", 95, 98));
        students.add(new Student("S005", "Charlie White", 70, 72));
        return students;
    }
}
