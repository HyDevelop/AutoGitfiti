package cc.moecraft.fun.autogitfiti.git;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;

/**
 * 此类由 Hykilpikonna 在 2018/09/16 创建!
 * Created by Hykilpikonna on 2018/09/16!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@Data
public class ContributionChart
{
    private Date[][] dateMap = new Date[53][7];
    private ContributionChart(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 重置到周日 (表的最下面那一行)
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY)
            calendar.add(Calendar.DAY_OF_WEEK, 1);

        for (int x = dateMap.length - 1; x >= 0; x--)
        {
            for (int y = dateMap[x].length - 1; y >= 0; y--)
            {
                dateMap[x][y] = calendar.getTime();
                calendar.add(Calendar.DAY_OF_YEAR, -1);
            }
        }
    }
}
