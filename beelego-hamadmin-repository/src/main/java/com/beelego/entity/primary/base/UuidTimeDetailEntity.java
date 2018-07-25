package com.beelego.entity.primary.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 通用id字段、修改时间、创建时间、创建人、修改人
 *
 * @author : hama
 * @since : created in  2018/7/25
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class UuidTimeDetailEntity extends BaseEntity {

    @Id
    @Column(name = "id", columnDefinition = "varchar(36) comment '主键编号'")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "com.beelego.ds.CustomUUIDGenerator"
    )
    private String id;


    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", columnDefinition = "datetime comment '创建时间'")
    private Date createdDate;

    @CreatedBy
    @Column(name = "created_by", columnDefinition = "varchar(36) comment '创建人'")
    @JsonIgnore
    private String createdBy;


    @JsonIgnore
    @LastModifiedDate
    @Column(name = "last_modified_date", columnDefinition = "bigint(20) comment '更新时间'")
    private Long lastModifiedDate;


    @LastModifiedBy
    @Column(name = "last_modified_by", columnDefinition = "varchar(36) comment '修改人'")
    @JsonIgnore
    private String lastModifiedBy;
}
