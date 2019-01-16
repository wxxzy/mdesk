package com.mungo.distributedlockzk;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/1/7 11:03
 */
@Component
public class ApplicationContextHelper  implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //SpringHelper.setApplicationContext(applicationContext);
        //InitData.start();
        //new Thread(new RebuildCacheThread()).start();
        ZooKeeperSession.init();

    }
}
