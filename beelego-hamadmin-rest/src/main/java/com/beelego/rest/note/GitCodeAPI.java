package com.beelego.rest.note;

import com.beelego.entity.primary.note.GitRepo;
import com.beelego.github.GitRepoService;
import com.beelego.global.base.BaseAPI;
import com.beelego.global.result.ApiResult;
import com.beelego.payload.CreateSource;
import com.beelego.repository.primary.note.GitRepoRepository;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

/**
 * @author : hama
 * @since : created in  2018/8/17
 */
@Slf4j
@Api("源码api")
@RestController
@RequestMapping(value = "/gitcode")
public class GitCodeAPI extends BaseAPI {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    GitRepoService gitRepoService;

    @ApiOperation("代码仓库新增")
    @RequestMapping(method = RequestMethod.POST, value = "/repo")
    public Object repo(@Valid @RequestBody @ApiParam(name = "gitRepo", value = "json格式", required = true) GitRepo gitRepo,
                       BindingResult bindingResult) {
        ApiResult checks = checkBindingResult(bindingResult);
        if (!checks.isSuccess()) {
            return checks;
        }
        return gitRepoService.gitRepoInit(gitRepo);
    }


    @ApiOperation("代码仓库列表")
    @RequestMapping(value = "/repo", method = RequestMethod.GET)
    public Object repo(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
                                     @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        log.info("pageNo is :{}, pageSize is :{}", pageNo, pageSize);
        return gitRepoService.findAll(PageRequest.of(pageNo - 1, pageSize));
    }

    @ApiOperation("代码仓库删除")
    @RequestMapping(value = "/repo/{repoId}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable String repoId) {
        gitRepoService.deleteRepo(repoId);
        return repoId;
    }

    @ApiOperation("储存仓库代码及解析")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "repoId", paramType = "path", defaultValue = "C21D7611-523D-4904-820C-BE27C8FE2D1A")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/treeInit/{repoId}")
    public Object gitTreeInit(@PathVariable String repoId) {
        return gitRepoService.findById(repoId);
    }

}
