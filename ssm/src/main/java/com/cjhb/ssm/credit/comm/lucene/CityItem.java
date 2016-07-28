/**
* 
*
*@author：
*@since： JDK1.6
*@version：1.0
*@date：2015年6月3日 下午3:31:14
*/ 

package com.cjhb.ssm.credit.comm.lucene;

/**
 * @ClassName: CityItem
 * @Description: CityItem
 * @author 
 * @date 2015年6月3日 下午3:31:14
 *
 */
public class CityItem{

	private String id;
	
	private String text;
	
	private String content;

	private String level;
	
	private String pinyin;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * @return the pinyin
	 */
	public String getPinyin() {
		return pinyin;
	}

	/**
	 * @param pinyin the pinyin to set
	 */
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "id:" + this.id + "\n" +
				"text:" + this.text + "\n" +
				"content:" + this.content + "\n" +
				"level" + this.level + "\n" +
				"pinyin:" + this.pinyin + "\n";
	}
	
}
