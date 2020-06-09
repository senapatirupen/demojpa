package com.exspring.demo.repository.blogrepo;

import com.exspring.demo.entity.blogentity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
