package cc.moecraft.fun.autogitfiti;

import cc.moecraft.fun.autogitfiti.git.GitUtils;
import cc.moecraft.fun.autogitfiti.git.Renderer;
import cc.moecraft.fun.autogitfiti.pattern.Pattern;
import cc.moecraft.utils.FileUtils;
import cc.moecraft.utils.cli.Args;
import cc.moecraft.utils.cli.ArgsUtils;
import cc.moecraft.utils.cli.ResourceUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;

import java.io.File;
import java.util.Map;

import static cc.moecraft.fun.autogitfiti.git.GitUtils.Cache.*;
import static cc.moecraft.fun.autogitfiti.git.GitUtils.*;
import static cc.moecraft.fun.autogitfiti.git.Renderer.render;
import static cc.moecraft.fun.autogitfiti.utils.JarUtils.getJarfileName;

/**
 * 此类由 Hykilpikonna 在 2018/09/21 创建!
 * Created by Hykilpikonna on 2018/09/21!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class Main
{
    public static void main(String[] rawArgs) throws Exception
    {
        Args args = ArgsUtils.parse(rawArgs);

        if (args.getOperations().size() == 0)
        {
            printHelp();
            return;
        }

        String text = run(args);

        if (text != null) System.out.println(text);
    }

    private static String run(Args args) throws Exception
    {
        Map<String, String> options = args.getOptions();
    }
}
