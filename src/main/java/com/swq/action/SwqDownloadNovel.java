package com.swq.action;

import com.swq.service.UserService;
import com.swq.util.ApplicationContextHolder;
import com.swq.util.FrameSpringBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * ${todo}(这里用一句话描述这个类的作用)
 *
 * @author swq
 * @date 2019-03-13 13:33
 */
public class SwqDownloadNovel implements Callable<List> {
    private String url;
    private String cate;
    @Autowired
    private ApplicationContextHolder holder;
    public SwqDownloadNovel(String url,String cate) {
        this.url = url;
        this.cate = cate;
    }

    @Override
    public List call() throws Exception {
        Thread curThread = Thread.currentThread();
        System.out.println("正在使用的线程名称："+curThread.getName());
        System.out.println("url=" + this.url);
        return downloadRar.getNameByUrl(url,cate);
    }
}
