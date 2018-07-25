package com.beelego.entity.primary.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 通用创建时间、修改时间
 * @author : hama
 * @since : created in  2018/7/25
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class TimeEntity extends BaseEntity {


    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "comment '创建时间'")
    private Date createTime;


    @JsonIgnore
    @LastModifiedDate
    @Column(name = "update_time", columnDefinition = "comment '更新时间'")
    private Long updateTime;

}
