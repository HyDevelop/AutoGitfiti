package cc.moecraft.fun.autogitfiti.git;

import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.IOException;
import java.util.*;

import static cc.moecraft.fun.autogitfiti.git.Cache.*;

/**
 * 此类由 Hykilpikonna 在 2018/09/16 创建!
 * Created by Hykilpikonna on 2018/09/16!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@SuppressWarnings("WeakerAccess")
public class GitUtils
{

    /**
     * 获取格子的日期
     * @param x X坐标
     * @param y Y坐标
     * @return 日期
     */
    public static Date getDate(int x, int y)
    {
        return Cache.contributionChart.getDateMap()[x][y];
    }

    /**
     * 提交一次
     * @param git Git对象
     * @param date 日期
     */
    public static void commit(Git git, Date date) throws GitAPIException
    {
        CommitCommand commit = git.commit();
        commit.setAuthor(getAuthor(date));
        commit.setMessage("");
        commit.call();
    }

    /**
     * 提交很多次
     * @param git Git对象
     * @param date 日期
     * @param times 多少次
     */
    public static void commit(Git git, Date date, int times)
    {
        for (int i = 0; i < times; i++)
        {
            try
            {
                commit(git, date);
            }
            catch (GitAPIException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void reset(Git git) throws GitAPIException
    {
        String branchName = "TEMP_BRANCH_" + System.currentTimeMillis();

        CheckoutCommand checkout = git.checkout();
        checkout.setOrphan(true);
        checkout.setName(branchName);
        checkout.call();

        commit(git, new Calendar.Builder().setDate(2001, 1, 1).build().getTime());

        DeleteBranchCommand delete = git.branchDelete();
        delete.setBranchNames("master");
        delete.setForce(true);
        delete.call();

        RenameBranchCommand rename = git.branchRename();
        rename.setOldName(branchName);
        rename.setNewName("master");
        rename.call();
    }

    public static void push(Git git) throws GitAPIException
    {
        PushCommand push = git.push();
        push.setForce(true);
        push.setPushAll();
        push.setRemote(remote);
        push.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));
        push.call();
    }

    public static void create(FileRepository repo) throws IOException
    {
        if (repo.getConfig().getFile().exists()) return;
        repo.create();
    }
}
