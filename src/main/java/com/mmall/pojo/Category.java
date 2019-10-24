package com.mmall.pojo;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Data
@ToString 
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
public class Category {
    private Integer id;

    private Integer parentId;

    private String name;

    private Boolean status;

    private Integer sortOrder;

    private Date createTime;

    private Date updateTime;
    
}