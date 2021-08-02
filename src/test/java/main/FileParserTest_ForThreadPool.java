package main;

import com.boraydata.logparser.parser.LogParser;
import com.boraydata.logparser.parser.LogParserFactory;
import com.boraydata.logparser.util.constant.ParserType;
import entity.ApacheEntity;
import entity.Log4jEntity;
import entity.SyslogEntity;
import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;

/** 用来测试文件解析 使用线程池
 * @author bufan
 * @data 2021/7/19
 */
public class FileParserTest_ForThreadPool {

    static String LOG4J_PATTERN = "%d{ISO8601} %style{%marker}{red} [%-22c{2}] %t %-5p: %m%n";
    static LogParser syslogParser = LogParserFactory.create(ParserType.Syslog);
    static LogParser apacheParser = LogParserFactory.create(ParserType.ApacheLog);
    static LogParser log4jParser = LogParserFactory.create(ParserType.Log4j,LOG4J_PATTERN);

    // 用来存放  线程池处理完的  数据
    static List<Object> allResult = new ArrayList<>(100_0000);

    // 将文件整个读取到 List 中
    public static List<String> uponFileToList(String fileName){
        List list = new ArrayList();
        try {
            list = Files.readAllLines(Paths.get(fileName));
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @SneakyThrows
    @ParameterizedTest
    @ValueSource(ints = {1})
    public static void runTask(Integer threadNum){

        List<String> sysList = uponFileToList("./sysLogTest");
        List<String> apacheList = uponFileToList("./ApacheLogTest");
        List<String> log4jList = uponFileToList("./Log4jTest");
        Thread.sleep(3000);
        System.out.println("file cache is load");

        System.out.println("=======  SysLog  ===============  toMap  =====================");
        createThreadPool(threadNum,sysList,s->syslogParser.toMap(s));
        System.out.println("=======  SysLog  ===============  toJson  =====================");
        createThreadPool(threadNum,sysList,s->syslogParser.toJson(s));
        System.out.println("=======  SysLog  ===============  toEntity  =====================");
        createThreadPool(threadNum,sysList,syslogParser, SyslogEntity.class);


        System.out.println("=======  Apache  ===============  toMap  =====================");
        createThreadPool(threadNum,apacheList,s->apacheParser.toMap(s));
        System.out.println("=======  Apache  ===============  toJson  =====================");
        createThreadPool(threadNum,apacheList,s->apacheParser.toJson(s));
        System.out.println("=======  Apache  ===============  toEntity  =====================");
        createThreadPool(threadNum,apacheList,apacheParser, ApacheEntity.class);


        System.out.println("=======  Log4j  ===============  toMap  =====================");
        createThreadPool(threadNum,log4jList,s->log4jParser.toMap(s));
        System.out.println("=======  Log4j  ===============  toJson  =====================");
        createThreadPool(threadNum,log4jList,s->log4jParser.toJson(s));
        System.out.println("=======  Log4j  ===============  toEntity  =====================");
        createThreadPool(threadNum,log4jList,log4jParser, Log4jEntity.class);
    }

    //  线程池 传入的 方法
    private static Runnable getThread(Integer start, Integer end, List<String> list, Function<String, Object> fun){
        return ()->{
            for (Integer i = start;i<end;i++)
                fun.apply(list.get(i));
//                allResult.add(fun.apply(list.get(i)));
        };
    }

    // 处理 Json  Map 方法
    @SneakyThrows
    public static void createThreadPool(Integer threadNum, List<String> list, Function<String, Object> function){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(threadNum,// 核心线程数
                threadNum, // 最大线程数
                Integer.MAX_VALUE, // 闲置线程存活时间
                TimeUnit.MILLISECONDS,// 时间单位
                new LinkedBlockingDeque<Runnable>(Integer.MAX_VALUE),// 线程队列
                Executors.defaultThreadFactory()// 线程工厂
        );

//        为了方便 观察 CPU 利用率   阻塞一段时间后再开始解析
//        Thread.sleep(3000);

        System.out.println("The CorePoolSize : "+executor.getCorePoolSize()+"  The MaximumPoolSize : "+executor.getMaximumPoolSize());
        long start = System.currentTimeMillis();

        Integer listSize = list.size();
        Integer step = listSize/threadNum;

        for (int i = 0;i<threadNum;i++){
            if (i==threadNum-1){
                executor.execute(getThread(i*step,listSize,list,function));
                break;
            }
            executor.execute(getThread(i*step,(i+1)*step,list,function));
        }
        // 关闭 线程池
        executor.shutdown();
        while(true){
            if(executor.isTerminated()){
                System.out.println("All threads are processed!");
                break;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time spent  " + (end - start));
//        System.out.println(allResult.size());
        // 清空 List 中解析 的日志
//        allResult.clear();
        // 调用 GC 来释放空间
//        System.gc();
    }

    // 处理 toEntity
    @SneakyThrows
    public static <T> void createThreadPool(Integer threadNum, List<String> list, LogParser parser, Class<T> c){

        ThreadPoolExecutor executor = new ThreadPoolExecutor(threadNum,// 核心线程数
                threadNum, // 最大线程数
                Integer.MAX_VALUE, // 闲置线程存活时间
                TimeUnit.MILLISECONDS,// 时间单位
                new LinkedBlockingDeque<Runnable>(Integer.MAX_VALUE),// 线程队列
                Executors.defaultThreadFactory()// 线程工厂
        );

//        为了方便 观察 CPU 利用率   阻塞一段时间后再开始解析
//        Thread.sleep(3000);

        System.out.println("The CorePoolSize : "+executor.getCorePoolSize()+"  The MaximumPoolSize : "+executor.getMaximumPoolSize());
        long start = System.currentTimeMillis();
        Integer listSize = list.size();
        Integer step = listSize/threadNum;

        for (int i = 0;i<threadNum;i++){
            if (i==threadNum-1){
                executor.execute(getThread(i*step,listSize,list,x->parser.toEntity(x,c)));
                break;
            }
            executor.execute(getThread(i*step,(i+1)*step,list,x->parser.toEntity(x,c)));
        }

        executor.shutdown();
        while(true){
            if(executor.isTerminated()){
                System.out.println("All threads are processed!");
                break;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time spent  " + (end - start));
//        allResult.clear();
//        System.gc();
    }



/////////////////////////// 单线程 每行读取文件 传给线程池  /////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @Param logStr : 需要被解析的 LogStr
     * @Param function : 需要被调用的参数
     */
    private Runnable startReadLineThread(final String logStr,final Function<String,Object> function){
        return ()->{
            allResult.add(function.apply(logStr));
        };
    }

    // 使用 单线程 逐行读取文件  传给线程池 来解析
    public void runThreadPool(Integer numThreads,String filePath,Function<String,Object> function){
        // 创建一个固定大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        long start = System.currentTimeMillis();

        try {
            File file = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while((line = br.readLine()) != null)
                executorService.execute(startReadLineThread(line,function));

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time spent : " + (end - start));
        System.out.println(allResult.size()+" : "+allResult.get(allResult.size()-1));
        allResult.clear();
        System.gc();
    }
}
