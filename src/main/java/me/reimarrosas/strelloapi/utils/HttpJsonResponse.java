package me.reimarrosas.strelloapi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpJsonResponse {
    public static void createJsonResponse(HttpServletResponse response, int statusCode, Object obj) throws IOException {
        var mapper = new ObjectMapper();
        var out = response.getWriter();
        response.setContentType("application/json");
        response.setStatus(statusCode);
        response.setCharacterEncoding("UTF-8");
        out.print(mapper.writeValueAsString(obj));
        out.flush();
    }
}
