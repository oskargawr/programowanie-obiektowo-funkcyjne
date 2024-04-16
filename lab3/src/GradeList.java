import java.util.ArrayList;

public class GradeList {
    private ArrayList<Double> grades = new ArrayList<>();

    public void addGrade(double grade) {
        grades.add(grade);
    }

    public double getAverage() {
        double sum = 0.0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public double getLowestGrade() {
        double lowest = grades.get(0);
        for (double grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }

    public double getHighestGrade() {
        double highest = grades.get(0);
        for (double grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }

    public boolean isEmpty() {
        return grades.isEmpty();
    }
}
