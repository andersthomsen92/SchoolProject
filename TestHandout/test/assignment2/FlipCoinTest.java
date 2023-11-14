package assignment2;

import assignment2.FlipCoin;
import org.junit.Test;
import static org.junit.Assert.*;

public class FlipCoinTest {

    //opgave 2
    @Test
    public void testFlipCoin() {
        FlipCoin coin = new FlipCoin();
        int zero = 0;
        int one = 0;
        int none = 0;
        for (int i = 0; i < 10000; i++) {
            int result = coin.flipCoin();
            switch (result) {
                case 0:
                    zero++;
                    break;
                case 1:
                    one++;
                    break;
                default:
                    none++;
                    break;
            }
        }
        assertTrue(zero > 10);
        assertTrue(one > 10);
        assertTrue(none == 0);
    }
}
