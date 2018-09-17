package cc.moecraft.fun.autogitfiti.git;

import org.eclipse.jgit.lib.PersonIdent;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * 缓存类
 */
public class Cache
{
    public static String authorName = "";
    public static String authorEmail = "";
    public static String remote = null;
    public static String username = null;
    public static String password = null;

    public static void init(String remote, String username, String password)
    {
        Cache.remote = remote;
        Cache.username = username;
        Cache.password = password;
    }

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
    public static final ContributionChart contributionChart = new ContributionChart();
}
