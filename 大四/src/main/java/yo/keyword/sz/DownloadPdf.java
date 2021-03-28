package yo.keyword.sz;

import com.alibaba.excel.EasyExcel;
import other.RedisBoolRunnable;
import util.DownloadUtil;
import util.ThreadUtil;

import java.io.File;
import java.util.List;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/26 13:28
 * @Description:
 */
public class DownloadPdf {
    public static final String EXCEL_LOCATION = "C:\\Users\\Administrator\\Desktop\\yo\\深证-问询函.xlsx";
    public static final String FILE_LOCATION = "C:\\Users\\Administrator\\Desktop\\yo\\sz_file";
    public static final ThreadUtil async = ThreadUtil.init();

    public static void main(String[] args) {
        List<SZObject> objects = EasyExcel.read(EXCEL_LOCATION).head(SZObject.class).sheet("17-19年剔除st").doReadSync();

        for (SZObject shObject : objects) {
            async.run(new DealSZ(shObject));
        }
    }
}

class DealSZ extends RedisBoolRunnable {
    private SZObject szObject;

    public DealSZ(SZObject object) {
        super("sz", object.getPdf_location(), "");
        setValue(DownloadPdf.FILE_LOCATION + "\\" + szObject.getPdf_location().substring(szObject.getPdf_location().lastIndexOf("/") + 1));
        szObject = object;
    }

    @Override
    protected void runTask() throws Exception {
        DownloadUtil.download(szObject.getPdf_location(), new File(getValue()));
    }
}

