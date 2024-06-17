package hash;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ExtensibleHashST<K, V> {
    private int mi; // global i
    private Bucket<K, V>[] directory;
    private int bucketCapacity;
    private int N;
    private ArrayList<Bucket<K, V>> blist; // Bucket 정보 출력용

    public ExtensibleHashST() {
        this(1);
    }

    public ExtensibleHashST(int bucketCapacity) {
        this.mi = 0;
        this.directory = new Bucket[1];
        this.directory[0] = new Bucket<>(bucketCapacity, 0);
        this.bucketCapacity = bucketCapacity;
        this.N = 0;
        this.blist = new ArrayList<>();
    }

    public V get(K key) {
        return directory[hash(key)].search(key);
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public int size() {
        return N;
    }

    public void detailInfo() {
        System.out.println("mi = " + mi);
        System.out.println("N = " + N);
        System.out.println("Directory: ");
        for (int i=0; i<directory.length; ++i) {
            System.out.println("[" + i + "] " + directory[i].getSize() + " " + directory[i].getNBits());
        }
        System.out.println("Bucket List: ");
        for (int i=0; i<blist.size(); ++i) {
            System.out.println("[" + i + "] " + blist.get(i).getSize() + " " + blist.get(i).getNBits());
        }
    }

    public void summaryInfo() {
        System.out.println("mi = " + mi);
        System.out.println("N = " + N);
        System.out.println("Directory: ");
        for (int i=0; i<directory.length; ++i) {
            System.out.println("[" + i + "] " + directory[i].getSize() + " " + directory[i].getNBits());
        }
    }

    public Collection<K> keys() {
        ArrayList<K> keys = new ArrayList<>();
        for (Bucket<K, V> bucket : directory) {
            for (int i=0; i<bucket.getSize(); ++i) {
                keys.add(bucket.getKey(i));
            }
        }
        return keys;
    }

    public void put(K key, V value) {
        int pos = hash(key);

        if (directory[pos].search(key) != null) { // 이미 존재함. 업데이트
            directory[pos].update(key, value);
            return;
        }

        if (directory[pos].getSize() < bucketCapacity) { // 추가
            directory[pos].add(key, value);
            ++N;
            return;
        }

        if (directory[pos].getNBits() < mi) { // bucket 분할 발생
            splitBucket(pos);
            put(key, value);
            return;
        }

        resize(mi+1); // directory를 2배로 확장 (global bit 1 증가)
        put(key, value);
    }

    private void splitBucket(int pos) { // pos = hash(key)
        // Phase 1: old bucket에서 MSD가 1인 항목을 new bucket으로 이동
        Bucket<K, V> oldBucket = directory[pos];
        Bucket<K, V> newBucket = new Bucket<>(this.bucketCapacity, oldBucket.getNBits() + 1);

        int oldPos = 0, newPos = 0;
        int oldHash = pos % ((1 << oldBucket.getNBits()) - 1); // MSD가 0인 버킷
        int newHash = pos % ((1 << newBucket.getNBits()) - 1); // MSD가 1인 버킷

        for (int i=0; i<oldBucket.getSize(); ++i) {
            if (hash(oldBucket.getKey(i), newBucket.getNBits()) == newHash) {
                newBucket.updatePair(newPos++, oldBucket.getPairs()[i]);
            } else {
                oldBucket.updatePair(oldPos++, oldBucket.getPairs()[i]);
            }
        }

        newBucket.updateSize(newPos);
        oldBucket.updateSize(oldPos);
        oldBucket.updateNBits(oldBucket.getNBits() + 1);

        for (int i=oldBucket.getSize(); i<oldBucket.getCapacity(); ++i) {
            oldBucket.updatePair(i, null); // 가비지 컬렉션 회수
        }

        // Phase 2: directory 참조 변경
        int delta = 0, addr = newHash;
        while (addr < (1 << mi)) {
            directory[addr] = newBucket;
            ++delta;
            addr = (delta << newBucket.getNBits()) | newHash;
        }
    }

    private void resize(int newBits) { // directory 확장되는 것만 고려
        Bucket<K, V>[] newDirectory = new Bucket[1 << newBits];

        for (int i=0; i<newDirectory.length; ++i) {
            newDirectory[i] = directory[i % directory.length];
        }

        // 가비지 컬렉션 회수
        Arrays.fill(directory, null);

        directory = newDirectory;
        this.mi = newBits;
    }

    private int hash(K key) {
        return key.hashCode() & ((1 << mi) - 1);
    }

    private int hash(K key, int nBits) {
        return key.hashCode() & ((1 << nBits) - 1);
    }
}

class Bucket<K, V> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id;
    private int capacity;
    private int nBits;
    private int size;
    private Pair<K, V>[] pairs;

    protected  Bucket() {
        this(2, 0);
    }

    protected  Bucket(int capacity, int nBits) {
        this.capacity = capacity;
        this.nBits = nBits;
        this.pairs = new Pair[capacity];
    }

    protected V search(K key) { // Bucket
        for (int i = 0; i < size; i++) { // Bucket 내에서 순차 검색
            if (pairs[i].key.equals(key)) {
                return pairs[i].value;
            }
        }
        return null;
    }

    protected void add(K key, V value) {
        if (size == capacity) {
            throw new IllegalStateException("Bucket is full");
        }
        pairs[size++] = new Pair<>(key, value);
    }

    protected void update(K key, V value) {
        for (int i = 0; i < size; i++) {
            if (pairs[i].key.equals(key)) {
                pairs[i] = new Pair<>(key, value);
                return;
            }
        }
    }

    protected void updatePair(int pos, Pair<K, V> pair) {
        pairs[pos] = pair;
    }

    protected void updateSize(int size) {
        this.size = size;
    }

    protected void updateNBits(int nBits) {
        this.nBits = nBits;
    }

    protected int getSize() {
        return size;
    }

    protected int getNBits() {
        return nBits;
    }

    protected K getKey(int i) {
        return pairs[i].key;
    }

    protected int getCapacity() {
        return capacity;
    }

    protected Pair<K, V>[] getPairs() {
        Pair<K, V>[] view = new Pair[size];
        System.arraycopy(pairs, 0, view, 0, size);
        return view;
    }

    private record Pair<K, V>(K key, V value) {
    }
}
