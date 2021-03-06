package edu.upm.midas.common.util;

import com.google.gson.Gson;
import edu.upm.midas.constants.Constants;
import edu.upm.midas.model.response.ProcessedText;
import edu.upm.midas.model.response.Text;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gerardo on 10/05/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project ExtractionInformationDiseasesWikipedia
 * @className Validations
 * @see
 */
@Component
public class Common {

    /**
     * @param jsonBody
     * @param snapshot
     * @param source
     * @throws IOException
     */
    public void writeJSONFile(String jsonBody, String snapshot, String source) throws IOException {
        String fileName = snapshot + Constants.UNDER_SCORE + source + Constants.MM_RETRIEVAL_FILE_NAME + Constants.DOT_JSON;
        String path = Constants.MM_RETRIEVAL_HISTORY_FOLDER + fileName;
        InputStream in = getClass().getResourceAsStream(path);
        //BufferedReader bL = new BufferedReader(new InputStreamReader(in));
        File file = new File(path);
        BufferedWriter bW;

        if (!file.exists()){
            bW = new BufferedWriter(new FileWriter(file));
            bW.write(jsonBody);
            bW.close();
        }
    }


    /**
     * @param snapshot
     * @return
     * @throws Exception
     */
    public List<Text> readMetamapResponseJSON(String snapshot, String source) throws Exception {
        List<Text> texts = new ArrayList<>();
        System.out.println("Read JSON!...");
        Gson gson = new Gson();
        String fileName = snapshot + "_" + source + Constants.MM_RETRIEVAL_FILE_NAME + Constants.DOT_JSON;
        String path = Constants.MM_RETRIEVAL_HISTORY_FOLDER + fileName;
        System.out.println("Read JSON!..." + path);

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            ProcessedText resp = gson.fromJson(br, ProcessedText.class);
            texts = resp.getTexts();
        }catch (Exception e){
            System.out.println("Error to read or convert JSON!...");
        }

        /*for (edu.upm.midas.data.validation.metamap.model.response.Text text: resp.getTexts()) {
            System.out.println("TextId: " + text.getId() + " | Concepts: " + text.getConcepts().toString());
        }*/
        //System.out.println("source: "+source);

        return texts;
    }

    public boolean isEmpty(String string) {
        if (string == null) {
            return true;
        }
        else {
            if (string.trim().equalsIgnoreCase("")) {
                return true;
            }
            else {
                return false;
            }

        }
    }

    public String getUnicode(char character){
        return "\\u" + Integer.toHexString(character | 0x10000).substring(1);
    }

    public String cutString(String str) {
        return str = str.substring(0, str.length()-2);
    }


    /**
     * @param cutStart
     * @param cutFinal
     * @param str
     * @return
     */
    public String cutStringPerformance(int cutStart, int cutFinal, String str) {
        return str = str.substring(cutStart, str.length() - cutFinal);
    }

    public String cutString(int cutStart, int cutFinal, String str) {
        return str = str.substring(cutStart, cutFinal);
    }


    public String replaceUnicodeToSpecialCharacters(String data){

        Pattern p = Pattern.compile("\\\\u(\\p{XDigit}{4})");
        Matcher m = p.matcher(data);
        StringBuffer buf = new StringBuffer(data.length());
        while (m.find()) {
            String ch = String.valueOf((char) Integer.parseInt(m.group(1), 16));
            m.appendReplacement(buf, Matcher.quoteReplacement(ch));
        }
        return m.appendTail(buf).toString();

    }

    public String replaceSpecialCharactersToUnicode(String text){
        return StringEscapeUtils.escapeJava(text);
    }



    public void removeRepetedElementsList(List<String> elementsList){
        Set<String> linkedHashSet = new LinkedHashSet<String>();
        linkedHashSet.addAll(elementsList);
        elementsList.clear();
        elementsList.addAll(linkedHashSet);
    }



    public boolean itsFound(String originalStr, String findStr){
//        System.out.println("RECIBE itsFound: ORI:" + originalStr + " | FIND: " + findStr);
        return originalStr.trim().indexOf(findStr.trim()) != -1;// Retorna true si ha encontrado la subcadena en la cadena
    }


}
