package ua.edu.ukma.javaee.polishchuk.demo.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PageResponse<T> {
    private final Iterable<T> list;
    private final int totalPages;
}
