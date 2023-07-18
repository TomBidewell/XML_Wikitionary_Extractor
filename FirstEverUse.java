import javax.xml.stream.XMLStreamException;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class FirstEverUse {

    public static void output_first(String[] args) throws IOException {
        //checking if we'll do repeat or not
        Scanner scanner = new Scanner(System.in);

        System.out.println("This is the first time you've ever run this program, it may take longer than usual. Will you be using this program more than once this session? (Y/N)");

        // Read the user's input
        String answer = scanner.nextLine();

        // Process the user's input
        if (answer.equalsIgnoreCase("Y")) {
            First(args, true);
        } else if (answer.equalsIgnoreCase("N")) {
            First(args, false);
        } else {
            // Invalid input, ask again or handle the error as appropriate
            System.out.println("Invalid input, please enter Y or N.");
        }
    }

    public static void First(String[] args, boolean repeat) {
        Command coms = new Command();
        ArrayList coms_arr = coms.getCommands(args);
        if(coms_arr.size() == 1){
            System.out.println(coms_arr.get(0));
            return;
        }

        String filename = coms_arr.get(0).toString();
        String word = coms_arr.get(1).toString();
        String what = coms_arr.get(2).toString();
        String into = coms_arr.get(3).toString();

        ContentClasses cs = new ContentClasses();

        HashMap<String, Interface> cl_arr = cs.getClasses();
        Interface what_class = cl_arr.get(what);

        try {
            boolean present = false;
            PageExtractor extractor = new PageExtractor(filename);
            Iterator<Page> it = extractor.iterator();
            HashMap<String, String> wiki = new HashMap<>();
            while (it.hasNext()) {
                //Main.npages ++;
                Page page = it.next();
                String l = null;

                //if we're creating the wikihashmap, only put in french words
                if(repeat){
                    Interface lang = cl_arr.get("language");
                    lang.setValue(page.content);
                    if (lang.getValue().equals("fr")){
                        l = lang.getValue().toString();
                        wiki.put(page.title, page.content);
                    }
                }

                if (page.title.equals(word)) {
                    present = true;

                    //if we're not repeating, we won't have already found the language so find it now
                    if(repeat == false){
                        Interface lang = cl_arr.get("language");
                        lang.setValue(page.content);
                        if (lang.getValue().equals("fr")){
                            l = lang.getValue().toString();
                        }
                    }

                    //above we only make the language not null if it's equal to fr, so if l is null, the lanugage is not french
                    if(l != null){
                        if (what.equals("translations")){

                            what_class.setValue(page.content);
                            Print_Translations t = new Print_Translations();
                            t.Print_Trans(what_class.getValue(), into);
                            if(repeat == false){
                                break;
                            }else{
                                System.out.println("Uploading content to make future searches quicker");
                            }

                        }else {
                            try{
                                what_class.setValue(page.content);
                                System.out.println(word + ": " + what + ": " + what_class.getValue());
                                if(repeat == false){
                                    break;
                                }else{
                                    System.out.println("Uploading content to make future searches quicker");
                                }
                            }catch(NullPointerException e){
                                System.out.println("Specific request not possible with this program");
                                if(repeat == false){
                                    break;
                                }
                            }

                        }
                    }else {
                        System.out.println("Word not in French");
                    }
                }
            }
            if (present == false){
                System.out.println("Word not in dictionary");
            }
            try{
                if(repeat){
                    FileOutputStream outputStream = new FileOutputStream("wiki.bin");
                    BufferedOutputStream bos = new BufferedOutputStream(outputStream);
                    ObjectOutputStream oos = new ObjectOutputStream(bos);
                    oos.writeObject(wiki);
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }catch (IOException | XMLStreamException e) {
            System.err.println("Error reading XML dump file: " + e.getMessage());
        }
    }
}
