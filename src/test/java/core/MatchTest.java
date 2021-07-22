package core;


/**
 * @author bufan
 * @data 2021/7/16
 */
public class MatchTest {


    public String cleanString(String value) {

        if (value == null || value.isEmpty()) {
            return value;
        }
        value = value.replaceAll("(^[\\\"\\'])|([\\\"\\']$)","");
        return value;
    }
    public String cleanString1 (String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }

        char firstChar = value.charAt(0);
        char lastChar = value.charAt(value.length() - 1);
        System.out.println("firstChar:"+firstChar+"    lastChar:"+lastChar);
        if (firstChar == lastChar
                && (firstChar == '"' || firstChar == '\'')
        ) {
            if (value.length() <= 2) {
                return "";
            } else {
                int found = 0;
                for (int i = 1; i < value.length() - 1; i++ ) {
                    if (value.charAt(i) == firstChar) {
                        found++;
                    }
                }
                if (found == 0) {
                    return value.substring(1, value.length() - 1);
                }
            }
        }

        return value;
    }


    public void test(){

        System.out.println(cleanString1("\"nih\"\"asdsad\""));
        System.out.println(cleanString1("\'nih\"asdsad\"\'"));
        System.out.println(cleanString1("\'asd23\""));
    }
}
