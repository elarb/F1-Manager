package Grade;

public class ProjectGrade {

    public static double CalculateGrade(boolean usedGit, boolean compiles, double grade)
    {
        if(!usedGit || !compiles)
        {
            return 1.0;
        }

        return grade;
    }

    public static boolean TAisHappy(boolean bringCake)
    {
        return bringCake;
    }

}
