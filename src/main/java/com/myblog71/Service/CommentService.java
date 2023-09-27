package com.myblog71.Service;

import com.myblog71.Payload.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    CommentDto creatComment(long postId,CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(long postId);


    CommentDto getCommentsById(Long postId, Long commentId);

    List<CommentDto> getAllCommentsById();

    void deleteCommentById(Long postId, Long commentId);
}
