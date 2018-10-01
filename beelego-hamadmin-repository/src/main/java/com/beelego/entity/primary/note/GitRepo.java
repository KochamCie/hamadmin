package com.beelego.entity.primary.note;

import com.beelego.entity.primary.base.UuidTimeDetailEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javaslang.collection.Tree;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

/**
 * @author : hama
 * @since : created in  2018/8/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ha_git_repo")
public class GitRepo extends UuidTimeDetailEntity {

    @Column(name = "sha")
    private String sha;

    @Column(name = "url")
    private String url;

    @Column(name = "truncated", nullable = false, columnDefinition = "tinyint(1) default 0")
    private boolean truncated;

    @Column(name = "repo_name")
    private String repoName;

    @Column(name = "repo_username")
    private String repoUserName;


//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "repo_id")
//    private List<GitTree> tree = new ArrayList<>();

    // @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "gitRepo",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Collection<GitTree> tree;

    @Column(name = "tree_size")
    private long treeSize;


    public boolean isInitialed(){
        return this.treeSize == tree.size();
    }

}
