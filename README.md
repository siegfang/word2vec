# word2vec的Java并行实现

## the Parallel Implementation of word2vec in Java

参考了其它语言的并行实现方法，包括：
 - Tomas Mikolov的C实现 [Google Code](https://code.google.com/p/word2vec/)
 - jdeng的C++实现 [GitHub](https://github.com/jdeng/word2vec)
 - piskvorky的Python实现 [GibHub](https://github.com/piskvorky/gensim)
 - ansj的Java实现 [GitHub](https://github.com/ansjsun/Word2VEC_java)

使用**Java 7**编写，读取的语料需先行**分词**完毕，并以空格分隔，由于低频词(<5)会被过滤，故建议使用较大的文本数据集。，例子见[TestWord2Vec](https://github.com/siegfang/word2vec/blob/master/src/test/TestWord2Vec.java)

write in Java 7, the word in input text should be segmented by one space。This program will filter the low frequency(<5) word, so please use large text dataset. Demo can be found in [TestWord2Vec](https://github.com/siegfang/word2vec/blob/master/src/test/TestWord2Vec.java)

![word2vec](http://images.cnblogs.com/cnblogs_com/siegfang/543577/o_word2vec_diagram.png)

