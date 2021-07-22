package entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author 凡
 * @data 2021/7/9
 */
@ToString
@Data
public class ApacheEntity {
    private String clientip;
    private String timestamp;
    private String method;
    private String url;
    private String httpversion;
    private String response;
    private String bytes;
}
