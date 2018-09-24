package com.beelego.github.impl;

import com.beelego.entity.primary.note.GitRepo;
import com.beelego.enums.ErrorCodeEnum;
import com.beelego.github.GitRepoService;
import com.beelego.global.exception.ApiException;
import com.beelego.global.result.ApiResult;
import com.beelego.repository.primary.note.GitRepoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

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

    @Override
    public ApiResult gitRepoInit(GitRepo gitRepo) {
        String repoUserName = gitRepo.getRepoUserName();
        String repoName = gitRepo.getRepoName();
        List<GitRepo> gitRepoList = gitRepoRepository.findAllByRepoUserNameAndRepoName(repoUserName, repoName);
        if(gitRepoList.size()>0) throw new ApiException(ErrorCodeEnum.RESOURCE_ALREADY_EXIST);
        String url = "https://api.github.com/repos/" + gitRepo.getRepoUserName() + "/" + gitRepo.getRepoName() + "/git/trees/master?recursive=1&_=" + System.currentTimeMillis();
        log.info("git url: {}", url);
        try {
            ResponseEntity<GitRepo> responseEntity = restTemplate.getForEntity(url, GitRepo.class);
            log.info("response status is [{}]", responseEntity.getStatusCode());
            gitRepo = responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            if(HttpStatus.NOT_FOUND.equals(e.getStatusCode())){
                throw new ApiException(ErrorCodeEnum.RESOURCE_NOT_EXIST);
            }
        }
        gitRepo.setRepoUserName(repoUserName);
        gitRepo.setRepoName(repoName);
        // without repo_id
        gitRepoRepository.save(gitRepo);
        return ApiResult.ok(gitRepo);
    }

    @Override
    public GitRepo findById(String id) {
        Optional<GitRepo> gitRepo = gitRepoRepository.findById(id);
        if(!gitRepo.isPresent()) throw new ApiException(ErrorCodeEnum.RESOURCE_NOT_EXIST);
        return gitRepo.get();
    }

    @Override
    public Page<GitRepo> findAll(Pageable pageable) {
        return gitRepoRepository.findAll(pageable);
    }

    @Override
    public void deleteRepo(String id) {
        gitRepoRepository.deleteById(id);
    }
}
