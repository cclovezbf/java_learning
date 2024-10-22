package easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.util.ListUtils;
import lombok.var;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EasyExcelDemo {
    private static final Logger log = LoggerFactory.getLogger(EasyExcelDemo.class);
    private File file;

    /**
     * 单次缓存的数据量
     */
    public static final int BATCH_COUNT = 200;
    /**
     * 临时存储
     */
    private List<HashMap<String, Object>> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    @Before
    public void before() {
        String path = "D:\\install\\code\\github\\java_learning\\java_utils\\src\\main\\resources\\EPO验收数据交付件.xlsx";
        file = new File(path);
    }
    @Test
    public void readConfig() {
        ExcelReader build = EasyExcel.read(file, new PageReadListener<HashMap<String, Object>>(lists -> {
            System.out.println("read page cnt=" + lists.size());
        })).build();
        ReadSheet readSheet = EasyExcel.readSheet(1).headRowNumber(0).build();
        build.read(readSheet);
    }

    @Test
    public void readSheet2() {
        ExcelReader build = EasyExcel.read(file, new PageReadListener<HashMap<String, Object>>(lists -> {
            System.out.println("read page cnt=" + lists.size());
        })).build();
        ReadSheet readSheet = EasyExcel.readSheet(1).build();
        build.read(readSheet);
    }

    @Test
    public void readPage() {
        EasyExcel.read(file, new PageReadListener<HashMap<String, Object>>(lists -> {
            System.out.println("read page cnt=" + lists.size());
        })).sheet().doRead();
    }

    @Test
    public void readCustomPage() {
        EasyExcel.read(file, new ReadListener<HashMap<String, Object>>() {
            @Override
            public void invoke(HashMap<String, Object> data, AnalysisContext analysisContext) {
                cachedDataList.add(data);
                if (cachedDataList.size() >= BATCH_COUNT) {
                    saveData();
                    // 存储完成清理 list
                    cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                saveData();
            }

            private void saveData() {
                log.info("{}条数据，开始存储数据库！", cachedDataList.size());
                log.info("存储数据库成功！");
            }
        }).sheet().doRead();
    }

    @Test
    public void readAll() {
        EasyExcel.read(file, new ReadListener<HashMap<String, Object>>() {
            @Override
            public void invoke(HashMap<String, Object> o, AnalysisContext analysisContext) {
                System.out.println(o);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).doReadAll();
    }
}
