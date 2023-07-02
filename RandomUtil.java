import java.util.Random;

public class RandomUtil {
    private static Random rand = new Random();
    
    static int[] generateIJ(int limit) {
        int i = rand.nextInt(limit);
        int j = rand.nextInt(limit);
    
        int[] output = new int[2];
        output[0] = i;
        output[1] = j;

        return output;
    }
}
