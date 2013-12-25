package org.nlp.util;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by fangy on 13-12-19.
 * 输入流的行迭代器
 * 改造自org.apache.commons.io。LineIterator
 */
public class LineIterator implements Iterator<String> {

    /** 缓存输入流 */
    private final BufferedReader bufferedReader;
    /** 当前读取的行 */
    private String cachedLine;
    /** 标识输入流是否已经读入完. */
    private boolean finished = false;

    /**
     * 构造函数
     *
     * @param reader 将要读取的输入流，不能为null
     * @throws IllegalArgumentException 当reader为null时抛出
     */
    public LineIterator(final Reader reader) throws IllegalArgumentException {
        if (reader == null) {
            throw new IllegalArgumentException("输入流不可为null");
        }
        if (reader instanceof BufferedReader) {
            bufferedReader = (BufferedReader) reader;
        } else {
            bufferedReader = new BufferedReader(reader);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * 标识输入流中是否还有行可供读入，如果程序产生了<code>IOException</code>，
     * close()将会被调用，以关闭输入流，并抛出<code>IllegalStateException</code>。
     *
     * @return 若还有行可供读入，则返回{@code true}，否则返回{@code false}
     * @throws IllegalStateException 当有IO异常产生时
     */
    public boolean hasNext() {
        if (cachedLine != null) {
            return true;
        } else if (finished) {
            return false;
        } else {
            try {
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        finished = true;
                        return false;
                    } else if (isValidLine(line)) {
                        cachedLine = line;
                        return true;
                    }
                }
            } catch(IOException ioe) {
                close();
                throw new IllegalStateException(ioe);
            }
        }
    }

    /**
     * 验证字符串，这里的实现是直接返回true
     * @param line  待验证的字符串行
     * @return 符合条件的字符串返回 {@code true}，否则返回{@code false}
     */
    protected boolean isValidLine(String line) {
        return true;
    }

    /**
     * 从 <code>Reader</code> 中读取一行.
     *
     * @return 输入流中的下一行
     * @throws NoSuchElementException 没有行可读入时抛出
     */
    public String next() {
        return nextLine();
    }

    /**
     * 从 <code>Reader</code> 中读取一行
     *
     * @return 从输入流中读取的一行
     * @throws NoSuchElementException 如果没有行可读入
     */
    public String nextLine() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more lines");
        }
        String currentLine = cachedLine;
        cachedLine = null;
        return currentLine;
    }

    /**
     * 关闭<code>Reader</code>
     * 如果你只想读取一个大文件的头几行，那么这个函数可以
     * 帮助你关闭输入流。如果没有调用close函数，那么
     * <code>Reader</code>将保持打开的状态。这一方法可以
     * 安全地多次调用。
     */
    public void close() {
        finished = true;
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cachedLine = null;
    }

    /**
     * 不支持的操作
     *
     * @throws UnsupportedOperationException 每次调用都会抛出
     */
    public void remove() {
        throw new UnsupportedOperationException("Remove unsupported on LineIterator");
    }

    //-----------------------------------------------------------------------
    /**
     * 关闭迭代器中的输入流，检查是否为null，忽略异常
     *
     * @param iterator  将要被关闭的迭代器
     */
    public static void closeQuietly(LineIterator iterator) {
        if (iterator != null) {
            iterator.close();
        }
    }

}
