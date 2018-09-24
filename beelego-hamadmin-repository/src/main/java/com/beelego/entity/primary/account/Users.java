package com.beelego.entity.primary.account;

import com.beelego.entity.primary.base.UuidTimeDetailEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 *
 *
 * @author : hama
 * @since : created in  2018/7/25
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "user")
public class Users extends UuidTimeDetailEntity {

    @Column(name = "name", columnDefinition = "varchar(20) comment '姓名'", nullable = false)
    private String name;

}
