package config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class LevelConfig {
	private DataConfig dataConfig;
	private String fileName;
	
	public LevelConfig(String fileName) throws Exception{
		SAXReader reader = new SAXReader();
		String file = "config/" + fileName + ".xml";
		Document doc;
		
		if(new File(file).exists()){
			doc = reader.read(file);
			this.fileName = fileName;
		}else{
			doc = reader.read("config/edit.xml");
			this.fileName = "edit";
		}
		Element level = doc.getRootElement();
		Element data = level.element("gamedata");
		this.dataConfig = new DataConfig(data);
	}

	public DataConfig getDataConfig() {
		return dataConfig;
	}
	
	public String getFileName(){
		return fileName;
	}
}
