package entity;


import lombok.Data;
import lombok.ToString;

/**
 * @author bufan
 * @data 2021/7/14
 */
@ToString
@Data
public class Log4jEntity {
    private String classname;
    private String logleve;
    private String message;
    private String timestamp;

}
