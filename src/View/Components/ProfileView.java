package View.Components;

import Data.Model.User;

public class ProfileView {
    public static void showProfile(User user){
        if(user != null){
            System.out.println();
            String name = user.getNama();
            String id = user.getUserID();
            String email = user.getEmail();
            System.out.println("====== USER PROFILE =====");
            System.out.println("ID : " + id);
            System.out.println("Name : " + name);
            System.out.println("Email : " + email);
            System.out.println("====== ============= =====");
            System.out.println();
        }
    }
}
