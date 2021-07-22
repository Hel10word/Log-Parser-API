package entity;


import lombok.Data;
import lombok.ToString;

/**
 * @author 凡
 * @data 2021/7/8
 */
@ToString
@Data
public class SyslogEntity {
    private String timestamp;
    private String hostname;
    private String program;
    private String pid;
    private String message;

}
