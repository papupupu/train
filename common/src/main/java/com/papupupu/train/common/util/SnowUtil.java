package com.papupupu.train.common.util;

import cn.hutool.core.util.IdUtil;

public class SnowUtil {
    private static long dataCenyerId = 1; //数据中心
    private static long workId = 1; //机器编号

    public static long getSnowflakeNextId(){
        return IdUtil.getSnowflake(workId, dataCenyerId).nextId();
    }

    public static String getSnowflakeNextIdStr(){
        return IdUtil.getSnowflake(workId, dataCenyerId).nextIdStr();
    }
}
