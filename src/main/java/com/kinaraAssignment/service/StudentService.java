package com.kinaraAssignment.service;

import com.kinaraAssignment.payload.PostResponse;
import com.kinaraAssignment.payload.StudentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    StudentDto saveDatas(StudentDto studentDto);

    PostResponse findAllDatasInDb(int pageNo, int pageSize, String sortBy, String sortDir);

    StudentDto getByIdes(int id);

    StudentDto updateposts(StudentDto stuDto, int id);

    void deletePosts(int id);
}
