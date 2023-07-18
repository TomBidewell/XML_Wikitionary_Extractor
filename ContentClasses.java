import java.util.HashMap;

public class ContentClasses {

    public HashMap<String, Interface> getClasses(){
        HashMap<String, Interface> dict = new HashMap<>();

        Antonymes a = new Antonymes();
        dict.put("antonymes", a);

        Derives d = new Derives();
        dict.put("dérivés", d);

        Hyperonymes he = new Hyperonymes();
        dict.put("hyperonymes", he);

        Hyponymes ho = new Hyponymes();
        dict.put("hyponymes", ho);

        Language l = new Language();
        dict.put("language", l);


        POS pos = new POS();
        dict.put("pos", pos);

        Pronunciation pr = new Pronunciation();
        dict.put("pronunciation", pr);

        Synonymes s = new Synonymes();
        dict.put("synonymes", s);

        Translations t = new Translations();
        dict.put("translations", t);

        return dict;
    }


}
