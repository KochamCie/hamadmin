package com.beelego.entity.primary.base;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 通用id字段
 * @author : hama
 * @since : created in  2018/7/25
 */
@Data
@MappedSuperclass
public class UuidEntity extends BaseEntity {

    @Id
    @Column(name = "id", columnDefinition = "varchar(36) comment '主键编号'")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "com.beelego.ds.CustomUUIDGenerator"
    )
    protected String id;

}
