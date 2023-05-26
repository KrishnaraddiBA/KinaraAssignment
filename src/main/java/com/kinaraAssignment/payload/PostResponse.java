package com.kinaraAssignment.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostResponse {

    private List<StudentDto> content;
    private int totalPages;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private boolean last;
}
