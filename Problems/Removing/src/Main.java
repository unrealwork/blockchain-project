import java.util.Scanner;

public class Main {
    private static class TableEntry<T> {
        private final int key;
        private final T value;
        private boolean removed;

        public TableEntry(int key, T value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public T getValue() {
            return value;
        }

        public void remove() {
            removed = true;
        }

        public boolean isRemoved() {
            return removed;
        }
    }

    private static class HashTable<T> {
        private static final int SCALE_FACTOR = 2;
        private int size;
        private TableEntry[] table;
        private int currentSize;

        public HashTable(int size) {
            this.size = size;
            table = new TableEntry[size];
            this.currentSize = 0;
        }

        public boolean put(int key, T value) {
            // put your code here
            if (currentSize == size) {
                rehash();
            }
            int idx = findKey(key);

            if (idx == -1) {
                return false;
            } else {
                if (table[idx] == null || table[idx].isRemoved() || key != table[idx].getKey()) {
                    currentSize++;
                }
                table[idx] = new TableEntry<T>(key, value);
                return true;
            }
        }

        public T get(int key) {
            int idx = findKey(key);

            if (idx == -1 || table[idx] == null) {
                return null;
            }

            return (T) table[idx].getValue();
        }

        private int findKey(int key) {
            int hash = key % size;

            while (!(table[hash] == null || table[hash].getKey() == key)) {
                hash = (hash + 1) % size;

                if (hash == key % size) {
                    return -1;
                }
            }

            return hash;
        }

        public void remove(final int key) {
            int idx = findKey(key);

            if (idx != -1 && table[idx] != null && !table[idx].isRemoved()) {
                table[idx].remove();
                currentSize--;
            }
        }

        private void rehash() {
            // put your code here
            this.size = size * SCALE_FACTOR;
            this.currentSize = 0;
            TableEntry<T>[] oldTable = this.table;
            this.table = new TableEntry[size];
            for (TableEntry<T> entry : oldTable) {
                put(entry.getKey(), entry.getValue());
            }
        }


        @Override
        public String toString() {
            StringBuilder tableStringBuilder = new StringBuilder();

            for (int i = 0; i < table.length; i++) {
                if (table[i] == null) {
                    tableStringBuilder.append(i + ": null");
                } else {
                    tableStringBuilder.append(i + ": key=" + table[i].getKey()
                            + ", value=" + table[i].getValue()
                            + ", removed=" + table[i].isRemoved());
                }

                if (i < table.length - 1) {
                    tableStringBuilder.append("\n");
                }
            }

            return tableStringBuilder.toString();
        }
    }

    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);
        int initialSize = sc.nextInt();
        int removesSize = sc.nextInt();
        sc.nextLine();
        HashTable<String> hashTable = new HashTable<>(5);
        for (int i = 0; i < initialSize; i++) {
            int key = sc.nextInt();
            String value = sc.nextLine().trim();
            hashTable.put(key, value);
        }
        for (int i = 0; i < removesSize; i++) {
            hashTable.remove(sc.nextInt());
        }
        System.out.println(hashTable);
    }
}
