/**
 * 
 *
 *@author：
 *@since： JDK1.6
 *@version：1.0
 *@date：2015年6月3日 下午2:28:16
 */

package com.cjhb.ssm.credit.comm.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;

/**
 * @ClassName: BuildLuceneIndex
 * @Description: 构建索引
 * @author 
 * @date 2015年6月3日 下午2:28:16
 * 
 */
public class BuildLuceneIndex {
	
	/**
	 * @author: kevin
	 * @date: 2015年6月5日 下午4:27:07
	 * @Description: 构建城市索引
	 * @param directory
	 * @param items
	 * @return
	 */
	public static boolean buildCityIndexer(Directory directory,List<CityItem> items) {
		Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_47);
		IndexWriter iwriter = null;
		
		FieldType indexType = new FieldType();  
		indexType.setIndexed(true);//set 是否索引  
		indexType.setStored(true);//set 是否存储  
		indexType.setTokenized(false);//set 是否分类  
		
		FieldType unindexType = new FieldType();  
		unindexType.setIndexed(false);//set 是否索引  
		unindexType.setStored(true);//set 是否存储  
		unindexType.setTokenized(true);//set 是否分类
		
		try {
			iwriter = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_47, analyzer));
			// 删除所有document
			iwriter.deleteAll();
			// 将文档信息存入索引
			Document doc[] = new Document[items.size()];
			
			for (int i = 0; i < items.size(); i++) {
				doc[i] = new Document();
				CityItem item = items.get(i);

				doc[i].add(new Field("id", item.getId(),unindexType));
				doc[i].add(new Field("text", item.getText(),indexType));
				doc[i].add(new Field("level", item.getLevel(),unindexType));
				doc[i].add(new Field("pinyin", item.getPinyin(),indexType));
				
				iwriter.addDocument(doc[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				iwriter.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	public static List<CityItem> searchCityIndexer(Directory directory, String keyword) {
		Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_47);
		DirectoryReader ireader = null;
		List<CityItem> result = new ArrayList<CityItem>();
		try {
			ireader = DirectoryReader.open(directory);
			IndexSearcher isearcher = new IndexSearcher(ireader);
			String[] multiFields = {"text","pinyin"};
			
			MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_47, multiFields, analyzer);
			Query query = parser.parse(keyword);
			ScoreDoc[] hits = isearcher.search(query, null, 10).scoreDocs;
			for (int i = 0; i < hits.length; i++) {
				Document hitDoc = isearcher.doc(hits[i].doc);
				CityItem item = new CityItem();
				for (String field : multiFields) {
					String setMethodName = "set" + toFirstLetterUpperCase(field);
					item.getClass().getMethod(setMethodName, String.class).invoke(item, hitDoc.get(field));
				}
				result.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				ireader.close();
			} catch (IOException e) {
			}
		}
		return result;
	}
	
	public static String toFirstLetterUpperCase(String str) {
		if (str == null || str.length() < 2) {
			return str;
		}
		return str.substring(0, 1).toUpperCase()+ str.substring(1, str.length());
	}
	
}
