package blockchain;

import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Deque;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

import static java.nio.charset.StandardCharsets.UTF_8;

public class BlockChain implements Iterable<BlockChain.Block> {
    private final Lock lock = new ReentrantLock();
    private final Deque<Block> blocks;

    private BlockChain() {
        this.blocks = new ConcurrentLinkedDeque<>();
    }

    public static BlockChain getInstance() {
        return new BlockChain();
    }

    public Block mine() {
        lock.lock();
        try {
            final BlockChain.Block block = blocks.isEmpty() ? Block.first() : blocks.peekLast().next();
            blocks.add(block);
            return block;
        } finally {
            lock.unlock();
        }
    }

    @NotNull
    @Override
    public Iterator<Block> iterator() {
        return blocks.iterator();
    }

    @Override
    public void forEach(Consumer<? super Block> action) {
        blocks.forEach(action);
    }

    @Override
    public Spliterator<BlockChain.Block> spliterator() {
        return blocks.spliterator();
    }

    public static BlockChain withSize(final int size) {
        final BlockChain chain = new BlockChain();
        for (int i = 0; i < size; i++) {
            chain.mine();
        }
        return chain;
    }

    public static class Block {
        private static final MessageDigest DIGEST = SHA256Digest();
        private final long id;
        private final long ts;
        private final String previousHashBlock;
        private final String hash;

        public long getId() {
            return id;
        }

        public long getTs() {
            return ts;
        }

        public String getPreviousHashBlock() {
            return previousHashBlock;
        }

        public String getHash() {
            return hash;
        }

        public Block(long id, long ts, String previousHashBlock, String hash) {
            this.id = id;
            this.ts = ts;
            this.previousHashBlock = previousHashBlock;
            this.hash = hash;
        }

        private static BlockChain.Block first() {
            return new BlockChain.Block(1, System.currentTimeMillis(), "0", applySha256(UUID.randomUUID().toString()));
        }

        private BlockChain.Block next() {
            return new BlockChain.Block(id + 1, System.currentTimeMillis(), this.hash, applySha256(hash + (id + 1)));
        }

        @Override
        public String toString() {
            return String.format("%nBlock:%nId: %d%nTimestamp: %d%n" +
                            "Hash of the previous block: %n%s%n" +
                            "Hash of the block: %n%s",
                    id, ts, previousHashBlock, hash
            );
        }

        public static String applySha256(String input) {
            try {
                /* Applies sha256 to our input */
                byte[] hash = DIGEST.digest(input.getBytes(UTF_8));
                StringBuilder hexString = new StringBuilder();
                for (byte elem : hash) {
                    String hex = Integer.toHexString(0xff & elem);
                    if (hex.length() == 1) {
                        hexString.append('0');
                    }
                    hexString.append(hex);
                }
                return hexString.toString();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }


    public static MessageDigest SHA256Digest() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
    
}
