package controllers;
import java.io.File;
import play.vfs.VirtualFile;
import models.*;
import java.util.*;

public class Users extends Secure {
  
	public static void view(Long id) {
      notFoundIfNull(id);
      User userDb = User.findById(id);
      notFoundIfNull(userDb);
      User current = connectedUser();
      //if(current.id == id)
      //  Profile.index();
      UserInfo user = new UserInfo(userDb, current);
      List<UserInfo> friends = new ArrayList<UserInfo>();
      for(User u : userDb.friends)
        friends.add(new UserInfo(u, current));
      render(user, friends);
	}
	
	public static void avatar(Long id) {
	    VirtualFile avatar = VirtualFile.fromRelativePath("/data/users/"+id+"/avatar");
	    renderBinary(avatar.exists() ? avatar.getRealFile() : VirtualFile.fromRelativePath("/public/images/avatar.png").getRealFile(), "avatar");
	}
}
