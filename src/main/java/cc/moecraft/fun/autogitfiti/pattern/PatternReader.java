package cc.moecraft.fun.autogitfiti.pattern;

/**
 * 此类由 Hykilpikonna 在 2018/09/15 创建!
 * Created by Hykilpikonna on 2018/09/15!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
class PatternReader
{
    static String removeComments(String original)
    {
        int offset = original.indexOf("//");
        return offset != -1 ? original.substring(0, offset) : original;
    }
}
