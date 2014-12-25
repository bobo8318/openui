package org.hao.iRobot;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * sin 2013-8-5
 * @author Administrator
 * 分词
 */
public class WordsDivid {

	/**
	 * sin 2013-8-5
	 * 中文分词
	 */
	public static List<String> getWordsDivid(String source){
		//System.out.println("source："+source);
		//StringBuffer sb = new StringBuffer();
		StringReader sr = new StringReader(source);
		List<String> keys = new ArrayList<String>();
		Analyzer analyzer = new IKAnalyzer(true);
		try {
			TokenStream ts = analyzer.tokenStream("",source);
			CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
			while(ts.incrementToken()){
				keys.add(term.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		/*IKSegmenter ik = new IKSegmenter(sr,true);
		Lexeme lx = null;
		
		try {
			while((lx=ik.next())!=null){
				sb.append(lx.getLexemeText());
				sb.append("|");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		sr.close();
		//System.out.println("keywords："+sb.toString());
		//String[] keywords = sb.toString().split("|");
		return keys;
	}
}
