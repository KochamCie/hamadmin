package com.beelego.entity.primary;

import com.beelego.enums.ModuleTypeEnum;
import com.beelego.global.base.BaseObject;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : hama
 * @since : created in  2018/6/25
 */
@Data
@Entity
public class ModuleSource extends BaseObject {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "com.beelego.ds.CustomUUIDGenerator"
    )
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ModuleTypeEnum module;

    @Column(name = "ip", nullable = false)
    private String ip;

    @Column(name = "port")
    private int port = 8080;

    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime = new Date();

}
