package com.hqw.websocket.kettle;
import cn.hutool.core.io.FileUtil;
import com.hqw.websocket.DateUtils;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class KattleTest {
    public static void main(String[] args) throws Exception {

        try {
            //        File file = FileUtil.file("kettle/patient.ktr");
//            String path = "F:front\\kettle\\"+"2001.ktr";
            String kettlePath = "kettle\\";
            File file = FileUtil.file(kettlePath + "3002.ktr");
            String path = file.getPath();
            System.out.println(path);

            // 初始化
            KettleEnvironment.init();

            // 加载文件
            TransMeta transMeta = new TransMeta(path);
            Trans trans = new Trans(transMeta);

            Date date = DateUtils.parse2Date("20150825", DateUtils.FULL_DATE_STRING_1);
            Date startTime = DateUtils.getDate(date, "00:00:00");
            Date endTime = DateUtils.getDate(date, "23:59:59");
            String s1 = DateUtils.date2String(startTime, DateUtils.FULL_DATE_STRING_4);
            String s2 = DateUtils.date2String(endTime, DateUtils.FULL_DATE_STRING_4);
            Map<String, Object> map = new HashMap<>();
            map.put("startTime", s1); // 开始时间 2015-05-25 00:00:00
            map.put("endTime", s2);  // 结束时间

            // 放入参数，这里其实可以从数据库中取到，如果没有参数，可以把这步忽略
//        trans.setParameterValue("ehrDate", "'2015-06-25'");
//        trans.setVariable("ehrDate", "'2015-06-25'");
            Set<String> sets = map.keySet();
            for (String key : sets) {
                Object value = map.get(key);
                trans.setVariable(key, "'" + value.toString() + "'");
            }
//        trans.execute(null);
            trans.prepareExecution(null);
            trans.startThreads();
            // 等待执行完毕
            trans.waitUntilFinished();


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("2222222222");
        }


    }
}
