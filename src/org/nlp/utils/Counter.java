package org.nlp.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * User: fangy 13-12-14
 * 计数器
 * @author siegfang
 * @param <T> 待计数的对象类型
 */
public class Counter<T> {

    // 计数使用的哈希表
    private Map<T, CountInteger> countMap = null;

    /**
     * 计数使用的可变整数类型，提升计数效率
     */
    private class CountInteger{

        int count;

        public CountInteger(int num){
            count = num;
        }

        public CountInteger(){
            count = 0;
        }

        public void set(int num){
            count = num;
        }

        public int value(){
            return count;
        }

        @Override
        public String toString() {
            return "Count=" + count;
        }
    }

    public Counter(){
        countMap = new HashMap<T, CountInteger>();
    }

    public Counter(int initialCapacity){
        countMap = new HashMap<T, CountInteger>(initialCapacity);
    }

    /**
     * 计数
     * @param t 待计数的对象
     */
    public void count(T t){

        CountInteger newCount = new CountInteger(1);
        CountInteger oldCount = countMap.put(t, newCount);
        // 若无此对象，则返回null
        if (oldCount != null){
            newCount.set(oldCount.value() + 1);
        }
    }

    /**
     * 删除某一对象
     * @param t 待删除的对象
     * @return 对象的数目
     */
    public int remove(T t){
        return countMap.remove(t).value();
    }

    /**
     * 计数器中已计数的对象集合的大小
     * @return 对象集合
     */
    public int size(){
        return countMap.size();
    }

    /**
     * 获取某一对象的数目
     * @param t 对象
     * @return 数目
     */
    public int get(T t){
        CountInteger countNum = countMap.get(t);
        if (countNum == null){
            return 0;
        } else {
            return countNum.value();
        }
    }

    /**
     * 计数器中已计数的对象集合
     * @return
     */
    public Set<T> keySet(){
        return countMap.keySet();
    }

    /**
     * 小测试
     * @param args 命令行参数
     */
    public static void main(String[] args){
        String[] words = {"1", "2", "4", "3", "3","1", "2", "4", "4", "3", "3"};
        Counter<String> wordCounter = new Counter<String>();
        for (String word : words){
            wordCounter.count(word);
        }

        for (String keyWord : wordCounter.keySet()){
            System.out.println(keyWord  + " : " + wordCounter.get(keyWord));
        }
        System.out.println("the size of counter : " + wordCounter.size());
        wordCounter.remove("4");
        for (String keyWord : wordCounter.keySet()){
            System.out.println(keyWord  + " : " + wordCounter.get(keyWord));
        }
    }

}
