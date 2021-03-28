package other;

import util.JedisUtil;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/26 20:39
 * @Description:
 */
public abstract class RedisBoolRunnable implements Runnable {
    public static final String OK_TEMPLATE = "%s:ok:%s";
    public static final String FAIL_TEMPLATE = "%s:fail:%s";

    private String prefix;
    private String key;
    private String value;
    private boolean isOutput = true;

    public RedisBoolRunnable(String prefix, String key, String value) {
        this.prefix = prefix;
        this.key = key;
        this.value = value;
    }

    @Override
    public final void run() {
        String okKey = getOkKey(prefix, key);
        String failKey = getFailKey(prefix, key);

        try {
            if (JedisUtil.hasKey(okKey)) return;
            runTask();
            JedisUtil.set(okKey, value);
            if (this.isOutput) System.out.println(okKey);
        } catch (Exception e) {
            JedisUtil.set(failKey, value);
            if (this.isOutput) System.out.println(failKey);
        }
    }

    public static String getOkKey(String prefix, String key) {
        return String.format(OK_TEMPLATE, prefix, key);
    }

    public static String getFailKey(String prefix, String key) {
        return String.format(FAIL_TEMPLATE, prefix, key);
    }


    public void setOutput(boolean isOutput) {
        this.isOutput = isOutput;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    protected abstract void runTask() throws Exception;


}
