package cc.moecraft.fun.autogitfiti.pattern;

import lombok.Data;

import java.io.*;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

/**
 * 此类由 Hykilpikonna 在 2018/09/15 创建!
 * Created by Hykilpikonna on 2018/09/15!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@Data
public class Pattern
{
    private Map<Character, Integer> charValueMap = new HashMap<>();
    private int[][] patternArray = new int[53][7]; // [x][y]

    public Pattern(File file) throws IOException
    {
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
    }

}
