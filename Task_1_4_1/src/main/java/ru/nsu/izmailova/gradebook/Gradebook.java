package ru.nsu.izmailova.gradebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class allows you to create a record book for student.
 */
public class Gradebook {

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

    private static class Semester {
        private final Map<String, Marks> subjects;

        public Semester() {
            this.subjects = new HashMap<>();
        }

        public void addMark(String subject, Marks mark) {
            subjects.put(subject, mark);
        }

        public Map<String, Marks> getSubjects() {
            return subjects;
        }
    }
    //private final List<Map<String, Marks>> semester; //красиво хранилище класс, без лист и мап
    private final List<Semester> semester;

    public Gradebook(String name, String surname, String patronymic, int group, int semesterNum) {
        this.NAME = name;
        this.SURNAME = surname;
        this.PATRONYMIC = patronymic;
        this.GROUP = group;
        this.semesterNum = semesterNum;
        qualifTask = Marks.Poor;
        semester = new ArrayList<>(SEMESTERS_AMOUNT);
        for (int i = 0; i < SEMESTERS_AMOUNT; i++) {
            semester.add(new Semester());
        }
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
        if (semesterNum >= 0 && semesterNum < semester.size()) {
            Map<String, Marks> currentSemester = semester.get(semesterNum).getSubjects();

            for (Marks num : currentSemester.values()) {
                iter = num.getMark();
                if (iter > 0) {
                    avg += iter;
                    cnt++;
                }
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
        Map<String, Marks> currentSemester = semester.get(semesterNum);

        if (currentSemester.containsValue(Marks.Satisfactory) || currentSemester.containsValue(Marks.Poor)) {
            return false;
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
        Map<String, Marks> currentSemester = semester.get(semesterNum);

        return !currentSemester.containsValue(Marks.Satisfactory) && !currentSemester.containsValue(Marks.Poor);
    }

    /**
     * Allows you to find out can you get the heightened scholarship in the chosen semester
     *
     * @return true if you can, false if not
     */
    public boolean heightenedScholarship() {
        Map<String, Marks> currentSemester = semester.get(semesterNum);

        int cnt = 0;
        for (Marks num : currentSemester.values()) {
            if (num == Marks.Good) {
                cnt++;
            }
        }

        return scholarship() && cnt < 3;
    }

    public void showRecordBook() {
        StringBuilder str = new StringBuilder("Full name: ");
        str.append(NAME);
        str.append(" ");
        str.append(SURNAME);
        str.append(" ");
        str.append(PATRONYMIC);
        System.out.println(str);

        str = new StringBuilder("Group: ");
        str.append(GROUP);
        System.out.println(str);
        for (int i = 1; i < SEMESTERS_AMOUNT; i++) {
            if (!semester.get(i).isEmpty()) {
                str = new StringBuilder("Semester: ");
                str.append(i);
                System.out.println(str);
                System.out.println("========================================================");
                semester.get(semesterNum).entrySet().stream().map(grade -> grade.getKey() + ": " + grade.getValue()).forEach(System.out::println);
                System.out.println("========================================================");
            }
        }
        str = new StringBuilder("Qualification task: ");
        str.append(qualifTask);
        System.out.println(str);
        System.out.println();
    }
}
