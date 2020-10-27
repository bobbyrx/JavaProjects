import javax.sound.midi.Soundbank;
import java.lang.reflect.Array;
import java.util.Arrays;

public class SandwichExtractor {

    public static String[] extractIngredients(String sandwich){
        String[] newString=sandwich.split("-");
        int countTwoBread=0;
        int countIndex=0;
        for (String value : newString) {
            if (value.contains("bread")) {
                countTwoBread++;
                String element;
                if (countTwoBread == 1) {
                    int newIndex = value.indexOf("bread") + 5;
                    element = value.substring(newIndex);
                } else {
                    int newIndex = value.indexOf("bread");
                    element = value.substring(0, newIndex);
                }
                if (element.length() > 0&&!element.equals("olives")) countIndex++;
            } else if (countTwoBread == 1) {
                if (value.length() > 0&&!value.equals("olives")) countIndex++;
            }
        }
        if(countIndex==0||countTwoBread<2)return new String[]{};
        String[] returnString=new String[countIndex];
        countIndex=0;
        countTwoBread=0;
        for (String s : newString) {
            if (s.contains("bread")) {
                countTwoBread++;
                String element;
                if (countTwoBread == 1) {
                    int newIndex = s.indexOf("bread") + 5;
                    element = s.substring(newIndex);
                } else {
                    int newIndex = s.indexOf("bread");
                    element = s.substring(0, newIndex);
                }
                if (element.length() > 0&&!element.equals("olives")) {
                    returnString[countIndex] = element;
                    countIndex++;
                }
            } else if (countTwoBread == 1) {
                if (s.length() > 0&&!s.equals("olives")) {
                    returnString[countIndex] = s;
                    countIndex++;
                }
            }
        }
        Arrays.sort(returnString);
        return returnString;
    }
}
