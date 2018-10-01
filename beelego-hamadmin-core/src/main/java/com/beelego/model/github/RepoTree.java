package com.beelego.model.github;

import com.beelego.global.base.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : hama
 * @since : created in  2018/9/27
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepoTree extends BaseObject {

    private String id;

    private String path;

    private List<RepoTree> children;

    private String mode;

    public RepoTree(String id, String label, String mode) {
        this.id = id;
        this.path = label;
        this.children = new ArrayList<>();
        this.mode = mode;
    }

    public String getLabel() {
        return this.mode.equals("040000") ? this.path : this.path.substring(this.path.lastIndexOf("/") + 1);
    }


    /**
     *
     */
    @Deprecated
    public void display() {
        for (RepoTree child : this.getChildren()) {
            log.info("{}========{}", child.getPath(), child.getChildren().size());
            if (null != child.getChildren() && child.getChildren().size() > 0) {
                for (RepoTree childChild : child.getChildren()) {
                    childChild.display();
                }
            }
        }
    }

    public void sort() {
        this.getChildren().sort((RepoTree o1, RepoTree o2) -> {
            boolean mode1 = o1.mode.equals("040000");
            boolean mode2 = o2.mode.equals("040000");
            if (!mode1 && !mode2) {
                return 0;
            }

            if (mode1 && mode2) {
                return o1.getPath().compareTo(o2.getPath());
            }
            return mode1 ? -1 : 1;
        });
    }


    // 递归查询是否有父节点
    public RepoTree fill(RepoTree son, RepoTree root) {
        log.info("filling root is [{}]=======>{}", root.getPath(), son.getPath());
        String sub = son.getPath();
        put(son, root);
        return root;
    }

    /**
     * 首先，需要是文件夹
     *
     * @param target
     * @param folder
     * @return
     */
    private boolean put(RepoTree target, RepoTree folder) {
        // 不是文件则返回失败
        if (!"040000".equals(folder.getMode())) {
            return false;
        }

        // target是不会重复的，所以不用判断是否存在
        if (!hasFolder(folder, target.getPath())) {
            // 没有下级，则直接添加
            folder.getChildren().add(target);
            return true;
        }

        for (RepoTree child : folder.getChildren()) {

            if ("040000".equals(child.getMode())
                    && target.getPath().indexOf(child.getPath()) > -1) {
                boolean result = put(target, child);
                if (result) {
                    break;
                }
            }
        }
        return true;
    }

    /**
     * 是否有目标文件夹
     *
     * @param folder
     * @return
     */
    public boolean hasFolder(RepoTree folder, String target) {
        boolean hasFolder = false;
        if (null == folder.getChildren() || folder.getChildren().size() == 0) {
            return hasFolder;
        }
        for (RepoTree child : folder.getChildren()) {
            if ("040000".equals(child.getMode())
                    && target.indexOf(child.getPath()) > -1) {
                hasFolder = true;
            }
        }
        return hasFolder;
    }

    public void trim() {
        log.info("trim in========");
        for (int i = 0; i < this.children.size(); i++) {
            RepoTree child = this.children.get(i);
            log.info("游标位于节点：{}", child.getPath());
            if (child.folderInChild()) {
                log.info("游标位于节点：{}======>其下存在目录", child.getPath());
                if (child.getChildren().size()>1){
                    log.info("游标位于节点：{}======>其下存在目录，目录数量大于1，节点自己去trim", child.getPath());
                    child.trim();
                } else {
                    log.info("游标位于节点：{}======>其下存在一个目录，准备替换！", child.getPath());
                    RepoTree replace = child.getChildren().get(0);
                    if(child.canReplace()){
                        log.info("游标位于节点：{}======>其下存在一个目录，可以替换！！替换调！！！", child.getPath());
                        this.children.remove(child);
                        this.children.add(i, replace);
                    } else {
                        log.info("游标位于节点：{}======>其下存在一个目录，[不]可以替换", child.getPath());
                        replace.trim();
                    }

//
//                    if(replace.folderInChild()){
//                        replace.trim();
//                    }
                }

            } else {
                log.info("游标位于节点：{}======>没有目录，游标赶往下一节点", child.getPath());
                continue;
            }
        }
        log.info("trim out========");
    }

    public boolean folderInChild() {
        int folders = 0;
        for (RepoTree child : this.getChildren()) {
            if (child.getMode().equals("040000")) {
                folders++;
            }
        }
        return folders > 0;
    }

    public boolean canReplace() {
        log.info("{}", null != this.getChildren());
        log.info("{}", this.getChildren().size() == 1);
        log.info("{}", this.getChildren().get(0).getMode().equals("040000"));
        if(null != this.getChildren() && this.getChildren().size() == 1 && this.getChildren().get(0).getMode().equals("040000")){
            return false;
        }
        return true;
    }


}
