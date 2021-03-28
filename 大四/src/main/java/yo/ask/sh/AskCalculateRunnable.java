package yo.ask.sh;

import other.RedisBoolRunnable;
import util.JedisUtil;
import yo.keyword.PdfToKeyWord;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/26 21:19
 * @Description:
 */
public class AskCalculateRunnable extends RedisBoolRunnable {
    public static final String PREFIX = "cal_sh";
    private AskSHDo askSHDo;

    public AskCalculateRunnable(AskSHDo askSHDo) {
        super(PREFIX, askSHDo.getPdf(), "");
        this.askSHDo = askSHDo;
    }

    @Override
    protected void runTask() throws Exception {
        String okKey = RedisBoolRunnable.getOkKey(AshSHRunnable.PREFIX, askSHDo.getPdf());

        if (JedisUtil.hasKey(okKey)) {
            String filePath = JedisUtil.get(okKey);
            int keywordTime = PdfToKeyWord.keywordTimes(filePath);
            askSHDo.setKeywords(keywordTime);
            setValue(String.valueOf(keywordTime));
        }
    }
}
