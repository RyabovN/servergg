package servlets;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zak on 14.11.2015.
 */
public class AccountService {

    private Map<String,UserProfile> users = new HashMap<String,UserProfile>();
    private Map<String,UserProfile> sessions = new HashMap<String,UserProfile>();

    public boolean addUser(String name, UserProfile userProfile) {
        if (users.containsKey(name))
            return false;
        users.put(name, userProfile);
        return true;
    }

    public void addSession(String sessionID, UserProfile userProfile) {
        sessions.put(sessionID,userProfile);
    }

    public UserProfile getUser(String userName) {
        return users.get(userName);
    }

    public UserProfile getSession(String sessionID) {
        sessions.get(sessionID);
    }
}
