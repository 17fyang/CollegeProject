package yo.ask.sh;

import other.RedisBoolRunnable;
import util.DownloadUtil;
import util.FileUtil;

import java.io.File;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/26 20:48
 * @Description:
 */
public class AshSHRunnable extends RedisBoolRunnable {
    public static final String PDF_TEMPLATE = "C:\\Users\\Administrator\\Desktop\\yo\\sh\\pdf";
    public static final String PREFIX = "ask_sh";
    private AskSHDo askSHDo;

    public AshSHRunnable(AskSHDo askSHDo) {
        super(PREFIX, askSHDo.getPdf(), "");
        String fileName = askSHDo.getPdf().substring(askSHDo.getPdf().lastIndexOf("/") + 1);
        this.setValue(FileUtil.mergeDir(PDF_TEMPLATE, fileName));
        this.askSHDo = askSHDo;
    }

    @Override
    protected void runTask() throws Exception {
        DownloadUtil.download(askSHDo.getPdf(), new File(getValue()));
    }
}
