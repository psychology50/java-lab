package design_pattern.proxy.type;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface UserService {
    List<String> getUsers(String id);
    int getAccessCount();
}

class UserServiceImpl implements UserService {
    private final Map<String, List<String>> users = Map.of(
            "id1", List.of("user1", "user2", "user3"),
            "id2", List.of("user4", "user5", "user6"),
            "id3", List.of("user7", "user8", "user9")
    );
    private int count;

    @Override
    public List<String> getUsers(String id) {
        ++count;
        return List.of("user1", "user2", "user3");
    }

    @Override
    public int getAccessCount() {
        return count;
    }
}

class CachingProxy implements UserService {
    private final UserService userService;
    private ConcurrentMap<String, List<String>> cachedUsers;
    private final Object writeLock = new Object();

    CachingProxy(UserService userService) {
        this.userService = userService;
        this.cachedUsers = new ConcurrentHashMap<>();
    }

    @Override
    public List<String> getUsers(String id) {
        if (!cachedUsers.containsKey(id)) {
            synchronized (writeLock) {
                if (!cachedUsers.containsKey(id)) {
                    cachedUsers.put(id, userService.getUsers(id));
                }
            }
        }
        return cachedUsers.get(id);
    }

    @Override
    public int getAccessCount() {
        return userService.getAccessCount();
    }
}

public class CachingProxyClient {
    public static void main(String[] args) {
        UserService cachingProxy = new CachingProxy(new UserServiceImpl());
        System.out.println(cachingProxy.getUsers("id1"));
        System.out.println(cachingProxy.getUsers("id2"));
        System.out.println(cachingProxy.getUsers("id3"));
        System.out.println(cachingProxy.getUsers("id1"));
        System.out.println("Access count: " + cachingProxy.getAccessCount());
    }
}
