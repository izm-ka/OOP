package ru.nsu.izmailova.gradebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class allows you to create a record book for student.
 */
public class Gradebook {

    // Enumeration to represent different marks
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
    private final String NAME;
    private final String SURNAME;
    private final String PATRONYMIC;
    private final int GROUP;
    private final int SEMESTERS_AMOUNT = 9;
    private int semesterNum;

    // List to store semesters
    private final List<Semester> semester;

    /**
     * Constructor to initialize Gradebook.
     */
    public Gradebook(String name, String surname, String patronymic, int group, int semesterNum) {
        this.NAME = name;
        this.SURNAME = surname;
        this.PATRONYMIC = patronymic;
        this.GROUP = group;
        this.semesterNum = semesterNum;
        qualifTask = Marks.Poor;
        // initializing semesters with an empty list of subjects
        semester = new ArrayList<>(SEMESTERS_AMOUNT);
        for (int i = 0; i < SEMESTERS_AMOUNT; i++) {
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
     * Allows you to add a new Semester to gradebook
     *
     * @param newSemester - new semester, which you want to change or work with
     */
    public void setNewSemester(int newSemester) {
        semesterNum = newSemester;
    }

    /**
     * Allows you to add new subject with a mark
     *
     * @param subject - name of subject
     * @param mark - grade for this subject
     */
    public void addMark(String subject, Marks mark) {
        semester.get(semesterNum).addMark(subject, mark);
    }

    /**
     * Allows you to change the grade for the Qualification task
     * @param qualifTask - mark for the Qualification task
     */
    public void setQualifTask(Marks qualifTask) {
        this.qualifTask = qualifTask;
    }

    /**
     * allows you to count average score for the chosen semester
     *
     * @return average grade for the semester
     */
    public double average() {
        int iter;
        int cnt = 0;
        double avg = 0;
        Semester currentSemester = getCurrentSemester();
        for (Marks num : currentSemester.getSubjects().values()) {
            iter = num.getMark();
            if (iter > 0) {
                avg += iter;
                cnt++;
            }
        }
        return avg / cnt;
    }

    /**
     * allows you to find out is there is a possibility of getting the red diploma
     *
     * @return thue if you can get the red diploma, false if not
     */
    public boolean redDiploma() {
        Semester currentSemester = getCurrentSemester();

        for (Marks mark : currentSemester.getSubjects().values()) {
            if (mark == Marks.Satisfactory || mark == Marks.Poor) {
                return false;
            }
        }

        if (average() < 4.75) {
            return false;
        }

        return qualifTask == Marks.Excellent;
    }

    /**
     * allows you to find out if you can get the scholarship in the chosen semester
     *
     * @return true if you can, false if not
     */
    public boolean scholarship() {
        Semester currentSemester = getCurrentSemester();

        for (Marks mark : currentSemester.getSubjects().values()) {
            if (mark == Marks.Satisfactory || mark == Marks.Poor) {
                return false;
            }
        }
        return true;
    }

    /**
     * Allows you to find out can you get the heightened scholarship in the chosen semester
     *
     * @return true if you can, false if not
     */
    public boolean heightenedScholarship() {
        Semester currentSemester = getCurrentSemester();

        int cnt = 0;
        for (Marks mark : currentSemester.getSubjects().values()) {
            if (mark == Marks.Good) {
                cnt++;
            }
        }

        return scholarship() && cnt < 3;
    }

    /**
     * Displays the record book information on the console, including full name,
     * group, semesters, subjects, and corresponding marks.
     */
    public void showRecordBook() {
        System.out.println("Full name: " + NAME + " " + SURNAME + " " + PATRONYMIC);
        System.out.println("Group: " + GROUP);

        for (int i = 1; i < SEMESTERS_AMOUNT; i++) {
            Semester currentSemester = semester.get(i);
            if (!currentSemester.getSubjects().isEmpty()) {
                System.out.println("Semester: " + i);
                System.out.println("========================================================");
                currentSemester.getSubjects().forEach((subject, mark) -> {
                    System.out.println(subject + ": " + mark);
                });
                System.out.println("========================================================");
            }
        }
        System.out.println("Qualification task: " + qualifTask);
        System.out.println();
    }
}