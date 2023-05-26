package com.kinaraAssignment.service;

import com.kinaraAssignment.Repository.StudentRepository;
import com.kinaraAssignment.entity.Student;
import com.kinaraAssignment.exception.ResourceNotFoundException;
import com.kinaraAssignment.payload.PostResponse;
import com.kinaraAssignment.payload.StudentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentDto saveDatas(StudentDto studentDto) {

        Student student = mapToEntity(studentDto);

        Student save = studentRepository.save(student);
        StudentDto studentDto2 = mapToDto(save);

        return  studentDto2;
    }

    @Override
    public PostResponse findAllDatasInDb(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Student> posts = studentRepository.findAll(pageable);
        List<Student> content = posts.getContent();

        List<StudentDto> contents = content.stream().map(c -> mapToDto(c)).collect(Collectors.toList());

        PostResponse postResponse= new PostResponse();
        postResponse.setContent(contents);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return postResponse;


    }

    @Override
    public StudentDto getByIdes(int id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(student);
    }

    @Override
    public StudentDto updateposts(StudentDto stuDto, int id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        student.setId(stuDto.getId());
        student.setName(stuDto.getName());
        student.setTotalMarks(stuDto.getTotalMarks());
        Student save = studentRepository.save(student);
        StudentDto studentDto = mapToDto(save);

        return studentDto;
    }

    @Override
    public void deletePosts(int id) {
        Student s = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        studentRepository.delete(s);


    }

    StudentDto mapToDto(Student student){

        StudentDto map = modelMapper.map(student, StudentDto.class);
        return map;
    }

    Student mapToEntity(StudentDto studentDto){
        Student map1 = modelMapper.map(studentDto, Student.class);

        return map1;
    }




}
