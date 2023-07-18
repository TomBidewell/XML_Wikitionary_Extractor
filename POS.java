import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class POS implements Interface{

    private ArrayList<String> pos;


    public Object getValue(){
        return this.pos;
    }

    public void setValue(String input){
        ArrayList<String> common_pos = new ArrayList<String>();
        common_pos.add("nom");
        common_pos.add("verbe");
        common_pos.add("adjectif");
        common_pos.add("adverbe");
        common_pos.add("pronom");
        common_pos.add("interjection");
        String pos_reg = "== (.*?) ==";
        Pattern pos_pattern = Pattern.compile(pos_reg, Pattern.DOTALL);
        Matcher pos_matcher = pos_pattern.matcher(input);
        ArrayList<String> pos = new ArrayList<>();
        while (pos_matcher.find()){
            String get_pos = "\\{\\{S\\|(.*?)\\|fr\\}\\}";
            Pattern get_pattern = Pattern.compile(get_pos, Pattern.DOTALL);
            Matcher get_matcher = get_pattern.matcher(pos_matcher.group(1));
            if(get_matcher.find()){
                if (common_pos.contains(get_matcher.group(1))){
                    pos.add(get_matcher.group(1).toUpperCase());
                }
            }
        }
        if (pos.size() == 0){
            pos.add("No POS given");
        }
        this.pos = pos;
    }
}
