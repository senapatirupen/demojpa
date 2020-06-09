package com.exspring.demo.entity.blogentity;

import java.io.Serializable;

public enum PostStatus implements Serializable {

    ACTIVE(1), NOT_ACTIVE(2);

    int status;

    PostStatus(int status)  {
        this.status = status;
    }
}
