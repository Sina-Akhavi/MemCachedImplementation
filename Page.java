import java.util.concurrent.Semaphore;

public class Page {
    static final int MG = 1000000;

    private int[][] pageArray;

    
    public int chunkSize;

    private int filledChunkCounter;

    private LRU lru;

    private Semaphore semaphore;

    private HashTable hashTable;
    // create HashTable

    
    public Page(int chunkSize) {
        this.chunkSize = chunkSize;
        this.createPage();
        this.filledChunkCounter = 0;
        this.lru = new LRU();
        this.semaphore = new Semaphore(1);
        this.hashTable = new HashTable(20);
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
        try {
            this.semaphore.acquire();
            if (this.pageArray[i][j] == 0) {
                this.semaphore.release();
                return true;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
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
        double numChunksDouble = MG / this.chunkSize;
        int numRowsCols = (int) Math.sqrt(numChunksDouble);

        this.pageArray = new int[numRowsCols][numRowsCols];
    }

    public int[] addData(int data, String key) {

        int[] output = new int[2];
        output[0] = -1;            
        output[1] = -1; 

        if (data > this.chunkSize) {
            return output;
        }

        if (this.canBePlaced()) {
            int[] ij = this.findEmptyChunk();
            int i = ij[0];            
            int j = ij[1];
            
            this.pageArray[i][j] = data;
            this.lru.doAnything(i, j);
            // update HashTable
            this.hashTable.write(key, data, i, j);
            System.out.println("data " + data + " added in " 
                + "i= " + i + " j=" + j + " page chunksize " + this.chunkSize);
            this.lru.show(this.chunkSize);

            return ij;
        } 

        return output;
    }

    public int[][] getArray() {
        return this.pageArray;
    }

    public void show() {
        System.out.println("*************************");
        System.out.println("a " + this.chunkSize + "-chunkSize Page");

        for (int i = 0; i < this.pageArray.length; i++) {
            for (int j = 0; j < this.pageArray.length; j++) {
                if (this.pageArray[i][j] != 0) {
                    System.out.println("[" + i + "]" +"[" + j + "] ==> " + this.pageArray[i][j]);
                }
            }
        }
        
        System.out.println("*************************");
    }

    public LRU getLRU() {
        return this.lru;
    }

    public HashTable getHashTable() {
        return this.hashTable;
    }

}


// public class Main {
//     public static void main(String[] args) {
//         int chunkSize = 2500;
//         Page page1 = new Page(chunkSize);

//         int[][] indexes = new int[6][2]; 
//         indexes[0] = page1.addData(50, 100);
//         indexes[1] = page1.addData(25, 200);
//         indexes[2] = page1.addData(14, 400);
//         indexes[3] = page1.addData(2, 201);
//         indexes[4] = page1.addData(45, 125);
//         indexes[5] = page1.addData(21, 140);

//         int[][] array = page1.getArray();

//         System.out.println("length: " + page1.getArray().length + 
//             "length: " + page1.getArray()[1].length);

//         for (int i = 0; i < indexes.length; i++) {
//             int[] ij = indexes[i];

//             System.out.println("at index " + ij[0] + " " +
//                  ij[1] + " There is " + array[ij[0]][ij[1]]);
//         }

//         // System.out.println(array[39][20]);

//     }
// }