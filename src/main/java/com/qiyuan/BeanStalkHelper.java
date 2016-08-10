package com.qiyuan;

import com.surftools.BeanstalkClient.Client;
import com.surftools.BeanstalkClientImpl.ClientImpl;

/**
 * Created by duxiutao duxiutao@gmail.com on 16/7/1.
 */
public class BeanStalkHelper {
    static Client s = new ClientImpl("localhost", 11300);

    static {
        s.useTube("dishui");
    }
    public static Client getClient() {
        return s;
    }
}
