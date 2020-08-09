package com.mavenwebapp.utils;

import javax.servlet.http.HttpSession;

import com.mavenwebapp.beans.UserAccount;


public class MyUtils {
	
	// Store user info in Session.
    public static void storeLoginedUser(HttpSession session, UserAccount loginedUser) {
        // On the JSP can access via ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
    }
 
    // Get the user information stored in the session.
    public static UserAccount getLoginedUser(HttpSession session) {
    	UserAccount loginedUser = null;
    	if(session.getAttribute("loginedUser")!=null) {
    		loginedUser = (UserAccount) session.getAttribute("loginedUser");
    	}
        return loginedUser;
    }

}
