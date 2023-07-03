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

    public static String[] getKeys() {
        int numKeys = 10;
        String[] keys = new String[numKeys];
        keys[0] = "ali";
        keys[1] = "sina";
        keys[2] = "hassan";
        keys[3] = "taqi";
        keys[4] = "fgh";
        keys[5] = "dert";
        keys[6] = "xqwed";
        keys[7] = "gtuk";
        keys[8] = "lohq";
        keys[9] = "azsyu";

        return keys;
    }

    public static int[] getData(int numData, int maxLimit) {
        int[] datas = new int[numData];

        for (int i = 0; i < numData; i++) {
            int data = rand.nextInt(maxLimit);
            datas[i] = data;
        }

        return datas;
    }

    public static int getaMaxChunkSize(Page[] pages) {
        int max = -1;

        for (int i = 0; i < pages.length; i++) {
            if (pages[i].chunkSize > max) {
                max = pages[i].chunkSize;
            }
        }

        return max;
    }
}
