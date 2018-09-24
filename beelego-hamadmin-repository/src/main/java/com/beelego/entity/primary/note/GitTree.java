package com.beelego.entity.primary.note;

import com.beelego.entity.primary.base.UuidEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author : hama
 * @since : created in  2018/8/17
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "ha_git_tree")
public class GitTree extends UuidEntity {

    @ManyToOne(cascade = CascadeType.ALL,optional=true)  //(fetch=FetchType.LAZY)
    @JoinColumn(name = "repo_id")
    private GitRepo gitRepo;

    @Column(name = "path")
    private String path;

    @Column(name = "mode")
    private String mode;

    @Column(name = "type")
    private String type;

    @Column(name = "sha")
    private String sha;

    @Column(name = "size")
    private int size;

    @Column(name = "url")
    private String url;

    @Column(name = "content")
    private String content;

    @Column(name = "nodeId")
    private String nodeId;

    @Column(name = "encoding")
    private String encoding;

}
