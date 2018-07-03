package leetcode;

import java.io.*;

/**
 * Created by tiang on 2018/6/22.
 * 统计文件行数
 */
public class CodeLines {

    public int getLines(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        return getLines(file);
    }

    /**
     * 深度优先遍历
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    private int getLines(File file) throws FileNotFoundException {
        int result = 0;
        if(file.isDirectory()){     //如果是文件夹，遍历下层文件
            File[] files = file.listFiles();
            for(File f : files){
                result += getLines(f);
            }
        }else{
            // 如果是文件，直接统计文件行数
            BufferedReader reader = new BufferedReader(new FileReader(file));
            result += reader.lines().count();
        }
        return result;
    }
}
