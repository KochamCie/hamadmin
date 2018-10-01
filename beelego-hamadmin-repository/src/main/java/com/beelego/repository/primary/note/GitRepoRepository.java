package com.beelego.repository.primary.note;

import com.beelego.entity.primary.note.GitRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : hama
 * @since : created in  2018/8/17
 */
@Repository
public interface GitRepoRepository extends JpaRepository<GitRepo, String> {

    List<GitRepo> findAllByRepoUserNameAndRepoName(@Param("repoUserName") String repoUserName, @Param("repoName") String repoName);

    GitRepo findByRepoUserNameAndRepoName(@Param("repoUserName") String repoUserName, @Param("repoName") String repoName);

}
