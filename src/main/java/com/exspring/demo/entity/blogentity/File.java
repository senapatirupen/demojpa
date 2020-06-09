package com.exspring.demo.entity.blogentity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"posts"})
@EqualsAndHashCode(exclude = {"posts"})
@Entity
public class File implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_files",joinColumns = {
            @JoinColumn(name = "file_id", referencedColumnName = "id")},
            inverseJoinColumns={
                    @JoinColumn(name = "post_id", referencedColumnName = "id")})
    private Set<Post> posts;

}
