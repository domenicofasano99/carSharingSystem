package utilities;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtilities {
	public static String getUserMail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		String utenteMail = "";
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("mail")) {
					if(!c.getName().equals("-1"))
						utenteMail = c.getValue();
				}
				if (!utenteMail.equals("")) {
					return utenteMail;
				}
			}
			return null;
		} else {
			return null;
		}
	}

	public static void deleteCookies(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("mail")){
					System.out.println(c.getValue());
					c.setValue("-1");
					System.out.println(c.getValue());
					response.addCookie(c);
				}
			}
				
		}
	}
}
