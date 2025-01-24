import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StudentTableView extends JFrame {
    private final JTable table;
    private final ArrayList<Student> students;
    private final DefaultTableModel tableModel;

    public StudentTableView(ArrayList<Student> students) {
        this.students = students;

        setTitle("Student Grades");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columns = {"Student ID", "Student Name", "Total Score", "Calculated Grade"};
        tableModel = new DefaultTableModel(columns, 0);
        populateTable();

        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Adjust column sizes
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getColumnModel().getColumn(0).setPreferredWidth(100); // Student ID
        table.getColumnModel().getColumn(1).setPreferredWidth(200); // Student Name
        table.getColumnModel().getColumn(2).setPreferredWidth(100); // Total Score
        table.getColumnModel().getColumn(3).setPreferredWidth(150); // Calculated Grade

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons for Update, Delete, and Back
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnBack = new JButton("Back");

        // Add ActionListeners for Update and Delete
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());

        btnBack.addActionListener(e -> {
            dispose(); // Close the current window
            Main.main(null); // Go back to the main view
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(btnUpdate);
        bottomPanel.add(btnDelete);
        bottomPanel.add(btnBack);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void populateTable() {
        tableModel.setRowCount(0); // Clear existing rows
        for (Student student : students) {
            tableModel.addRow(new Object[]{
                    student.getStudentID(),
                    student.getStudentName(),
                    student.calculateTotalScore(),
                    student.calculateGrade()
            });
        }
    }

    private void updateStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String studentID = (String) tableModel.getValueAt(selectedRow, 0);
        Student student = students.stream()
                .filter(s -> s.getStudentID().equals(studentID))
                .findFirst()
                .orElse(null);

        if (student != null) {
            JTextField txtStudentName = new JTextField(student.getStudentName());
            JTextField txtHomeworkScore = new JTextField(String.valueOf(student.getHomeworkScore()));
            JTextField txtTestScore = new JTextField(String.valueOf(student.getTestScore()));

            Object[] message = {
                    "Student Name:", txtStudentName,
                    "Homework Score:", txtHomeworkScore,
                    "Test Score:", txtTestScore
            };

            int option = JOptionPane.showConfirmDialog(this, message, "Update Student", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    student.setStudentName(txtStudentName.getText().trim());
                    student.setHomeworkScore(Double.parseDouble(txtHomeworkScore.getText().trim()));
                    student.setTestScore(Double.parseDouble(txtTestScore.getText().trim()));
                    populateTable(); // Refresh table
                    JOptionPane.showMessageDialog(this, "Student updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void deleteStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this student?", "Delete Student", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            String studentID = (String) tableModel.getValueAt(selectedRow, 0);
            students.removeIf(student -> student.getStudentID().equals(studentID));
            populateTable(); // Refresh table
            JOptionPane.showMessageDialog(this, "Student deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Sample data for testing
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("S001", "John Doe", 25.0, 65.0));
        students.add(new Student("S002", "Jane Smith", 20.0, 70.0));
        new StudentTableView(students);
    }
}