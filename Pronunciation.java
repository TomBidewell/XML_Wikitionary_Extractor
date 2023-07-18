import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pronunciation implements Interface{


    private String pr;


    public Object getValue(){
        return this.pr;
    }



    public void setValue(String content){
        String pattern = "\\{\\{pron\\|([^|}]+)\\|";

        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(content);

        if (matcher.find()) {
            String extractedString = matcher.group(1);
            this.pr = extractedString;
        }else{
            this.pr =  "no pronunciation found";
        }
    }
}

