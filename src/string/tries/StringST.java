package string.tries;

public interface StringST<V> {
    /**
     * key에 val을 저장한다.
     * val이 null이면 key를 삭제한다.
     */
    void put(String key, V val);

    /**
     * key에 대한 값을 반환한다.
     */
    V get(String key);

    /**
     * key-value 쌍을 삭제한다.
     */
    void delete(String key);

    /**
     * key가 존재하는지 여부를 반환한다.
     */
    boolean contains(String key);

    /**
     * 테이블이 비어있는지 여부를 반환한다.
     */
    boolean isEmpty();

    /**
     * s의 접두사 중 가장 긴 키를 반환한다.
     */
    String longestPrefixOf(String s);

    /**
     * s를 접두사로 가지는 키들을 반환한다.
     */
    Iterable<String> keysWithPrefix(String s);

    /**
     * s와 매치되는 키들을 반환한다.
     * .은 와일드 카드로 사용된다.
     */
    Iterable<String> keysThatMatch(String s);

    /**
     * 테이블의 모든 키를 반환한다.
     */
    int size();

    /**
     * 테이블의 모든 키를 반환한다.
     */
    Iterable<String> keys();
}
