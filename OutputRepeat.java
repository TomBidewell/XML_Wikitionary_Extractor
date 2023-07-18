import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class OutputRepeat {
    public static void output_repeat(String[] args){
        try {
            FileInputStream inputStream = new FileInputStream("wiki.bin");
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            ObjectInputStream ois = new ObjectInputStream(bis);
            HashMap<String, String> wiki = (HashMap<String, String>) ois.readObject();
            ois.close();
            repeat(wiki, args);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void repeat(HashMap<String, String> wiki, String[] args) throws IOException {
        Command coms = new Command();
        ArrayList coms_arr = coms.getCommands(args);
        if(coms_arr.size() == 1){
            System.out.println(coms_arr.get(0));
            return;
        }

        String word = coms_arr.get(1).toString();
        String what = coms_arr.get(2).toString();
        String into = coms_arr.get(3).toString();

        ContentClasses cs = new ContentClasses();
        HashMap<String, Interface> cl_arr = cs.getClasses();
        Interface what_class = cl_arr.get(what);
        if (wiki.containsKey(word)){

            String content = wiki.get(word);

            if (what.equals("translations")){

                what_class.setValue(content);
                Print_Translations t = new Print_Translations();
                t.Print_Trans(what_class.getValue(), into);

            }else {
                try{
                    what_class.setValue(content);
                    System.out.println(word + ": " + what + ": " + what_class.getValue());
                }catch(NullPointerException e){
                    System.out.println("Specific request not possible with this program");
                }

            }
        }else{
            System.out.println("Word not in French in this dictionary");
        }
    }
}
