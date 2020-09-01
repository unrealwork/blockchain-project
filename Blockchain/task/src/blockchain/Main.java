package blockchain;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        BlockChain chain = BlockChain.withSize(10);
        Iterator<BlockChain.Block> it = chain.iterator();
        int i = 0;
        while (it.hasNext() && i < 5) {
            System.out.println(it.next());
            i++;
        }
    }
}
