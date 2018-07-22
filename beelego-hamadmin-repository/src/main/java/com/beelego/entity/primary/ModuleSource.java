package com.beelego.entity.primary;

import com.beelego.enums.ModuleTypeEnum;
import com.beelego.global.base.BaseObject;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.Valid;
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

    @Column(name = "publish", nullable = false, columnDefinition = "tinyint(1) default 0")
    private boolean publish;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "publish_time")
    private Date publishTime = new Date();

    @Column(name = "url", nullable = false, columnDefinition = "varchar(255) default 'https://www.baidu.com'")
    private String url = "http://172.16.3.211:8888/lanmao/swagger-ui.html";

    public String getAlias() {
        return this.ip + "_" + this.port;
    }

}
