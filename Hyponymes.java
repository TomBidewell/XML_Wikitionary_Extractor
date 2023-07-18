import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hyponymes implements Interface{

    private ArrayList<String> hypos;


    public Object getValue(){
        return this.hypos;
    }
    public void setValue(String input){
        String init_regex = "hyponymes\\}\\}(.*?)\\{\\{S";
        Pattern init_pattern = Pattern.compile(init_regex, Pattern.DOTALL);
        Matcher init_matcher = init_pattern.matcher(input);
        ArrayList<String> hyps = new ArrayList<>();
        if (init_matcher.find()){
            String hyps_str = init_matcher.group(1);
            String hyps_reg = "\\[\\[(.*?)\\]\\]";
            Pattern hyps_pattern = Pattern.compile(hyps_reg, Pattern.DOTALL);
            Matcher hyps_matcher = hyps_pattern.matcher(hyps_str);
            while (hyps_matcher.find()){
                hyps.add(hyps_matcher.group(1));
            }
        }else{
            hyps.add("No hyponymes");
        }
        this.hypos = hyps;
    }

}
