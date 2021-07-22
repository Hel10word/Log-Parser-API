package TestLogSource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * @author 凡
 * @data 2021/7/8
 */
public class Create_100W_ApacheLog {

    public static void main(String[] args) {
        String path = "./ApacheLogTest";

        File file=new File(path);
        try (BufferedWriter br = file.exists()?
                //表示不续写原文件
                new BufferedWriter(new FileWriter(file,false))
                //创建写出流对象
                :new BufferedWriter(new FileWriter(path))) {
//        写入 数据
            for (int i = 0 ;i<50_000;i++)
                br.write(APACHE_STR);
        }catch (Exception e){}
        System.out.println("文件创建成功");
    }
// 20条 Apache log 记录
    final static String APACHE_STR =
        "192.168.64.101 - - [17/Nov/2020:20:33:13 +0800] \"GET / HTTP/1.1\" 200 11216\n" +
        "192.168.64.101 - - [17/Nov/2020:20:33:13 +0800] \"GET /favicon.ico HTTP/1.1\" 200 21630\n" +
        "192.168.64.101 - - [17/Nov/2020:20:33:13 +0800] \"GET /bg-nav.png HTTP/1.1\" 200 1401\n" +
        "192.168.64.101 - - [17/Nov/2020:20:33:13 +0800] \"GET /asf-logo-wide.svg HTTP/1.1\" 200 27235\n" +
        "192.168.64.101 - - [17/Nov/2020:20:37:38 +0800] \"GET / HTTP/1.1\" 200 11216\n" +
        "192.168.64.101 - - [17/Nov/2020:20:37:38 +0800] \"GET /tomcat.css HTTP/1.1\" 200 5581\n" +
        "192.168.64.101 - - [17/Nov/2020:20:37:38 +0800] \"GET /favicon.ico HTTP/1.1\" 200 21630\n" +
        "192.168.64.101 - - [17/Nov/2020:20:37:38 +0800] \"GET /bg-nav.png HTTP/1.1\" 200 1401\n" +
        "192.168.64.101 - - [17/Nov/2020:20:37:38 +0800] \"GET /asf-logo-wide.svg HTTP/1.1\" 200 27235\n" +
        "192.168.64.101 - - [17/Nov/2020:20:37:38 +0800] \"GET /bg-middle.png HTTP/1.1\" 200 1918\n" +
        "192.168.64.1 - - [17/Nov/2020:20:45:41 +0800] \"GET / HTTP/1.1\" 200 11216\n" +
        "192.168.64.1 - - [17/Nov/2020:20:45:41 +0800] \"GET /tomcat.css HTTP/1.1\" 200 5581\n" +
        "192.168.64.1 - - [17/Nov/2020:20:45:41 +0800] \"GET /asf-logo-wide.svg HTTP/1.1\" 200 27235\n" +
        "192.168.64.1 - - [17/Nov/2020:20:45:41 +0800] \"GET /bg-middle.png HTTP/1.1\" 200 1918\n" +
        "192.168.64.1 - - [17/Nov/2020:20:45:42 +0800] \"GET /favicon.ico HTTP/1.1\" 200 21630\n" +
        "127.0.0.1 - - [17/Nov/2020:22:43:32 +0800] \"CONNECT aus5.mozilla.org:443 HTTP/1.1\" 400 764\n" +
        "127.0.0.1 - - [17/Nov/2020:22:45:00 +0800] \"CONNECT normandy.cdn.mozilla.net:443 HTTP/1.1\" 400 764\n" +
        "127.0.0.1 - - [17/Nov/2020:22:49:00 +0800] \"CONNECT redirector.gvt1.com:443 HTTP/1.1\" 400 764\n" +
        "127.0.0.1 - - [17/Nov/2020:22:49:00 +0800] \"GET /openh264-linux64-2e1774ab6dc6c43debb0b5b628bdf122a391d521.zip HTTP/1.1\" 404 761\n" +
        "127.0.0.1 - - [17/Nov/2020:22:51:00 +0800] \"CONNECT blocklists.settings.services.mozilla.com:443 HTTP/1.1\" 400 764\n";

}
