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

        long lineCount = 0;
        State state = State.NORMAL;
        String rawLine;
        String line;

        while ((rawLine = reader.readLine()) != null)
        {
            lineCount ++;
            line = PatternReader.removeComments(rawLine);
            String lowLine = line.toLowerCase();

            if (lowLine.startsWith("[start]"))
            {
                if (state == State.STARTED) error(lineCount, line, "Invalid: Already had a [start] that didn't end yet...");
                state = State.STARTED;
                continue;
            }
            if (lowLine.startsWith("[end]"))
            {
                break;
            }

            line = line.replace(" ", "");

            if (state == State.NORMAL)
            {
                if (lowLine.startsWith("def "))
                {

                }
            }
            else // STARTED
            {

            }
        }

        fileReader.close();
    }

    private enum State
    {
        NORMAL, STARTED
    }

    private static void error(long lineCount, String line, String message)
    {
        throw new RuntimeException(message + "\n(At line: " + lineCount + " - " + line + ")");
    }
}
