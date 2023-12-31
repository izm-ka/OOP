package ru.nsu.izmailova.gradebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class allows you to create a record book for student.
 */
public class Gradebook {
    /**
     * Enumeration representing different marks.
     * Each mark has an associated numeric value.
     */
    public enum Marks {
        Excellent(5),
        Good(4),
        Satisfactory(3),
        Poor(2),
        Passed(0);

        private final int gradeNum;

        Marks(int mark) {
            gradeNum = mark;
        }

        public int getMark() {
            return gradeNum;
        }
    }

    private Marks qualifTask;
    private final String name;
    private final String surname;
    private final String patronymic;
    private final int group;
    private final int semestersAmount = 9;
    private int semesterNum;

    // List to store semesters
    private final List<Semester> semester;

    /**
     * Constructor to initialize Gradebook.
     */
    public Gradebook(String name, String surname, String patronymic, int group, int semesterNum) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.group = group;
        this.semesterNum = semesterNum;
        qualifTask = Marks.Poor;
        // initializing semesters with an empty list of subjects
        semester = new ArrayList<>(semestersAmount);
        for (int i = 0; i < semestersAmount; i++) {
            semester.add(new Semester());
        }
    }

    /**
     * Private inner class representing a Semester.
     */
    private static class Semester {
        private final Map<String, Marks> subjects;

        /**
         * Constructor to initialize the semester with an empty list of subjects.
         */
        public Semester() {
            this.subjects = new HashMap<>();
        }

        /**
         * Method to add a subject and its mark to the semester.
         *
         *  @param subject The name of the subject.
         *  @param mark The grade for this subject.
         */
        public void addMark(String subject, Marks mark) {
            subjects.put(subject, mark);
        }

        /**
         * Method to get the subjects of the semester.
         *
         * @return A map containing subjects and their corresponding marks.
         */
        public Map<String, Marks> getSubjects() {
            return subjects;
        }
    }

    /**
     * Method to get the current semester.
     *
     * @return The current semester.
     * @throws IndexOutOfBoundsException If the semester number is out of bounds.
     */
    private Semester getCurrentSemester() throws IndexOutOfBoundsException {
        return semester.get(semesterNum);
    }


    /**
     * Allows you to add a new Semester to gradebook.
     *
     * @param newSemester - new semester, which you want to change or work with
     */
    public void setNewSemester(int newSemester) {
        semesterNum = newSemester;
    }

    /**
     * Allows you to add new subject with a mark.
     *
     * @param subject - name of subject
     * @param mark - grade for this subject
     */
    public void addMark(String subject, Marks mark) {
        semester.get(semesterNum).addMark(subject, mark);
    }

    /**
     * Allows you to change the grade for the Qualification task.
     *
     * @param qualifTask - mark for the Qualification task
     */
    public void setQualifTask(Marks qualifTask) {
        this.qualifTask = qualifTask;
    }

    /**
     * Allows you to count average score for the chosen semester.
     *
     * @return average grade for the semester
     */
    public double average() {
        Semester currentSemester = getCurrentSemester();

        return currentSemester.getSubjects().values().stream()
                .mapToDouble(Marks::getMark)
                .filter(mark -> mark > 0)
                .average()
                .orElse(0.0);  // Если поток пуст, возвращаем 0.0
    }

    /**
     * Allows you to find out is there is a possibility of getting the red diploma.
     *
     * @return thue if you can get the red diploma, false if not
     */
    public boolean redDiploma() {
        Semester currentSemester = getCurrentSemester();

        return (currentSemester.getSubjects().values().stream()
                .noneMatch(mark -> mark == Marks.Satisfactory || mark == Marks.Poor))
                && (average() >= 4.75) && (qualifTask == Marks.Excellent);
    }

    /**
     * Allows you to find out if you can get the scholarship in the chosen semester.
     *
     * @return true if you can, false if not
     */
    public boolean scholarship() {
        Semester currentSemester = getCurrentSemester();

        return currentSemester.getSubjects().values().stream()
                .noneMatch(mark -> mark == Marks.Satisfactory || mark == Marks.Poor);
    }

    /**
     * Allows you to find out can you get the heightened scholarship in the chosen semester.
     *
     * @return true if you can, false if not
     */
    public boolean heightenedScholarship() {
        Semester currentSemester = getCurrentSemester();

        long cnt = currentSemester.getSubjects().values().stream()
                .filter(mark -> mark == Marks.Good).count();

        return scholarship() && cnt < 3;
    }

    /**
     * Displays the record book information on the console, including full name,
     * group, semesters, subjects, and corresponding marks.
     */
    public void showRecordBook() {
        System.out.println("Full name: " + name + " " + surname + " " + patronymic);
        System.out.println("Group: " + group);

        for (int i = 1; i < semestersAmount; i++) {
            Semester currentSemester = semester.get(i);
            if (!currentSemester.getSubjects().isEmpty()) {
                System.out.println("Semester: " + i);
                System.out.println("========================================================");
                currentSemester.getSubjects().forEach((subject, mark) -> {
                    System.out.println(subject + ": " + mark);
                });
                double semesterAverage = average();
                String averageResult = String.format("%.2f", semesterAverage);
                System.out.println("Average Grade for Semester: " + averageResult);
                System.out.println("========================================================");
            }
        }
        System.out.println("Qualification task: " + qualifTask);
        System.out.println();
    }
}