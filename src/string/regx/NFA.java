package string.regx;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

public class NFA {
    private final char[] re; // 정규 표현식
    private final int M; // 정규 표현식의 길이
    private final Digraph G; // ε-이동을 나타내는 유향 그래프

    public NFA(String regexp) {
        re = regexp.toCharArray(); // 정규 표현식을 문자 배열로 변환
        M = re.length; // 정규 표현식의 길이

        ArrayDeque<Integer> ops = new ArrayDeque<>();
        G = new Digraph(M+1); // 정규 표현식의 길이 + 1의 크기를 갖는 유향 그래프 생성

        for (int i=0; i<M; ++i) {
            int lp = i;

            if (re[i] == '(' || re[i] == '|') {
                ops.push(i);
            } else if (re[i] == ')') { // ( ) 사이에 '|'가 기껏해야 하나만 저장된다고 가정
                int or = ops.pop();

                if (re[or] == '|') {
                    lp = ops.pop();
                    G.addEdge(lp, or+1);
                    G.addEdge(or, i);
                } else {
                    lp = or;
                }
            }

            if (i < M-1 && re[i+1] == '*') {
                G.addEdge(lp, i+1);
                G.addEdge(i+1, lp);
            }

            if (re[i] == '(' || re[i] == '*' || re[i] == ')') {
                G.addEdge(i, i+1);
            }
        }
    }
    
    public boolean recognize(String txt) {
        List<Integer> pc = new LinkedList<>();
        DirectedDfs dfs = new DirectedDfs(G, 0); // 0부터 DFS 수행
        
        for (int v = 0; v < G.V(); ++v) {
            if (dfs.marked(v)) { // dfs에서 방문한 모든 노드를 리스트에 추가
                pc.add(v);
            }
        }
        
        List<Integer> match = new LinkedList<>();
        for (int i=0; i<txt.length(); ++i) {
            match.clear();
            
            for (int v : pc) { // 리스트의 노드에 대해 match transition의 결과 노드 저장
                if (v < M && (re[v] == txt.charAt(i) || re[v] == '.')) {
                    match.add(v+1);
                }
            }
            
            pc.clear();
            dfs = new DirectedDfs(G, match); // match transition의 결과 노드에 대해 ε-이동 수행
            
            for (int v = 0; v < G.V(); ++v) {
                if (dfs.marked(v)) {
                    pc.add(v); // dfs에서 방문한 모든 노드를 리스트에 추가
                }
            }
        }
        
        for (int v : pc) {
            if (v == M) return true; // 마지막 문자까지 매칭한 후, accept 상태에 도달하면 true 반환
        }
        
        return false;
    }

    public static void main(String[] args) {
        NFA nfa = new NFA("((A*B|AC)D)");
        System.out.println(nfa.recognize("AABD")); // true
        System.out.println(nfa.recognize("ACD")); // true
        System.out.println(nfa.recognize("BD")); // true
        System.out.println(nfa.recognize("AABACD")); // false
        System.out.println(nfa.recognize("ACBD")); // false
    }
}

/**
 * DirectedDfs 클래스는 유향 그래프에서 깊이 우선 탐색을 수행하는 클래스이다.
 * 생성자에서 유향 그래프 G와 시작 정점 s를 입력으로 받아 깊이 우선 탐색을 수행한다.
 */
class DirectedDfs {
    private final boolean[] marked;

    public DirectedDfs(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public DirectedDfs(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (int s : sources) {
            if (!marked[s]) {
                dfs(G, s);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }
}

/**
 * Digraph 클래스는 유향 그래프를 나타내는 클래스이다.
 * 생성자에서 정점의 개수 V를 입력으로 받아 인접 리스트를 초기화한다.
 * 정점의 개수 V와 간선의 개수 E, 인접 리스트 adj를 저장한다.
 */
class Digraph {
    private final int V; // 정점의 개수
    private int E; // 간선의 개수
    private final List<List<Integer>> adj; // 인접 리스트

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new LinkedList<>();
        for (int v=0; v<V; ++v) {
            adj.add(new LinkedList<>());
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj.get(v);
    }
}