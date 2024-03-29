package cc.moecraft.fun.autogitfiti.git;

import cc.moecraft.fun.autogitfiti.pattern.Pattern;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;

import java.util.Date;

/**
 * 此类由 Hykilpikonna 在 2018/09/16 创建!
 * Created by Hykilpikonna on 2018/09/16!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class Renderer
{
    public static void render(Pattern pattern, Repository repository)
    {
        Git git = new Git(repository);

        for (int x = 0; x < pattern.getPatternArray().length; x++) // 每一列
        {
            int[] column = pattern.getPatternArray()[x];
            for (int y = 0; y < column.length; y++) // 每一个数字
            {
                Date date = GitUtils.getDate(x, y);
                GitUtils.commit(git, date, column[y]);
            }
        }
    }
}
