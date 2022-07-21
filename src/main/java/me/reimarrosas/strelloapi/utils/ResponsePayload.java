package me.reimarrosas.strelloapi.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePayload<T> {
    private String message;
    private boolean success;
    private T payload;
}
