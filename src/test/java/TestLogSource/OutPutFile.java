package TestLogSource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/** 写入文件
 * @author 凡
 * @data 2021/7/8
 */
public class OutPutFile {
    private BufferedWriter bufferedWriter;

    public OutPutFile(String path) throws IOException {
        //判断文件对象是否存在
        File file=new File(path);

        if (!file.exists()) {
            //创建写出流对象
            this.bufferedWriter=new BufferedWriter(new FileWriter(path));
        }else{
            //-------表示续写原文件
            this.bufferedWriter=new BufferedWriter(new FileWriter(file,true));
        }
    }


    public boolean bufferedWriterTest(String str) {
        if(this.bufferedWriter==null)return false;
        try {
            this.bufferedWriter.write(str);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void bufferedClose() throws IOException {
        if(this.bufferedWriter!=null)
            bufferedWriter.close();
        this.bufferedWriter=null;
    }

}
