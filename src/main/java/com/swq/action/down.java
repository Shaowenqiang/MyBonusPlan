package com.swq.action;

/**
 * ${todo}(这里用一句话描述这个类的作用)
 *
 * @author swq
 * @date 2019-03-13 11:14
 */
public class down
{
    public static void main(String[] args){
        try {
            java.io.InputStream inputStream;
            java.net.URL url = new java.net.URL("http://xiazai.xqishu.com/rar");
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url
                    .openConnection();
            connection.connect();
            inputStream = connection.getInputStream();
            connection.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
