package org.cadmium.nichijo.utils;

import org.cadmium.nichijo.entity.User;

public abstract class UserHandler {
    
    private final static ThreadLocal<User> th = new InheritableThreadLocal<>();
    
    
    private UserHandler() {
        super();
    }
    
    
    public static void set(User user) {
        th.set(user);
    }
    
    
    public static User get() {
        return th.get();
    }
    
    
    public static void remove() {
        th.remove();
    }
    
    
}
