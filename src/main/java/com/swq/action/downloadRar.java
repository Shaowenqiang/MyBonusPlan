package com.swq.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * ${todo}(这里用一句话描述这个类的作用)
 *
 * @author swq
 * @date 2019-03-13 11:00
 */
public class downloadRar {
    /**
     * 第一步：获取页面的源代码；
     * 第二步：解析源代码，含有图片的标签，再找到图片标签里面的src；
     * 第三步：利用Java里面的net包，网络编程
     * */


    /**
     * 根据网页和编码获取网页内容和源代码
     * @param url
     * @param encoding
     */
    public static String getHtmlResourceByUrl(String url,String encoding){
        StringBuffer buffer   = new StringBuffer();
        URL urlObj            = null;
        URLConnection uc      = null;
        InputStreamReader in  = null;
        BufferedReader reader = null;

        try {
            // 建立网络连接
            urlObj = new URL(url);
            // 打开网络连接
            uc     = urlObj.openConnection();
            // 创建输入流
            in     = new InputStreamReader(uc.getInputStream(),encoding);
            // 创建一个缓冲写入流
            reader = new BufferedReader(in);

            String line = null;
            while ((line = reader.readLine()) != null) {
                // 一行一行追加
                buffer.append(line+"\r\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }

    /**
     * 根据图片的URL下载的图片到本地的filePath
     * @param filePath 文件夹
     * @param imageUrl 图片的网址
     */
    public static void downImages(String filePath,String imageUrl){
        // 截取图片的名称
        String fileName = imageUrl.substring(imageUrl.lastIndexOf("/"));

        //创建文件的目录结构
        File files = new File(filePath);
        if(!files.exists()){// 判断文件夹是否存在，如果不存在就创建一个文件夹
            files.mkdirs();
        }
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            // 创建文件
            File file = new File(filePath+fileName);
            FileOutputStream out = new FileOutputStream(file);
            int i = 0;
            while((i = is.read()) != -1){
                out.write(i);
            }
            is.close();
            out.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //执行测试程序代码
    public static void main(String[] args) {
        List<Map<String,String>> result  = new ArrayList<>();
        for (int i = 0;i<20;i++){
            for(int j = 0;j<1000;j++){
                String url = "http://www.qishu.cc/xuanhuan/list"+i+"_"+j+".html";
                result.addAll(getNameByUrl(url,"cate"));
            }
        }
        System.out.println(result.size());
    }

    public static List getNameByUrl(String url,String cate){
        List<Map<String,String>> result = new ArrayList<>();
        String encoding = "GB2312";
        String htmlResource = getHtmlResourceByUrl(url, encoding);
        // System.out.println(htmlResource);

        // 解析网页源代码
        Document document = Jsoup.parse(htmlResource);
        // 获取所有图片的地址
        Elements elements = document.getElementsByAttribute("title");

        for(Element element : elements){
            String names = element.html();
//            System.out.println("小说名称：" + element.html());
            if (!"".equals(names) && (names.endsWith(" TXT全集下载"))) {
                // 判断imgSrc是否为空且是否以"http://"开头
                System.out.println("小说名称：" + names.replace("  TXT全集下载",".rar"));
                String novel_name = names.replace(" TXT全集下载",".rar");
                Map<String,String> map = new LinkedHashMap<>();
                map.put("id",novel_name);
                map.put("novel_name",novel_name);
                map.put("cate",cate);
                map.put("url","http://xiazai.xqishu.com/rar/"+novel_name);
                result.add(map);
            }
        }
        return result;
    }
}