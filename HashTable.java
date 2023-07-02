import java.util.HashMap;

import javax.sound.midi.Soundbank;

class HashCell {
    public HashCell next;
    public String key;
    public int value;
    private int[] addressInPage;
    
    public HashCell(HashCell next, String key, int value, int i, int j) {
        this.next = next;
        this.key = key;
        this.value = value;
        addressInPage = new int[2];
        addressInPage[0] = i;
        addressInPage[1] = j;

    }

    public HashCell clone() {
        HashCell cel = new HashCell(this.next, this.key, this.value,
            this.getAddressInPage()[0], this.getAddressInPage()[1] );
        return cel;
    }

    public HashCell() {
        addressInPage = new int[2];
    }
    

    public void addData(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public void addAddressInPage(int i, int j) {
        int[] temp = new int[2];

        temp[0] = i;
        temp[1] = j;

        this.addressInPage = temp;
    }

    public int[] getAddressInPage() {
        return this.addressInPage;
    }
}

public class HashTable {
    private HashCell[] cells;

    private int size;

    public HashTable(int size) {
        this.size = size;
        cells = new HashCell[this.size];

    }

    public int generateIndex(String key) {
        int generatedIndex;

        int sum = 0;
        int prime = 31;
        for (int i = 0; i < key.length(); i++) {
            sum += (int) key.charAt(i);
        }
        sum *= prime;

        generatedIndex = sum % this.size;

        return generatedIndex;
    }
    
    public int[] readForAddress(String key) {
        int index = this.generateIndex(key);

        HashCell cell = this.cells[index];

        while(cell != null) {
            if (cell.key == key) {
                int[] addressInPage = cell.getAddressInPage();
                return addressInPage;
            }

            cell = cell.next;
        }

        return null;
    }

    public void write(String key, int value, int i, int j) {
        // if the key exists, it is updated 
        
        int index = this.generateIndex(key);
        HashCell cell = this.cells[index];
        if (cell == null) {
            System.out.println("Hello1");
            HashCell newCell = new HashCell(null, key, value, i, j);
            this.cells[index] = newCell;
            return;
        }

        while (cell != null) {
            if (key == cell.key) {
                cell.value = value;
                cell.addAddressInPage(i, j);
                return;
            }
            if (cell.next == null) {
                System.out.println("Hello2");
                cell.next = new HashCell(null, key, value, i, j);
                return;
            }
            cell = cell.next;
        }


    }

    public void show() {

        for (int i = 0; i < this.size; i++) {
            HashCell cell = this.cells[i];
            if (cell == null) {
                continue;
            }

            System.out.print("index " + i + " is " + cell.value + "==>");
            while (cell.next != null) {
                cell = cell.next;
                System.out.print("index " + i + " is " + cell.value + "with key: " + cell.key + "==>");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int size = 10;
        HashTable hashTable = new HashTable(size);
        
        int i = 3;
        int j = 6;
        int value = 5;

        hashTable.write("sian", value, i, j);

        int value1 = 6; 
        hashTable.write("sian", value1, i, j);
        
        int value2 = 16; 
        hashTable.write("dnas", value2, i, j);

        int value3 = 26; 
        hashTable.write("a", value3, i, j);
        
        int value4 = 88; 
        hashTable.write("ndas", value4, i, j);


        
        hashTable.show();
        // int value2 = 7; 
        // hashTable.write("ttes", value2, i, j);

        // int value3 = 4; 
        // hashTable.write("abcd", value3, i, j);

        // int value4 = 2; 
        // hashTable.write("abdc", value4, i, j);

        // int value5 = 8;
        // hashTable.write("ieee", value5, i, j);


        // int value6 = 1;
        // hashTable.write("ieee", value6, i, j);


        // int value7 = 4;
        // hashTable.write("ieee", value7, i, j);

        System.out.println(hashTable.generateIndex("a") + "  " + hashTable.generateIndex("sina"));



    }

}






