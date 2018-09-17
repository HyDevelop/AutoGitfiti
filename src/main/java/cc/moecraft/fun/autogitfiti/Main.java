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

        if (!options.containsKey("remote")) return "Error: Push remote not defined.";
        if (!options.containsKey("u")) return "Error: Username not defined.";
        if (!options.containsKey("p")) return "Error: Password not defined.";

        init(options.get("remote"), options.get("u"), options.get("p"));

        authorEmail = options.getOrDefault("email", authorEmail);
        authorName = options.getOrDefault("name", authorName);

        File repoBaseDir = new File(options.getOrDefault("repo", "./.repo/"));

        File repoDir = new File(repoBaseDir, ".git/");
        FileRepository repository = new FileRepository(repoDir);
        Git git = new Git(repository);

        File patternFile = new File(repoBaseDir, options.getOrDefault("pattern", "../default.pattern"));
        if (!patternFile.exists()) FileUtils.copyResource(Main.class, "default.pattern", patternFile);
        Pattern pattern = new Pattern(patternFile);

        switch (args.getOperations().get(0))
        {
            case "help": printHelp(); return null;
            case "create": create(repository); return "Done.";
            case "reset": reset(git); return "Done.";
            case "push": push(git); return "Done.";
            case "draw": render(pattern, repository); return "Done.";
            case "run":
            {
                create(repository);
                System.out.println("Create Done.");
                reset(git);
                System.out.println("Reset Done.");
                render(pattern, repository);
                System.out.println("Draw Done.");
                push(git);
                System.out.println("Push Done.");
                return "All Done.";
            }
        }
        return "Unidentified command: " + args.getOperations().get(0);
    }

    private static void printHelp()
    {
        ResourceUtils.printResource(System.out::println, "help.txt", "jarfile.name", getJarfileName());
    }
}
