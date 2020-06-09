package com.exspring.demo.repository.blogrepo;

import com.exspring.demo.entity.blogentity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
