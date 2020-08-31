import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        private int size;
        private TableEntry[] table;

        public HashTable(int size) {
            this.size = size;
            table = new TableEntry[size];
        }

        public boolean put(int key, T value) {
            // put your code here
            int idx = findEntryIndex(key);

            if (idx == -1) {
                return false;
            }

            table[idx] = new TableEntry(key, value);
            return true;
        }

        public T get(int key) {
            // put your code here
            int idx = findEntryIndex(key);

            if (idx == -1 || table[idx] == null) {
                return null;
            }

            return (T) table[idx].getValue();
        }

        public void remove(int key) {
            // put your code here
            int idx = findEntryIndex(key);
            if (idx != -1 && table[idx] != null) {
                table[idx] = null;
            }
        }

        private int findKey(int key) {
            // put your code here
            return findEntryIndex(key);
        }


        private int findEntryIndex(int key) {
            int hash = key % size;

            while (!(table[hash] == null || table[hash].getKey() == key)) {
                hash = (hash + 1) % size;

                if (hash == key % size) {
                    return -1;
                }
            }

            return hash;
        }

        @Override
        public String toString() {
            StringBuilder tableStringBuilder = new StringBuilder();

            for (int i = 0; i < table.length; i++) {
                if (table[i] == null) {
                    tableStringBuilder.append(i + ": null");
                } else {
                    tableStringBuilder.append(i + ": key=" + table[i].getKey()
                            + ", value=" + table[i].getValue());
                }

                if (i < table.length - 1) {
                    tableStringBuilder.append("\n");
                }
            }

            return tableStringBuilder.toString();
        }
    }

    enum ActionType {
        PUT, GET, REMOVE;
        private static final Map<String, ActionType> MAP = Arrays.stream(ActionType.values())
                .collect(Collectors.toMap(
                        e -> e.name().toLowerCase(),
                        Function.identity()
                ));


        public static ActionType parse(String value) {
            if (MAP.containsKey(value)) {
                return MAP.get(value);
            }
            throw new IllegalStateException("Faile to parse action type from : " + value);
        }
    }

    static class Action {
        private final ActionType type;
        private final Consumer<HashTable<String>> consumer;

        Action(ActionType type, Consumer<HashTable<String>> consumer) {
            this.type = type;
            this.consumer = consumer;
        }

        public void doAction(final HashTable<String> table) {
            consumer.accept(table);
        }
    }

    static Action readAction(Scanner scanner) {
        final ActionType type = ActionType.parse(scanner.next());
        switch (type) {
            case PUT:
                int id = scanner.nextInt();
                String number = scanner.nextLine().trim();
                return new Action(type, table -> table.put(id, number));
            case GET:
                id = scanner.nextInt();
                return new Action(type, table -> {
                    final String value = table.get(id);
                    System.out.println(value != null ? value : -1);
                });
            case REMOVE:
                id = scanner.nextInt();
                return new Action(type, table -> table.remove(id));
        }
        return null;
    }

    public static void main(String[] args) {
        // put your code here
        final Scanner scanner = new Scanner(System.in);
        final int size = scanner.nextInt();
        scanner.nextLine();
        HashTable<String> table = new HashTable<>(size);
        for (int i = 0; i < size; i++) {
            Action action = readAction(scanner);
            if (action != null) {
                action.doAction(table);
            }
        }
    }
}
 
