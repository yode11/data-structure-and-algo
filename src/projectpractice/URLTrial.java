package projectpractice;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * EasyExcel里面，对于输出文件名字的格式处理
 *
 */
public class URLTrial {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String fileName = URLEncoder.encode("服务者区域配置YYYYMMDD", "UTF-8").replaceAll("\\+", "%20");
		String f = URLEncoder.encode("localhost:8081/a rea+d/ddd", "UTF-8").replaceAll("\\+", "%20");
		System.out.println(fileName);
		System.out.println(f);
	}
}
