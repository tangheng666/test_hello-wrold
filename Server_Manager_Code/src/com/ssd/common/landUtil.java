package com.ssd.common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
 
import com.ssd.controller.Ssd_soilController;
import com.ssd.po.Thedrop_down_list;

public class landUtil {

	// 查询所有的土地类型
	public static List<Thedrop_down_list> readvMap(String filepath) {

		Properties properties = new Properties();

		try {
			URL url = Ssd_soilController.class.getClassLoader().getResource(
					filepath);// 注意
			// 2.通过url得到文件的路径
			String path = url.getPath();
			FileInputStream fis = new FileInputStream(path);
			properties.load(fis);

			List<Thedrop_down_list> list = new ArrayList<Thedrop_down_list>();
			Enumeration enume = properties.propertyNames(); // 获取资源名字
			while (enume.hasMoreElements()) {
				Thedrop_down_list thedrop_down_list = new Thedrop_down_list();
				String key = enume.nextElement().toString();
				thedrop_down_list.setId(key);
				String value = properties.getProperty(key.toString());
				thedrop_down_list.setName(value);
				list.add(thedrop_down_list);

			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	// 根据土地的key拿值
	public static String readValue(String filePath, String key) {
		Properties p = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					filePath));// 拿取路径
			p.load(in);
			String value = p.getProperty(key);
			return value;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	// 解析xml 省份
	public static List<Thedrop_down_list> province(String path)
			throws FileNotFoundException {
		SAXReader reader = new SAXReader();

		Document document;
		URL url = Ssd_soilController.class.getClassLoader().getResource(path);
		try {
			document = reader.read(url);
			Element element = document.getRootElement();
			System.out.println(element.getName());

			List<Element> nodes = element.elements("Province");
			List<Thedrop_down_list> ae = new ArrayList<Thedrop_down_list>();
			for (Element node : nodes) {
				Thedrop_down_list tr = new Thedrop_down_list();
				tr.setId(node.attributeValue("ID"));
				tr.setName(node.attributeValue("Name"));
				ae.add(tr);
				System.out.println(node.attributeValue("ID") + ":"
						+ node.attributeValue("Name"));
			}
			return ae;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}
	}

	// 根据省份id查询该地区
	public static List<Thedrop_down_list> area(String ID, String path) {
		SAXReader reader = new SAXReader();

		URL url = Ssd_soilController.class.getClassLoader().getResource(path);
		Document document;
		try {
			document = reader.read(url);

			Element element = document.getRootElement();
			System.out.println(element.getName());
			List<Thedrop_down_list> ae = new ArrayList<Thedrop_down_list>();
			List<Element> list1 = document.selectNodes("//Province[@ID='"+ID+"']/City");
			for (Element n : list1) {
				Thedrop_down_list ko = new Thedrop_down_list();

				ko.setId(n.attribute("ID").getText());
				ko.setName(n.getStringValue());
				// 打印元素中属性为id的值
				System.out.println("City ID = " + n.attribute("ID").getText()
						+ "" + n.getStringValue());
            ae.add(ko);
			}
			return ae;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
