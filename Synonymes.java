import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Synonymes implements Interface{

    private ArrayList<String> syns;


    public Object getValue(){
        return this.syns;
    }
    public void setValue(String input){
        String init_regex = "synonymes\\}\\}(.*?)\\{\\{S";
        Pattern init_pattern = Pattern.compile(init_regex, Pattern.DOTALL);
        Matcher init_matcher = init_pattern.matcher(input);
        ArrayList<String> syns = new ArrayList<>();
        if (init_matcher.find()){
            String syn_str = init_matcher.group(1);
            String syn_reg = "\\[\\[(.*?)\\]\\]";
            Pattern syn_pattern = Pattern.compile(syn_reg, Pattern.DOTALL);
            Matcher syn_matcher = syn_pattern.matcher(syn_str);
            while (syn_matcher.find()){
                syns.add(syn_matcher.group(1));
            }
        }else{
            syns.add("No synonymes");
        }
        this.syns = syns;
    }
}