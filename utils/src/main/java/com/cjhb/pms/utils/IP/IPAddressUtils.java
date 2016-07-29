package com.cjhb.pms.utils.IP;/*
* Copyright (c) 2016 www.51cjhb.com. All Rights Reserved.
*/

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <p>Title:请求ip处理类 </p>
 * <p>Description:获取访问ip等相关操作 </p>
 * @author edgar
 * @version 1.0
 * @create 2016 07 29 10:40
 */
public class IPAddressUtils {

	//ip处理类日志记录
	private static Logger log = Logger.getLogger(IPAddressUtils.class);

	/*
		 在jsp里，获取客户端的ip地址的方法是：request.getRemoteAddr()，这种方法在大部分情况下都是有效的。
		但是在通过了Apache,Squid等反向代理软件就不能获取到客户端的真实IP地址了。如果使用了反向代理软件，
		用request.getRemoteAddr()方法获取的IP地址是：127.0.0.1或192.168.1.110，而并不是客户端的真实ＩＰ。
		经过代理以后，由于在客户端和服务之间增加了中间层，因此服务器无法直接拿到客户端的 IP，
		服务器端应用也无法直接通过转发请求的地址返回给客户端。但是在转发请求的HTTP头信息中，增加了X－FORWARDED－FOR信息。
		用以跟踪原有的客户端IP地址和原来客户端请求的服务器地址。当我们访问index.jsp/时，其实并不是我们浏览器真正访问到了服务器上的index.jsp文件，
		而是先由代理服务器去访问index.jsp ，代理服务器再将访问到的结果返回给我们的浏览器，因为是代理服务器去访问index.jsp的，
		所以index.jsp中通过request.getRemoteAddr()的方法获取的IP实际上是代理服务器的地址，并不是客户端的IP地址。

		如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
		答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。如：
		X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 192.168.1.100
		用户真实IP为： 192.168.1.110
	*/
	/**
	 * 获得客户端真实IP地址的方法一
	 * @param request
	 * @return
	 */
	public static String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}

	/**
	 * 获得客户端真实IP地址的方法二
	 * @param request
	 * @return
	 */
	public static String getIPAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unkonwn".equalsIgnoreCase(ip)) {
			ip = request.getHeader("PRoxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unkonwn".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unkonwn".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unkonwn".equalsIgnoreCase(ip)) {
			ip = request.getHeader("http_client_ip");
		}
		if (ip == null || ip.length() == 0 || "unkonwn".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDEC_FOR");
		}
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		}
		return ip;
	}

	/**
	 *
	 * @param content
	 *            请求的参数 格式为：name=xxx&pwd=xxx
	 * @param encodingString
	 *            服务器端请求编码字符串。如GBK,UTF-8等
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getPosition(String content, String encodingString) {
		try {
			//这里调用淘宝IP地址库 也可以使用 pconline的接口
			// 从http://whois.pconline.com.cn取得IP所在的省市区信息
			String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
			//获取查询json字符串结果
			String returnStr = getResult(urlStr, content, encodingString);
			if (returnStr != null) {
				// 处理返回的省市区信息
				log.info("result:" + returnStr);
				String[] temp = returnStr.split(",");
				if (temp.length < 3) {
					return "0";// 无效IP，局域网测试
				}

				String country = "";
				String area = "";
				String region = "";
				String city = "";
				String county = "";
				String isp = "";
				for (int i = 0; i < temp.length; i++) {
					switch (i) {
						case 1:
							country = (temp[i].split(":"))[2].replaceAll("\"", "");
							country = decodeUnicode(country);// 国家
							break;
						case 3:
							area = (temp[i].split(":"))[1].replaceAll("\"", "");
							area = decodeUnicode(area);// 地区
							break;
						case 5:
							region = (temp[i].split(":"))[1].replaceAll("\"", "");
							region = decodeUnicode(region);// 省份
							break;
						case 7:
							city = (temp[i].split(":"))[1].replaceAll("\"", "");
							city = decodeUnicode(city);// 市区
							break;
						case 9:
							county = (temp[i].split(":"))[1].replaceAll("\"", "");
							county = decodeUnicode(county);// 地区
							break;
						case 11:
							isp = (temp[i].split(":"))[1].replaceAll("\"", "");
							isp = decodeUnicode(isp); // ISP公司
							break;
					}
				}
				return country + "," + area + "," + region + "," + city + "," + county + "," + isp;
			}
			return null;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * @param urlStr
	 *            请求的地址
	 * @param content
	 *            请求的参数 格式为：name=xxx&pwd=xxx
	 * @param encoding
	 *            服务器端请求编码。如GBK,UTF-8等
	 * @return
	 */
	private static String getResult(String urlStr, String content, String encoding) {
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();// 新建连接实例
			connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
			connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
			connection.setDoOutput(true);// 是否打开输出流 true|false
			connection.setDoInput(true);// 是否打开输入流true|false
			connection.setRequestMethod("POST");// 提交方法POST|GET
			connection.setUseCaches(false);// 是否缓存true|false
			connection.connect();// 打开连接端口
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());// 打开输出流往对端服务器写数据
			out.writeBytes("ip=" + content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
			out.flush();// 刷新
			out.close();// 关闭输出流
			// 往对端写完数据对端服务器返回数据,以BufferedReader流来读取
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			if (connection != null) {
				connection.disconnect();// 关闭连接
			}
		}
		return null;
	}

	/**
	 * unicode码 转换成 中文字符串
	 * @param theUnicodeString
	 * @return
	 */
	public static String decodeUnicode(String theUnicodeString) {
		char aChar;
		int len = theUnicodeString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theUnicodeString.charAt(x++);
			if (aChar == '\\') {
				aChar = theUnicodeString.charAt(x++);
				if (aChar == 'u') {
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theUnicodeString.charAt(x++);
						switch (aChar) {
							case '0':
							case '1':
							case '2':
							case '3':
							case '4':
							case '5':
							case '6':
							case '7':
							case '8':
							case '9':
								value = (value << 4) + aChar - '0';
								break;
							case 'a':
							case 'b':
							case 'c':
							case 'd':
							case 'e':
							case 'f':
								value = (value << 4) + 10 + aChar - 'a';
								break;
							case 'A':
							case 'B':
							case 'C':
							case 'D':
							case 'E':
							case 'F':
								value = (value << 4) + 10 + aChar - 'A';
								break;
							default:
								throw new IllegalArgumentException("Malformed      encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't') {
						aChar = '\t';
					} else if (aChar == 'r') {
						aChar = '\r';
					} else if (aChar == 'n') {
						aChar = '\n';
					} else if (aChar == 'f') {
						aChar = '\f';
					}
					outBuffer.append(aChar);
				}
			} else {
				outBuffer.append(aChar);
			}
		}
		return outBuffer.toString();
	}



}
