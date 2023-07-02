import java.util.HashMap;

public class HashCell {
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

class HashTable {
    private HashCell[] cells;

    private int size;

    public HashTable() {
        cells = new HashCell[this.size];

        for (int i = 0; i < this.size; i++) {
            cells[i] = new HashCell();
        }
    }

    private int generateIndex(String key) {
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

        while (cell.next != null) {
            if (key == cell.key) {
                cell.value = value;
                cell.addAddressInPage(i, j);
                return;
            }
        }

        cell.next = new HashCell(null, key, value, i, j);

    }

}






