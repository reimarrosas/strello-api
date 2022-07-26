package me.reimarrosas.strelloapi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePayload<T> {
    private String message;
    private boolean success;
    private T payload;

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
