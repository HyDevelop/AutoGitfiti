package cc.moecraft.fun.autogitfiti.git;

import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static cc.moecraft.fun.autogitfiti.git.GitUtils.Cache.*;

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
     * 缓存类
     */
    public static class Cache
    {
        public static String authorName = "";
        public static String authorEmail = "";
        /**
         * Author信息缓存
         */
        private static final Map<Date, PersonIdent> author = new HashMap<>();

        /**
         * 因为每个Author信息都包含日期,
         * 所以按日期缓存好,
         * 这样如果在一个格子提交50次的话就不用重新构造50个新对象了
         * @param date 日期
         * @return Author
         */
        public static PersonIdent getAuthor(Date date)
        {
            if (author.containsKey(date)) return author.get(date);
            author.put(date, new PersonIdent(authorName, authorEmail, date, TimeZone.getTimeZone("UTC")));
            return getAuthor(date);
        }

        /**
         * 提前计算好格子的日期
         */
        private static final ContributionChart contributionChart = new ContributionChart();
    }

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
        push.call();
    }

    public static void create(FileRepository repo) throws IOException
    {
        if (repo.getConfig().getFile().exists()) return;
        repo.create();
    }
}
