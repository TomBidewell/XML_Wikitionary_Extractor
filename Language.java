import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Language implements Interface{

    private String lang;


    public Object getValue(){
        return this.lang;
    }

    public void setValue(String input){
        String l_regex = "\\{\\{langue\\|(.*?)\\}\\}";
        Pattern l_pattern = Pattern.compile(l_regex, Pattern.DOTALL);
        Matcher l_matcher = l_pattern.matcher(input);
        if (l_matcher.find()){
            this.lang = l_matcher.group(1);
        }else{
            this.lang = "No Language Given";
        }
    }
}
