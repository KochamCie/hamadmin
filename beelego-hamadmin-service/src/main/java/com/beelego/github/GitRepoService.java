package com.beelego.github;

import com.beelego.entity.primary.note.GitRepo;
import com.beelego.entity.primary.note.GitTree;
import com.beelego.global.result.ApiResult;
import com.beelego.model.github.RepoTree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author : hama
 * @since : created in  2018/9/20
 */
public interface GitRepoService {

    ApiResult gitRepoInit(GitRepo gitRepo);

    GitRepo findById(String id);

    Page<GitRepo> findAll(Pageable pageable);

    void deleteRepo(String id);

    boolean gitCodeInit(Pageable pageable);

    List<GitTree> listByMode(String repoId, String mode);

    RepoTree getRepoFolder(String repoId);

}
