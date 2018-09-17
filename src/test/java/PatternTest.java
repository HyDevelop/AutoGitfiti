import cc.moecraft.fun.autogitfiti.pattern.Pattern;

import java.io.File;
import java.io.IOException;

/**
 * 此类由 Hykilpikonna 在 2018/09/16 创建!
 * Created by Hykilpikonna on 2018/09/16!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class PatternTest
{
    public static void main(String[] args) throws IOException
    {
        Pattern pattern = new Pattern(new File("test.pattern"));
        System.out.println(pattern);
    }
}
