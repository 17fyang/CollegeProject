package yo.ask.sz;

import other.RedisBoolRunnable;
import util.JedisUtil;
import yo.keyword.PdfToKeyWord;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/27 11:28
 * @Description:
 */
public class SZCalculateRunnable extends RedisBoolRunnable {
    public static final String PREFIX = "cal_sz:chuang";
    public static final String REDIS_PREFIX = "dl_sz:zhong";
    private SZObject szObject;

    public SZCalculateRunnable(SZObject szObject) {
        super(PREFIX, SZDownloadRunnable.CONTENT_PREFIX + szObject.getContentPdf(), "");
        this.szObject = szObject;
    }

    @Override
    protected void runTask() throws Exception {
        String okKey = RedisBoolRunnable.getOkKey(REDIS_PREFIX, getKey());

        if (JedisUtil.hasKey(okKey)) {
            String filePath = JedisUtil.get(okKey);
            int keywordTime = PdfToKeyWord.keywordTimes(filePath);
            szObject.setKeyWordTimes(keywordTime);
            setValue(String.valueOf(keywordTime));
        }
    }
}
