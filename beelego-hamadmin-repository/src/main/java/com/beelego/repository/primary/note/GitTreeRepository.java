package com.beelego.repository.primary.note;

import com.beelego.entity.primary.note.GitTree;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : hama
 * @since : created in  2018/8/17
 */
@Repository
public interface GitTreeRepository extends JpaRepository<GitTree, String> {

    List<GitTree> findAllByGitRepoIsNull(Pageable pageable);

    @Query("select gt from GitTree gt where gt.gitRepo.id = :repoId")
    List<GitTree> listRepoTree(@Param("repoId") String repoId);

    @Query("select gt from GitTree  gt where gt.gitRepo.id = :repoId and gt.mode = :mode")
    List<GitTree> listByMode(@Param("repoId") String repoId, @Param("mode") String mode);

    @Query("select gt from GitTree  gt where gt.gitRepo.id = :repoId order by gt.path asc")
    List<GitTree> listByRepoSorted(@Param("repoId") String repoId);
}
