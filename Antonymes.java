import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Antonymes implements Interface{

    private HashMap<String, ArrayList<String>> ants;


    public Object getValue(){
        return this.ants;
    }

    public void setValue(String input){
        String init_regex = "antonymes\\}\\}(.*?)\\{\\{S";
        Pattern init_pattern = Pattern.compile(init_regex, Pattern.DOTALL);
        Matcher init_matcher = init_pattern.matcher(input);
        HashMap<String, ArrayList<String>> m_n_ans = new HashMap<>();
        if (init_matcher.find()){
            String m_regex = "\\{\\{(.*?)\\}\\}";
            Pattern m_pattern = Pattern.compile(m_regex, Pattern.DOTALL);
            Matcher m_matcher = m_pattern.matcher(init_matcher.group(1));
            if(m_matcher.find()){
                while(m_matcher.find()) {
                    String meaning_regex = "\\|(.*?)\\|";
                    Pattern meaning_pattern = Pattern.compile(meaning_regex, Pattern.DOTALL);
                    Matcher meaning_matcher = meaning_pattern.matcher(m_matcher.group(1));
                    if (meaning_matcher.find()) {
                        String meaning = meaning_matcher.group(1);
                        String ans_regex = "\\[\\[(.*?)\\]\\]";
                        Pattern ans_pattern = Pattern.compile(ans_regex, Pattern.DOTALL);
                        Matcher ans_matcher = ans_pattern.matcher(m_matcher.group(1));
                        ArrayList<String> ans = new ArrayList<>();
                        while (ans_matcher.find()) {
                            ans.add(ans_matcher.group(1));
                        }
                        m_n_ans.put(meaning, ans);
                    } else {
                        String meaning = "No context provided";
                        String ans_regex = "\\[\\[(.*?)\\]\\]";
                        Pattern ans_pattern = Pattern.compile(ans_regex, Pattern.DOTALL);
                        Matcher ans_matcher = ans_pattern.matcher(m_matcher.group(1));
                        ArrayList<String> ans = new ArrayList<>();
                        while (ans_matcher.find()) {
                            ans.add(ans_matcher.group(1));
                        }
                        m_n_ans.put(meaning, ans);
                    }
                }
            }else{
                String meaning = "Single meaning for word ";
                String ans_regex = "\\[\\[(.*?)\\]\\]";
                Pattern ans_pattern = Pattern.compile(ans_regex, Pattern.DOTALL);
                Matcher ans_matcher = ans_pattern.matcher(init_matcher.group(1));
                ArrayList<String> ans = new ArrayList<>();
                while (ans_matcher.find()) {
                    ans.add(ans_matcher.group(1));
                }
                m_n_ans.put(meaning, ans);
            }
        }else{
            ArrayList<String> filler = new ArrayList<>();
            m_n_ans.put("No antonymes", filler);
        }
        this.ants = m_n_ans;
    }
}
