package com.kinaraAssignment.controller;

import com.kinaraAssignment.utils.AppConstants;
import com.kinaraAssignment.payload.PostResponse;
import com.kinaraAssignment.payload.StudentDto;
import com.kinaraAssignment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class StudentController {

    @Autowired
    private StudentService stService;

//    localhost:8080/api/v1/post
    @PostMapping("/post")
    public ResponseEntity<StudentDto> savingData(@RequestBody StudentDto studentDto){
        StudentDto st = stService.saveDatas(studentDto);

        return new ResponseEntity<>(st, HttpStatus.OK);
    }

//    localhost:8080/api/v1/get

    @GetMapping("/get")
    public ResponseEntity<PostResponse> getAllDatasOfStudents(@RequestParam(value="pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NO,required = false)int pageNo,
                                                                  @RequestParam(value="pageSize",defaultValue=AppConstants.DEFAULT_PAGE_SIZE,required = false)int pageSize,
                                                                  @RequestParam(value="sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false)String sortBy,
                                                                  @RequestParam(value="sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir){
        PostResponse allDatasInDb = stService.findAllDatasInDb(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allDatasInDb,HttpStatus.FOUND);
    }

//    localhost:8080/api/v1/1
    @GetMapping("/{id}")
    public StudentDto getByIds(@PathVariable(name="id") int id){
        return stService.getByIdes(id);
    }


//    localhost:8080/api/v1/updatePost/1
    @PutMapping("/updatePost/{id}")
    public ResponseEntity<StudentDto> updatePost(@PathVariable(name="id") int id,@RequestBody StudentDto stuDto){
        return new ResponseEntity<>(stService.updateposts(stuDto,id),HttpStatus.OK);
    }


//    localhost:8080/api/v1/deletePost/1
    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<String> deletePosts(@PathVariable(name="id") int id){
        stService.deletePosts(id);

        return  new ResponseEntity<>("Student deleted successfully!!!",HttpStatus.OK);

    }
}
