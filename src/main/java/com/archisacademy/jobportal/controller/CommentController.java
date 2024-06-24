package com.archisacademy.jobportal.controller;

import com.archisacademy.jobportal.dto.CommentDto;
import com.archisacademy.jobportal.services.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createComment(@RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(commentDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteComment(@RequestParam Long id,
                                                @RequestParam String userUuid) {
        return new ResponseEntity<>(commentService.deleteComment(id, userUuid), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateComment(@RequestParam Long id,
                                                @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.updateComment(id, commentDto), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<CommentDto>> getAllComments(@RequestParam(defaultValue = "0") int pageNo,
                                                           @RequestParam(defaultValue = "10") int pageSize) {
        return new ResponseEntity<>(commentService.getAllComments(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.getCommentById(id), HttpStatus.OK);
    }
}
