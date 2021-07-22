package TestLogSource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
/**
 * @author 凡
 * @data 2021/7/8
 */
public class Create_100W_Syslog {

    public static void main(String[] args) {
        String path = "./sysLogTest";

        File file=new File(path);
        try (BufferedWriter br = file.exists()?
                //表示不续写原文件
                new BufferedWriter(new FileWriter(file,false))
                //创建写出流对象
                :new BufferedWriter(new FileWriter(path))) {
//        写入 数据
            for (int i = 0 ;i<10_000;i++)
                br.write(SYSLOG_STR);
        }catch (Exception e){}

        System.out.println("文件创建成功");
    }
// 100 条 Syslog 记录
    final static String SYSLOG_STR = "Jul  5 16:00:20 boray01 systemd[1]: logrotate.service: Main process exited, code=exited, status=1/FAILURE\n" +
            "Jul  5 16:00:20 boray01 systemd[1]: logrotate.service: Failed with result 'exit-code'.\n" +
            "Jul  5 16:00:20 boray01 systemd[1]: Failed to start Rotate log files.\n" +
            "Jul  5 16:00:21 boray01 conmon[15913]: cluster 2021-07-05T16:00:20.775908+0000 mgr.boray01.eamgxr (mgr.14976) 953797 : cluster [DBG] \n" +
            "Jul  5 16:00:21 boray01 conmon[15913]: pgmap v954165: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 48 KiB/s rd, 348 KiB/s wr, 1 op/s\n" +
            "Jul  5 16:00:22 boray01 conmon[95799]: debug 2021-07-05T16:00:22.774+0000 7fa625813700  0 log_channel(cluster) log [DBG] : pgmap v954166: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 48 KiB/s rd, 348 KiB/s wr, 1 op/s\n" +
            "Jul  5 16:00:23 boray01 conmon[15913]: cluster 2021-07-05T16:00:22.776765+0000 mgr.boray01.eamgxr (mgr.14976) 953798\n" +
            "Jul  5 16:00:23 boray01 conmon[15913]:  : cluster [DBG] pgmap v954166: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 48 KiB/s rd, 348 KiB/s wr, 1 op/s\n" +
            "Jul  5 16:00:24 boray01 conmon[15913]: debug 2021-07-05T16:00:24.446+0000 7f2030d2c700  1 mon.boray01@0(leader).osd e3500 _set_new_cache_sizes cache_size:1020054731 inc_alloc: 71303168 full_alloc: 88080384 kv_alloc: 851443712\n" +
            "Jul  5 16:00:24 boray01 conmon[95799]: debug 2021-07-05T16:00:24.774+0000 7fa625813700  0 log_channel(cluster) log [DBG] : pgmap v954167: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 48 KiB/s rd, 348 KiB/s wr, 1 op/s\n" +
            "Jul  5 16:00:25 boray01 conmon[15913]: cluster 2021-07-05T16:00:24.777812+0000 mgr.boray01.eamgxr (mgr.14976) 953799 : cluster\n" +
            "Jul  5 16:00:25 boray01 conmon[15913]:  [DBG] pgmap v954167: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 48 KiB/s rd, 348 KiB/s wr, 1 op/s\n" +
            "Jul  5 16:00:26 boray01 conmon[95799]: debug 2021-07-05T16:00:26.774+0000 7fa625813700  0 log_channel(cluster) log [DBG] : pgmap v954168: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 48 KiB/s rd, 348 KiB/s wr, 1 op/s\n" +
            "Jul  5 16:00:27 boray01 conmon[15913]: cluster 2021-07-05T16:00:26.778907+0000 mgr.boray01.eamgxr (mgr.14976) 953800 : cluster\n" +
            "Jul  5 16:00:27 boray01 conmon[15913]:  [DBG] pgmap v954168: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 48 KiB/s rd, 348 KiB/s wr, 1 op/s\n" +
            "Jul  5 16:00:28 boray01 conmon[95799]: debug 2021-07-05T16:00:28.774+0000 7fa625813700  0 log_channel(cluster) log [DBG] : pgmap v954169: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 48 KiB/s rd, 3.3 KiB/s wr, 0 op/s\n" +
            "Jul  5 16:00:29 boray01 conmon[15913]: debug 2021-07-05T16:00:29.450+0000 7f2030d2c700  1 mon.boray01@0(leader).osd e3500 _set_new_cache_sizes cache_size:1020054731 inc_alloc: 71303168 full_alloc: 88080384 kv_alloc: 851443712\n" +
            "Jul  5 16:00:29 boray01 conmon[15913]: cluster 2021-07-05T16:00:28.779868+0000 mgr.boray01.eamgxr (mgr.\n" +
            "Jul  5 16:00:29 boray01 conmon[15913]: 14976) 953801 : cluster [DBG] pgmap v954169: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 48 KiB/s rd, 3.3 KiB/s wr, 0 op/s\n" +
            "Jul  5 16:00:30 boray01 conmon[95799]: debug 2021-07-05T16:00:30.778+0000 7fa625813700  0 log_channel(cluster) log [DBG] : pgmap v954170: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail\n" +
            "Jul  5 16:00:31 boray01 conmon[15913]: cluster 2021-07-05T16:00:30.780955+0000 mgr.boray01.eamgxr (mgr.14976) 953802 : cluster\n" +
            "Jul  5 16:00:31 boray01 conmon[15913]:  [DBG] pgmap v954170: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail\n" +
            "Jul  5 16:00:32 boray01 conmon[95799]: debug 2021-07-05T16:00:32.778+0000 7fa625813700  0 log_channel(cluster) log [DBG] : pgmap v954171: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail\n" +
            "Jul  5 16:00:34 boray01 conmon[15913]: cluster 2021-07-05T16:00:32.781688+0000 mgr.boray01.eamgxr (mgr.14976) 953803 : cluster \n" +
            "Jul  5 16:00:34 boray01 conmon[15913]: [DBG] pgmap v954171: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail\n" +
            "Jul  5 16:00:34 boray01 conmon[15913]: debug 2021-07-05T16:00:34.450+0000 7f2030d2c700  1 mon.boray01@0(leader).osd e3500 _set_new_cache_sizes cache_size:1020054731 inc_alloc: 71303168 full_alloc: 88080384 kv_alloc: 851443712\n" +
            "Jul  5 16:00:34 boray01 conmon[95799]: debug 2021-07-05T16:00:34.778+0000 7fa625813700  0 log_channel(cluster) log [DBG] : pgmap v954172: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail\n" +
            "Jul  5 16:00:35 boray01 conmon[15913]: cluster 2021-07-05T16:00:34.782522+0000 mgr.boray01.eamgxr (mgr.14976) 953804 : cluster [DBG] pgmap v954172: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail\n" +
            "Jul  5 16:00:36 boray01 conmon[15913]: debug 2021-07-05T16:00:36.742+0000 7f202e527700  0 mon.boray01@0(leader) e5 handle_command mon_command({\"prefix\": \"config dump\", \"format\": \"json\"} v 0) v1\n" +
            "Jul  5 16:00:36 boray01 conmon[15913]: debug 2021-07-05T16:00:36.742+0000 7f202e527700  0 log_channel(audit) log [DBG] : from='mgr.14976 192.168.120.11:0/189651033' entity='mgr.boray01.eamgxr' cmd=[{\"prefix\": \"config dump\", \"format\": \"json\"}]: dispatch\n" +
            "Jul  5 16:00:36 boray01 conmon[95799]: debug 2021-07-05T16:00:36.778+0000 7fa625813700  0 log_channel(cluster) log [DBG] : pgmap v954173: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail\n" +
            "Jul  5 16:00:36 boray01 conmon[15913]: audit 2021-07-05T16:00:36.747652+0000 mon.boray01 (mon.\n" +
            "Jul  5 16:00:36 boray01 conmon[15913]: 0) 172431 : audit [DBG] from='mgr.14976 192.168.120.11:0/189651033' entity='mgr.boray01.eamgxr' cmd=[{\"prefix\": \"config dump\", \"format\": \"json\"}]: dispatch\n" +
            "Jul  5 16:00:37 boray01 kernel: [1928889.511431] overlayfs: unrecognized mount option \"volatile\" or missing value\n" +
            "Jul  5 16:00:37 boray01 conmon[15913]: cluster 2021-07-05T16:00:36.783476+0000 mgr.boray01.eamgxr (mgr.14976) 953805 : cluster [DBG]\n" +
            "Jul  5 16:00:37 boray01 conmon[15913]:  pgmap v954173: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail\n" +
            "Jul  5 16:00:38 boray01 conmon[95799]: debug 2021-07-05T16:00:38.782+0000 7fa625813700  0 log_channel(cluster) log [DBG] : pgmap v954174: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail\n" +
            "Jul  5 16:00:39 boray01 conmon[15913]: debug 2021-07-05T16:00:39.010+0000 7f202e527700  0 mon.boray01@0(leader) e5 handle_command mon_command([{prefix=config-key set, key=mgr/cephadm/host.boray05}] v 0) v1\n" +
            "Jul  5 16:00:39 boray01 conmon[15913]: debug 2021-07-05T16:00:39.018+0000 7f202cd24700  0 log_channel(audit) log [INF] : from='mgr.14976 192.168.120.11:0/189651033' entity='mgr.boray01.eamgxr' \n" +
            "Jul  5 16:00:39 boray01 conmon[15913]: debug 2021-07-05T16:00:39.454+0000 7f2030d2c700  1 mon.boray01@0(leader).osd e3500 _set_new_cache_sizes cache_size:1020054731 inc_alloc: 71303168 full_alloc: 88080384 kv_alloc: 851443712\n" +
            "Jul  5 16:00:40 boray01 conmon[15913]: cluster 2021-07-05T16:\n" +
            "Jul  5 16:00:40 boray01 conmon[15913]: 00:38.784536+0000 mgr.boray01.eamgxr (mgr.14976) 953806 : cluster [DBG] pgmap v954174: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail\n" +
            "Jul  5 16:00:40 boray01 conmon[15913]: audit 2021-07-05T16:00\n" +
            "Jul  5 16:00:40 boray01 conmon[15913]: :39.023154+0000 mon.boray01 (mon.0) 172432 : audit [INF] from='mgr.14976 192.168.120.11:0/189651033' entity='mgr.boray01.eamgxr' \n" +
            "Jul  5 16:00:40 boray01 conmon[95799]: debug 2021-07-05T16:00:40.782+0000 7fa625813700  0 log_channel(cluster) log [DBG] : pgmap v954175: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 3.0 KiB/s wr, 1 op/s\n" +
            "Jul  5 16:00:41 boray01 conmon[15913]: cluster 2021-07-05T16:00:40.785631+0000 mgr.boray01.eamgxr (mgr.14976) 953807 : cluster \n" +
            "Jul  5 16:00:41 boray01 conmon[15913]: [DBG] pgmap v954175: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 3.0 KiB/s wr, 1 op/s\n" +
            "Jul  5 16:00:42 boray01 conmon[95799]: debug 2021-07-05T16:00:42.783+0000 7fa625813700  0 log_channel(cluster) log [DBG] : pgmap v954176: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 3.3 KiB/s wr, 1 op/s\n" +
            "Jul  5 16:00:43 boray01 conmon[15913]: cluster 2021-07-05T16:00:42.786411+0000 mgr.boray01.eamgxr (mgr.14976) 953808 : cluster [DBG] pgmap v954176: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 3.3 KiB/s wr, 1 op/s\n" +
            "Jul  5 16:00:43 boray01 conmon[15913]: \n" +
            "Jul  5 16:00:44 boray01 conmon[15913]: debug 2021-07-05T16:00:44.459+0000 7f2030d2c700  1 mon.boray01@0(leader).osd e3500 _set_new_cache_sizes cache_size:1020054731 inc_alloc: 71303168 full_alloc: 88080384 kv_alloc: 851443712\n" +
            "Jul  5 16:00:44 boray01 conmon[15913]: debug 2021-07-05T16:00:44.523+0000 7f202e527700  0 mon.boray01@0(leader) e5 handle_command mon_command([{prefix=config-key set, key=mgr/cephadm/host.boray02}] v 0) v1\n" +
            "Jul  5 16:00:44 boray01 conmon[15913]: debug 2021-07-05T16:00:44.551+0000 7f202cd24700  0 log_channel(audit) log [INF] : from='mgr.14976 192.168.120.11:0/189651033' entity='mgr.boray01.eamgxr' \n" +
            "Jul  5 16:00:44 boray01 conmon[95799]: debug 2021-07-05T16:00:44.783+0000 7fa625813700  0 log_channel(cluster) log [DBG] : pgmap v954177: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 4.7 KiB/s wr, 1 op/s\n" +
            "Jul  5 16:00:45 boray01 conmon[15913]: debug 2021-07-05T16:00:45.103+0000 7f202e527700  0 mon.boray01@0(leader) e5 handle_command mon_command([{prefix=config-key set, key=mgr/cephadm/osd_remove_queue}] v 0) v1\n" +
            "Jul  5 16:00:45 boray01 conmon[15913]: debug 2021-07-05T16:00:45.119+0000 7f202cd24700  0 log_channel(audit) log [INF] : from='mgr.14976 192.168.120.11:0/189651033' entity='mgr.boray01.eamgxr' \n" +
            "Jul  5 16:00:45 boray01 conmon[15913]: audit 2021-07-05T16:00:44.554707+0000 mon.boray01 (mon.0) 172433 : audit \n" +
            "Jul  5 16:00:45 boray01 conmon[15913]: [INF] from='mgr.14976 192.168.120.11:0/189651033' entity='mgr.boray01.eamgxr' \n" +
            "Jul  5 16:00:45 boray01 conmon[15913]: cluster 2021-07-05T16:00:44.787368+0000 mgr.boray01.eamgxr (mgr.14976) 953809 : cluster [DBG] pgmap v954177: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 4.7 KiB/s wr, 1 op/s\n" +
            "Jul  5 16:00:45 boray01 conmon[15913]: audit 2021-07-05T16:00:45.120953+0000 mon.boray01 (mon\n" +
            "Jul  5 16:00:45 boray01 conmon[15913]: .0) 172434 : audit [INF] from='mgr.14976 192.168.120.11:0/189651033' entity='mgr.boray01.eamgxr' \n" +
            "Jul  5 16:00:46 boray01 conmon[95799]: debug 2021-07-05T16:00:46.787+0000 7fa625813700  0 log_channel(cluster) log [DBG] : pgmap v954178: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 9.0 KiB/s wr, 2 op/s\n" +
            "Jul  5 16:00:46 boray01 dockerd[2373064]: time=\"2021-07-06T00:00:46.859633333+08:00\" level=info msg=\"NetworkDB stats boray01(f89ba2444f98) - netID:klotipji3q4f24mvjivoyymqc leaving:false netPeers:4 entries:26 Queue qLen:0 netMsg/s:0\"\n" +
            "Jul  5 16:00:46 boray01 dockerd[2373064]: time=\"2021-07-06T00:00:46.859792894+08:00\" level=info msg=\"NetworkDB stats boray01(f89ba2444f98) - netID:odv4snkx0ulzd2ym1uqi6ucjp leaving:false netPeers:4 entries:52 Queue qLen:0 netMsg/s:0\"\n" +
            "Jul  5 16:00:47 boray01 conmon[15913]: cluster 2021-07-05T16:00:46.788440+0000 mgr.boray01.eamgxr (mgr.14976) 953810 : cluster \n" +
            "Jul  5 16:00:47 boray01 conmon[15913]: [DBG] pgmap v954178: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 9.0 KiB/s wr, 2 op/s\n" +
            "Jul  5 16:00:48 boray01 conmon[95799]: debug 2021-07-05T16:00:48.787+0000 7fa625813700  0 log_channel(cluster) log [DBG] : pgmap v954179: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 11 KiB/s wr, 2 op/s\n" +
            "Jul  5 16:00:49 boray01 conmon[15913]: debug 2021-07-05T16:00:49.463+0000 7f2030d2c700  1 mon.boray01@0(leader).osd e3500 _set_new_cache_sizes cache_size:1020054731 inc_alloc: 71303168 full_alloc: 88080384 kv_alloc: 851443712\n" +
            "Jul  5 16:00:49 boray01 conmon[15913]: cluster 2021-07-05T16:00:48.789439+0000 mgr.boray01.eamgxr (mgr.14976\n" +
            "Jul  5 16:00:49 boray01 conmon[15913]: ) 953811 : cluster [DBG] pgmap v954179: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 11 KiB/s wr, 2 op/s\n" +
            "Jul  5 16:00:50 boray01 conmon[95799]: debug 2021-07-05T16:00:50.787+0000 7fa625813700  0 log_channel(cluster) log [DBG] : pgmap v954180: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 17 KiB/s wr, 4 op/s\n" +
            "Jul  5 16:00:51 boray01 conmon[15913]: cluster 2021-07-05T16:00:50.790529+0000 mgr.boray01.eamgxr (mgr.14976) 953812 : \n" +
            "Jul  5 16:00:51 boray01 conmon[15913]: cluster [DBG] pgmap v954180: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 17 KiB/s wr, 4 op/s\n" +
            "Jul  5 16:00:52 boray01 conmon[95799]: debug 2021-07-05T16:00:52.787+0000 7fa625813700  0 log_channel(cluster) log [DBG] : pgmap v954181: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 14 KiB/s wr, 3 op/s\n" +
            "Jul  5 16:00:53 boray01 conmon[15913]: cluster 2021-07-05T16:\n" +
            "Jul  5 16:00:53 boray01 conmon[15913]: 00:52.791216+0000 mgr.boray01.eamgxr (mgr.14976) 953813 : cluster [DBG] pgmap v954181: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 14 KiB/s wr, 3 op/s\n" +
            "Jul  5 16:00:54 boray01 conmon[15913]: debug 2021-07-05T16:00:54.463+0000 7f2030d2c700  1 mon.boray01@0(leader).osd e3500 _set_new_cache_sizes cache_size:1020054731 inc_alloc: 71303168 full_alloc: 88080384 kv_alloc: 851443712\n" +
            "Jul  5 16:00:54 boray01 conmon[95799]: debug 2021-07-05T16:00:54.787+0000 7fa625813700  0 log_channel(cluster) log [DBG] : pgmap v954182: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 16 KiB/s wr, 4 op/s\n" +
            "Jul  5 16:00:55 boray01 conmon[15913]: cluster 2021-07-05T16:00:54.\n" +
            "Jul  5 16:00:55 boray01 conmon[15913]: 792230+0000 mgr.boray01.eamgxr (mgr.14976) 953814 : cluster [DBG] pgmap v954182: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 16 KiB/s wr, 4 op/s\n" +
            "Jul  5 16:00:56 boray01 conmon[95799]: debug 2021-07-05T16:00:56.791+0000 7fa625813700  0 log_channel(cluster) log [DBG] : pgmap v954183: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 14 KiB/s wr, 4 op/s\n" +
            "Jul  5 16:00:57 boray01 conmon[15913]: cluster 2021-07-05T16:00:56.793298+0000 mgr.boray01.eamgxr\n" +
            "Jul  5 16:00:57 boray01 conmon[15913]:  (mgr.14976) 953815 : cluster [DBG] pgmap v954183: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 14 KiB/s wr, 4 op/s\n" +
            "Jul  5 16:00:58 boray01 conmon[95799]: debug 2021-07-05T16:00:58.791+0000 7fa625813700  0 log_channel(cluster) log [DBG] : pgmap v954184: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 10 KiB/s wr, 3 op/s\n" +
            "Jul  5 16:00:59 boray01 conmon[15913]: debug 2021-07-05T16:00:59.467+0000 7f2030d2c700  1 mon.boray01@0(leader).osd e3500 _set_new_cache_sizes cache_size:1020054731 inc_alloc: 71303168 full_alloc: 88080384 kv_alloc: 851443712\n" +
            "Jul  5 16:00:59 boray01 conmon[15913]: cluster 2021-07-05T16\n" +
            "Jul  5 16:00:59 boray01 conmon[15913]: :00:58.794309+0000 mgr.boray01.eamgxr (mgr.14976) 953816 : cluster [DBG] pgmap v954184: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 10 KiB/s wr, 3 op/s\n" +
            "Jul  5 16:01:00 boray01 conmon[95799]: debug 2021-07-05T16:01:00.791+0000 7fa625813700  0 log_channel(cluster) log [DBG] : pgmap v954185: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 7.7 KiB/s wr, 3 op/s\n" +
            "Jul  5 16:01:01 boray01 conmon[15913]: cluster 2021-07-05T16:01:00.795406+0000 \n" +
            "Jul  5 16:01:01 boray01 conmon[15913]: mgr.boray01.eamgxr (mgr.14976) 953817 : cluster [DBG] pgmap v954185: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 7.7 KiB/s wr, 3 op/s\n" +
            "Jul  5 16:01:02 boray01 conmon[95799]: debug 2021-07-05T16:01:02.791+0000 7fa625813700  0 log_channel(cluster) log [DBG] : pgmap v954186: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 2.3 KiB/s wr, 1 op/s\n" +
            "Jul  5 16:01:03 boray01 conmon[15913]: cluster 2021-07-05T16:01:02.796230+0000 \n" +
            "Jul  5 16:01:03 boray01 conmon[15913]: mgr.boray01.eamgxr (mgr.14976) 953818 : cluster [DBG] pgmap v954186: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 2.3 KiB/s wr, 1 op/s\n" +
            "Jul  5 16:01:04 boray01 conmon[15913]: debug 2021-07-05T16:01:04.467+0000 7f2030d2c700  1 mon.boray01@0(leader).osd e3500 _set_new_cache_sizes cache_size:1020054731 inc_alloc: 71303168 full_alloc: 88080384 kv_alloc: 851443712\n" +
            "Jul  5 16:01:04 boray01 conmon[95799]: debug 2021-07-05T16:01:04.795+0000 7fa625813700  0 log_channel(cluster) log [DBG] : pgmap v954187: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 1.7 KiB/s wr, 0 op/s\n" +
            "Jul  5 16:01:05 boray01 conmon[15913]: cluster 2021-07-05T16:01:04.797254+0000 mgr.boray01.eamgxr (mgr.\n" +
            "Jul  5 16:01:05 boray01 conmon[15913]: 14976) 953819 : cluster [DBG] pgmap v954187: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail; 0 B/s rd, 1.7 KiB/s wr, 0 op/s\n" +
            "Jul  5 16:01:06 boray01 conmon[95799]: debug 2021-07-05T16:01:06.795+0000 7fa625813700  0 log_channel(cluster) log [DBG] : pgmap v954188: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail\n" +
            "Jul  5 16:01:07 boray01 conmon[15913]: cluster 2021-07-05T16:01:06.798275\n" +
            "Jul  5 16:01:07 boray01 conmon[15913]: +0000 mgr.boray01.eamgxr (mgr.14976) 953820 : cluster [DBG] pgmap v954188: 145 pgs: 145 active+clean; 83 GiB data, 249 GiB used, 19 TiB / 19 TiB avail\n";
}
