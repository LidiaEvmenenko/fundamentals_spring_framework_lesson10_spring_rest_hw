package ru.geekbrains.hw9.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class MarketError {
    private List<String> message;
    private Date date;

    public MarketError(List<String> message) {
        this.message = message;
        this.date = new Date();
    }
    public MarketError(String message) {
        this(List.of(message));
    }

    public MarketError(String... messages) {
        this(Arrays.asList(messages));
    }
}
