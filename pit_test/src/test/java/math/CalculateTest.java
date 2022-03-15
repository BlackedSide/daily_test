package math;

import org.junit.Test;

/**
 * AUTHOR fanny
 * DATE 2022/3/7 11:04 AM
 * DESCRIPTION
 */
public class CalculateTest {

    @Test
    public void test() {
        System.out.println(score("6742", "11910"));
    }

    private int score(String usedStr, String totalStr) {
        int used = (int) (Double.parseDouble(usedStr) / Double.parseDouble(totalStr) * 100);
        int score = 0;
        if (used >= 0 && used <= 60) {
            score = (int) (100 - 1.5 * (60 - used));
        } else if (used > 60 && used <= 80) {
            score = 100;
        } else if (used > 80 && used <= 100) {
            score = 100 - 5 * (used - 80);
        }
        return score;
    }
}
