package Grade;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectGradeTest {

    private static final double DELTA = 0.001;

    @Test
    public void getGoodGradeIfCorrect() {

        double grade = ProjectGrade.CalculateGrade(true, true, 7.5);

        Assert.assertEquals(7.5, grade, DELTA);
    }

    @Test
    public void getBadGradeIfNotCompiles() {

        double grade = ProjectGrade.CalculateGrade(true, false, 7.5);

        Assert.assertEquals(1, grade, DELTA);
    }

    @Test
    public void getBadGradeIfNotUsedGit() {

        double grade = ProjectGrade.CalculateGrade(false, true, 7.5);

        Assert.assertEquals(1, grade, DELTA);
    }

}