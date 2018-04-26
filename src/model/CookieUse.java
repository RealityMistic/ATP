package model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Player;
public class CookieUse {
	public void addUserCookie(HttpServletRequest request, HttpServletResponse response, Player userp){
		Cookie c=new Cookie("user", userp.getUser());
		c.setMaxAge(100000);
		response.addCookie(c);
	}
	public String recuperaUserCookie(Cookie cp){
		return cp.getValue();
	}
}
