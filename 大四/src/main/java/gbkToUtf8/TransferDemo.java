package gbkToUtf8;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/14 16:08
 * @Description:
 */
public class TransferDemo {
    public static void main(String[] args) throws IOException {
        String srcDirPath = "E:\\IDEA\\workSpace\\CollegeProject";

        //获取所有文件      extensions:new String[]{"java"} //仅获取java文件
        Collection<File> javaGbkFileCol = FileUtils.listFiles(new File(srcDirPath), null, true);

        for (File javaGbkFile : javaGbkFileCol) {
            String filePath = javaGbkFile.getAbsolutePath();
            if (!filePath.endsWith("java")) continue;

            //UTF8格式文件路径
            System.out.println(filePath);
            //使用GBK读取数据，然后用UTF-8写入数据
            FileUtils.writeLines(new File(filePath), "UTF-8", FileUtils.readLines(javaGbkFile, "GBK"));
        }
    }
}
