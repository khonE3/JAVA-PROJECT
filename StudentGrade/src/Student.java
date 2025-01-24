public class Student extends AbstractStudent implements GradeCalculatorInterface {
    private double homeworkScore;
    private double testScore;

    public Student(String studentID, String studentName, double homeworkScore, double testScore) {
        super(studentID, studentName); // เรียกใช้งาน Constructor ของ AbstractStudent
        this.homeworkScore = homeworkScore;
        this.testScore = testScore;
    }

    @Override
    public double calculateGrade() {
        return (homeworkScore * 0.3) + (testScore * 0.7); // คำนวณเกรดจากคะแนนการบ้าน 30% และคะแนนการทดสอบ 70%
    }

    @Override
    public double calculateTotalScore() {
        return homeworkScore + testScore;
    }

    // Getter methods
    public double getHomeworkScore() {
        return homeworkScore;
    }

    public double getTestScore() {
        return testScore;
    }

    // Setter methods for updating values
    public void setHomeworkScore(double homeworkScore) {
        this.homeworkScore = homeworkScore;
    }

    public void setTestScore(double testScore) {
        this.testScore = testScore;
    }
}