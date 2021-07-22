package com.evilve.blog.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors
public class BlogQuery {
    private String title;
    private Long typeId;
    private boolean recommend;

}
