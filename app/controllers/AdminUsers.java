package controllers;
import models.*;
import play.data.validation.*;
import java.util.*;
import play.mvc.*;

public class Backoffice extends SecureAdmin {
    
    public static void index() {
        List inactive = User.getInactiveUsers();
        System.out.println(inactive.size());
        render(inactive);
    }
    
    public static void activateUser(Long id) {
        notFoundIfNull(id);
        User user = User.findById(id);
        notFoundIfNull(user);
        user.activate();
        user.save();
        index();
    }
    
    public static void deleteUser(Long id) {
        notFoundIfNull(id);
        User user = User.findById(id);
        notFoundIfNull(user);
        user.delete();
        index();
    }
}