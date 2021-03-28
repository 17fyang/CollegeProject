package yo.keyword;

import util.PDFUtil;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/26 14:24
 * @Description:
 */
public class PdfToKeyWord {
    public static final String[] keywords = new String[]{"诉讼", "仲裁", "或有负债", "违约", "冻结", "舞弊", "风险警示", "汇率风险", "投资风险", "偿债能力", "经营风险", "公司到期承兑", "公司到期兑付", "结算风险", "流动风险", "应收账款回收风险", "内部控制", "并购重组", "研究开发", "收入确认", "利润波动", "关联交易", "资金占用" };

    public static int keywordTimes(String path) {
        String text = PDFUtil.readText(path);
//        System.out.println(text);
        int times = 0;

        for (String keyword : keywords) {
            int index = 0;
            while ((index = text.indexOf(keyword, index)) != -1) {
//                System.out.println(index + " " + keyword);
                index++;
                times++;
            }
        }
        return times;
    }

}
