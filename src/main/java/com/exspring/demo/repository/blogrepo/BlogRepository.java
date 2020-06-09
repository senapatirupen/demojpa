package com.exspring.demo.repository.blogrepo;

import com.exspring.demo.entity.blogentity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
