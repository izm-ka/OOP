package ru.nsu.izmailova.gradebook;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** A set of tests for the Gradebook class. */
public class GradebookTest {
    Gradebook gb = new Gradebook("Karina", "Izmailova", "Renatovna", 22213, 1);

    @BeforeEach
    public void myBook() {
        gb.addMark("PE", Gradebook.Marks.Excellent);
        gb.addMark("English", Gradebook.Marks.Excellent);
        gb.addMark("OOP", Gradebook.Marks.Excellent);
        gb.addMark("Introduction to AI", Gradebook.Marks.Excellent);
        gb.addMark("Computing Systems", Gradebook.Marks.Excellent);
        gb.addMark("Differential Equations", Gradebook.Marks.Good);
    }

    @Test
    public void redDiploma_noQualifTask() {
        assertFalse(gb.redDiploma());
    }

    @Test
    public void redDiploma_giveQualifTask() {
        gb.setQualifTask(Gradebook.Marks.Excellent);
        gb.showRecordBook();
        assertTrue(gb.redDiploma());
    }

    @Test
    public void redDiploma_badMark() {
        gb.setQualifTask(Gradebook.Marks.Excellent);
        gb.addMark("Operating Systems", Gradebook.Marks.Satisfactory);
        gb.showRecordBook();
        assertFalse(gb.redDiploma());
    }

    @Test
    public void scholarship_allExc() {
        assertTrue(gb.scholarship());
    }

    @Test
    public void scholarship_badMark() {
        gb.addMark("Operating Systems", Gradebook.Marks.Satisfactory);
        gb.showRecordBook();
        assertFalse(gb.scholarship());
    }

    @Test
    public void heightenedScholarship_allExc() {
        assertTrue(gb.heightenedScholarship());
    }

    @Test
    public void heightenedScholarship_theeGood() {
        gb.addMark("Operating Systems", Gradebook.Marks.Good);
        gb.addMark("PE 2.0", Gradebook.Marks.Good);
        gb.showRecordBook();
        assertFalse(gb.heightenedScholarship());
    }

    @Test
    public void average_test() {
        gb.addMark("Operating Systems", Gradebook.Marks.Satisfactory);
        gb.showRecordBook();
        assertEquals(32.0 / 7.0, gb.average());
    }

    @Test
    public void average_withPassed() {
        gb.addMark("Operating Systems", Gradebook.Marks.Passed);
        gb.showRecordBook();
        assertEquals(29.0 / 6.0, gb.average());
    }

    @Test
    public void heightenedScholarship_twoSemesters() {
        assertTrue(gb.heightenedScholarship());
        gb.setNewSemester(2);
        gb.addMark("PE 2.0", Gradebook.Marks.Poor);
        gb.addMark("English 2.0", Gradebook.Marks.Excellent);
        gb.addMark("OOP 2.0", Gradebook.Marks.Excellent);
        gb.addMark("Introduction to AI 2.0", Gradebook.Marks.Excellent);
        gb.addMark("Computing Systems 2.0", Gradebook.Marks.Excellent);
        gb.addMark("Differential Equations 2.0", Gradebook.Marks.Good);
        gb.showRecordBook();
        assertFalse(gb.heightenedScholarship());
    }
}