




class Page {
    static final int MG = 1000000;
    private int[][] pageArray;
    private int chunkSize;
    private int filledChunkCounter;

    public Page(int chunkSize) {
        this.chunkSize = chunkSize;
        this.createPage();
        this.filledChunkCounter = 0;
    }

    public int[] findEmptyChunk() {
        while (true) {
            int[] ij = RandomUtil.generateIJ(this.pageArray.length);

            if (this.isChunkEmpty(ij[0], ij[1])) {
                return ij;
            }
        }        
    }

    public boolean isChunkEmpty(int i, int j) {
        if (this.pageArray[i][j] == 0) {
            return true;
        }
        return false;
    }

    public boolean canBePlaced() {
        if (this.filledChunkCounter >= Math.pow(this.pageArray.length, 2)) {
            return false;
        } 
        return true;
    } 

    public void createPage() {
        // obtain dimensions of the pageArray
        double numChunksDouble = MG / this.chunkSize;
        int numRowsCols = (int) Math.sqrt(numChunksDouble);

        this.pageArray = new int[numRowsCols][numRowsCols];

    }

    public int[] addData(int data, int size) {

        int[] output = new int[2];
        output[0] = -1;            
        output[1] = -1; 

        if (size > this.chunkSize) {
            return output;
        }

        if (this.canBePlaced()) {
            int[] ij = this.findEmptyChunk();
            int i = ij[0];            
            int j = ij[1];

            this.pageArray[i][j] = data;

            return ij;
        } 

        return output;
    }

    public int[][] getArray() {
        return this.pageArray;
    }



}


public class Main {
    public static void main(String[] args) {
        int chunkSize = 2500;
        Page page1 = new Page(chunkSize);

        int[][] indexes = new int[6][2]; 
        indexes[0] = page1.addData(50, 100);
        indexes[1] = page1.addData(25, 200);
        indexes[2] = page1.addData(14, 400);
        indexes[3] = page1.addData(2, 201);
        indexes[4] = page1.addData(45, 125);
        indexes[5] = page1.addData(21, 140);

        int[][] array = page1.getArray();

        System.out.println("length: " + page1.getArray().length + 
            "length: " + page1.getArray()[1].length);

        for (int i = 0; i < indexes.length; i++) {
            int[] ij = indexes[i];

            System.out.println("at index " + ij[0] + " " +
                 ij[1] + " There is " + array[ij[0]][ij[1]]);
        }

        // System.out.println(array[39][20]);

    }
}