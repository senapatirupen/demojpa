package com.exspring.demo.repository.blogrepo;

import com.exspring.demo.entity.blogentity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
