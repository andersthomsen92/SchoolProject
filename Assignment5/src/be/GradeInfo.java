package be;

public class GradeInfo {

    private int grade;
    private String subject;

    public GradeInfo(int grade, String subject) {
        this.grade = grade;
        this.subject = subject;
    }

    public int getGrade() {
        return grade;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return  "grade: " + grade +
                ", subject: " + subject;

    }
}
