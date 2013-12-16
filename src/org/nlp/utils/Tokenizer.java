package org.nlp.utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by fangy on 13-12-16.
 *
 */
public class Tokenizer {

//    private String[] tokens;
//    int currentIndex;
    private List<String> tokens;
    private ListIterator<String> tokenIter;

    public Tokenizer(){
        tokens = new LinkedList<String>();
        tokenIter = tokens.listIterator();
    }

    public Tokenizer(String text, String delim){

        tokens = Arrays.asList(text.split(delim));
        tokenIter = tokens.listIterator();
    }

    public int size(){
        return tokens.size();
    }

    public boolean hasMoreTokens(){
        return tokenIter.hasNext();
    }

    public String nextToken(){

        return tokenIter.next();
    }

    public void add(String token){

        if (token == null){
            return;
        }

        tokens.add(token);
    }


    public String toString(String delim){
        StringBuilder sb = new StringBuilder();
        if (tokens.size() < 1){
            return sb.toString();
        }
        ListIterator<String> tempTokenIter = tokens.listIterator();
        sb.append(tempTokenIter.next());
        while (tempTokenIter.hasNext()){
            sb.append(" ").append(tempTokenIter.next());
        }

        return sb.toString();
    }

}
