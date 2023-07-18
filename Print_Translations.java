import java.util.ArrayList;
import java.util.HashMap;

public class Print_Translations {
    public void Print_Trans(Object obj, String into){
        HashMap<String, HashMap<String, ArrayList<String>>> input = (HashMap<String, HashMap<String, ArrayList<String>>>) obj;

        for (String meaning: input.keySet()){
            System.out.println(meaning + ": ");
            HashMap<String, ArrayList<String>> translations = input.get(meaning);
            if (into.length() == 0){
                for (String lang: translations.keySet()){
                    ArrayList<String> words = translations.get(lang);
                    System.out.println(lang + " --> " + words);
                }
            }else{
                ArrayList<String> sg_trans = translations.get(into);
                System.out.println(into + " --> " + sg_trans);

            }
        }
    }
}
