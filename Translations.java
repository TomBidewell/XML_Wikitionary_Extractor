import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Translations implements Interface{

    private HashMap<String, HashMap<String, ArrayList<String>>> transl;


    public Object getValue(){
        return this.transl;
    }

    public void setValue(String input){
        String regex = "\\{\\{trad-début(.*?)\\{\\{trad-fin\\}\\}";
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);

        HashMap<String, HashMap<String, ArrayList<String>>> translations = new HashMap<>();

        while (matcher.find()) {
            String regex2 = "^(.*?)(?=\\}\\})";
            Pattern pattern2 = Pattern.compile(regex2, Pattern.DOTALL);
            Matcher matcher2 = pattern2.matcher(matcher.group(1));
            while(matcher2.find()){
                String meaning = matcher2.group(1); //wanna put this as key

                HashMap<String , ArrayList<String>> lang_trans = new HashMap<>();

                String reglang = "T\\|(.*?)\\}\\}";
                Pattern patternlang = Pattern.compile(reglang, Pattern.DOTALL);
                Matcher matcherlang = patternlang.matcher(matcher.group(1));


                while(matcherlang.find()){
                    String lang = matcherlang.group(1);
                    String regtrans = "\\|" + lang + "\\|(.*?)\\}\\}";
                    Pattern patterntrans = Pattern.compile(regtrans, Pattern.DOTALL);
                    Matcher matchertrans = patterntrans.matcher(matcher.group(1));
                    while (matchertrans.find()){
                        if (lang_trans.containsKey(lang)) {
                            String l = matchertrans.group(1);
                            if (l != null){
                                lang_trans.get(lang).add(l.split("\\|")[0]);
                            }
                        } else {
                            ArrayList<String> new_lang = new ArrayList<>();
                            String l = matchertrans.group(1);
                            if (l != null){
                                new_lang.add(l.split("\\|")[0]);
                                lang_trans.put(lang, new_lang);
                            }
                        }
                    }
                }
                if (meaning.length() == 0){
                    meaning = "No Meaning Provided";
                }
                translations.put(meaning.replace("|", ""), lang_trans);
            }
        }
        this.transl = translations;
    }
}
