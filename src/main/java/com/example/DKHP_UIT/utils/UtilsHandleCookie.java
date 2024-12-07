package com.example.DKHP_UIT.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.DKHP_UIT.exception.ExceptionCode;
import com.example.DKHP_UIT.exception.ExceptionUser;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UtilsHandleCookie {

    @Value("${jwt.jwtTime}")
    private int jwtTime;

    public void setCookie(String nameCookie, String valueCookie, HttpServletResponse httpServletResponse) {
        Cookie cookie = new Cookie(nameCookie, valueCookie);
        cookie.setHttpOnly(false); // it is used to access cookie from client
        cookie.setSecure(true); // Ensure the cookie is only sent over HTTPS
        cookie.setMaxAge(jwtTime);
        cookie.setPath("/"); // Cookie will be available to the entire application
        httpServletResponse.addCookie(cookie);
    }

    public String getCookie(HttpServletRequest req, String nameCk) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
                if (nameCk.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        throw new ExceptionUser(ExceptionCode.NotFoundJwtInCookie);
    }
}
