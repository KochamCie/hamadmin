package com.beelego.github.impl;

import com.beelego.entity.primary.note.GitRepo;
import com.beelego.entity.primary.note.GitTree;
import com.beelego.enums.ErrorCodeEnum;
import com.beelego.github.GitRepoService;
import com.beelego.global.exception.ApiException;
import com.beelego.global.result.ApiResult;
import com.beelego.model.github.RepoTree;
import com.beelego.repository.primary.note.GitRepoRepository;
import com.beelego.repository.primary.note.GitTreeRepository;
import javaslang.collection.Array;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : hama
 * @since : created in  2018/9/20
 */
@Slf4j
@Service("gitRepoService")
@Transactional(rollbackFor = Exception.class)
public class GitRepoServiceImpl implements GitRepoService {

    @Autowired
    GitRepoRepository gitRepoRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    GitTreeRepository gitTreeRepository;

    private static Map<String, GitRepo> repoMap = new HashMap<>();

    @Override
    public ApiResult gitRepoInit(GitRepo gitRepo) {
        String repoUserName = gitRepo.getRepoUserName();
        String repoName = gitRepo.getRepoName();
        List<GitRepo> gitRepoList = gitRepoRepository.findAllByRepoUserNameAndRepoName(repoUserName, repoName);
        if (gitRepoList.size() > 0) throw new ApiException(ErrorCodeEnum.RESOURCE_ALREADY_EXIST);
        String url = "https://api.github.com/repos/" + gitRepo.getRepoUserName() + "/" + gitRepo.getRepoName() + "/git/trees/master?recursive=1&_=" + System.currentTimeMillis();
        log.info("git url: {}", url);
        try {
            ResponseEntity<GitRepo> responseEntity = restTemplate.getForEntity(url, GitRepo.class);
            log.info("response status is [{}]", responseEntity.getStatusCode());
            gitRepo = responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
                throw new ApiException(ErrorCodeEnum.RESOURCE_NOT_EXIST);
            }
        }
        gitRepo.setTreeSize(gitRepo.getTree().size());
        gitRepo.setRepoUserName(repoUserName);
        gitRepo.setRepoName(repoName);
        // without repo_id
        gitRepoRepository.save(gitRepo);
        return ApiResult.ok(gitRepo);
    }

    @Override
    public GitRepo findById(String id) {
        Optional<GitRepo> gitRepo = gitRepoRepository.findById(id);
        if (!gitRepo.isPresent()) throw new ApiException(ErrorCodeEnum.RESOURCE_NOT_EXIST);
        return gitRepo.get();
    }

    @Override
    public Page<GitRepo> findAll(Pageable pageable) {
        return gitRepoRepository.findAll(pageable);
    }

    @Override
    public void deleteRepo(String id) {
        GitRepo gitRepo = findById(id);
        gitRepoRepository.delete(gitRepo);
    }

    @Override
    public boolean gitCodeInit(Pageable pageable) {
        List<GitTree> gitTreeList = gitTreeRepository.findAllByGitRepoIsNull(pageable);

        gitTreeList.forEach(gitTree -> {
            String url = gitTree.getUrl();
            String match = firstMatch(url, "/repos/(.*?)/git/");
            if (repoMap.containsKey(match)) {
                log.debug("repoMap has this repo ================{}", match);
                gitTree.setGitRepo(repoMap.get(match));
                return;
            }
            log.info("find repo by match ================{}", match);
            String[] repoInfo = match.split("/");
            GitRepo gitRepo = gitRepoRepository.findByRepoUserNameAndRepoName(repoInfo[0], repoInfo[1]);
            if (null != gitRepo) {
                gitTree.setGitRepo(gitRepo);
                repoMap.put(match, gitRepo);
            }
        });
        gitTreeRepository.saveAll(gitTreeList);
        return false;
    }

    @Override
    public List<GitTree> listByMode(String repoId, String mode) {
        GitRepo gitRepo = findById(repoId);
        List<GitTree> gitTreeList;
        if (StringUtils.isBlank(mode)) {
            gitTreeList = gitTreeRepository.listRepoTree(repoId);
        } else {
            gitTreeList = gitTreeRepository.listByMode(repoId, mode);
        }
        return gitTreeList;
    }

    @Override
    public RepoTree getRepoFolder(String repoId) {
        log.info("getRepoFolder [{}]=========start", repoId);
        long start = System.currentTimeMillis();

        GitRepo gitRepo = findById(repoId);
        List<GitTree> gitTreeList = gitTreeRepository.listByRepoSorted(repoId);
        RepoTree repoTree = new RepoTree(gitRepo.getId(), gitRepo.getRepoName(), "040000");
        int i = 1;
        for (GitTree gitTree : gitTreeList) {
            if (i == 30) {
                //break;
            }
            String path = gitTree.getPath();
            log.info("查找[{}]的父节点=========start", path);
            repoTree = repoTree.fill(new RepoTree(gitTree.getId(), gitTree.getPath(), gitTree.getMode()), repoTree);
            //log.info("查找[{}]的父节点=========end，父节点是：[{}]", path, parent);
//            if(null == parent){
//                repoTree.getChildren().add(new RepoTree(gitTree.getId(), gitTree.getPath()));
//            } else {
//                repoTree.add(parent, new RepoTree(gitTree.getId(), gitTree.getPath()));
//            }
            i++;
        }
        log.info("getRepoFolder [{}]=========end cost {} ms", repoId, System.currentTimeMillis() - start);

        return repoTree;
    }


    /**
     * @param soap
     * @param regex
     * @return
     */
    public String firstMatch(String soap, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            return m.group(1);
        }
        return "";
    }


}
