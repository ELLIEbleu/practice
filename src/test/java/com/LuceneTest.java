package com;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAFDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Test;

import java.io.IOException;

public class LuceneTest {

    @Test
    public void givenSearchQueryWhenFetchedDocumentThenCorrect() throws ParseException, IOException {
//        InMemoryLuceneIndex inMemoryLuceneIndex = new InMemoryLuceneIndex();
        Query query = new QueryParser("", new StandardAnalyzer()).parse("");
        Directory memoryIndex = new RAMDirectory();
        IndexReader indexReader = DirectoryReader.open(memoryIndex);

        Term term = new Term("","");
        Query query1 = new WildcardQuery(term);

    }

    @Test
    public void createInMemoryIndex() throws IOException {
        Directory memoryIndex = new RAMDirectory();
        StandardAnalyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(memoryIndex,indexWriterConfig);
        Document document = new Document();

        document.add(new TextField("title","title", Field.Store.YES));
        document.add(new TextField("body","body",Field.Store.YES));

        writer.addDocument(document);
        writer.close();
    }

    @Test
    public void searchIndex(){
//        Query query = new QueryParser("test",)
    }

    @Test
    public void test(){

    }
}
