package com.beelego.repository.primary.note;

import com.beelego.entity.primary.note.GitRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : hama
 * @since : created in  2018/8/17
 */
@Component
public interface GitRepoRepository extends JpaRepository<GitRepo, String> {

    List<GitRepo> findAllByRepoUserNameAndRepoName(@Param("repoUserName") String repoUserName, @Param("repoName") String repoName);

}
