package test;

import org.nlp.util.LineIterator;
import org.nlp.util.Tokenizer;
import org.nlp.vec.VectorModel;
import org.nlp.vec.Word2Vec;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author siegfang
 */
public class TestWord2Vec {

    public static void readByUtil(){
        Word2Vec wv = new Word2Vec.Factory().build();

        String textFilePath = "Z:\\DataDeport\\novel_4j.txt";

        LineIterator li = null;
        try {

            li = new LineIterator(
                    new InputStreamReader(new FileInputStream(textFilePath)));
            int lineCount = 0;
            while (li.hasNext()){
                wv.readTokens(new Tokenizer(li.nextLine(), " "));
                lineCount ++;
                if (lineCount > 2000){
                    break;
                }
            }

        } catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        } finally{
            LineIterator.closeQuietly(li);
        }

        wv.training();
    }

    public static void readByJava(){

        Word2Vec wv = new Word2Vec.Factory()
                .setMethod(Word2Vec.Method.Skip_Gram)
                .setNumOfThread(4).build();

        String textFilePath = "D:\\library\\novel_4j.txt";

//        BufferedReader br = null;
        try (BufferedReader br =
                     new BufferedReader(new FileReader(textFilePath))) {
            int lineCount = 0;
            for (String line = br.readLine(); line != null;
                    line = br.readLine()){
                wv.readTokens(new Tokenizer(line, " "));
//                System.out.println(line);
                lineCount ++;
//                if (lineCount > 200){
//                    break;
//                }
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        wv.training();
        wv.saveModel(new File("D:\\model\\novel_4j.nn"));
    }

    public static void testVector(){

        VectorModel vm = VectorModel.loadFromFile(
                "D:\\model\\novel_4j.nn");
        Set<VectorModel.WordScore> result1 = Collections.emptySet();
        Long startTime, endTime;
        Set<VectorModel.WordScore> result2 = Collections.emptySet();

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++){
//            result2 = vm.similar("脚");
            result2 = vm.analogy("水", "火", "天");
        }
        endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

//        result1 = vm.similar("脚");
//        System.out.println(result1);
//        System.out.println(result1.size());
//
//        result2 = vm.analogy("水", "火", "天");
        System.out.println(result2);
        //
//        System.out.println(result.size());
//        for (VectorModel.WordScore we : result2){
//            System.out.println(we.name + " : " + we.score);
//        }
    }

    public static void main(String[] args){

//        readByJava();
        testVector();
    }

}
