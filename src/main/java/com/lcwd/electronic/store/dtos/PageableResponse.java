package com.lcwd.electronic.store.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageableResponse<T> {    // it is generic type and can be turned into what object we need .
    private List<T> contnet;                // it will hold the dto ( each users data like shambhu , ram, hari etc)
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;

}
