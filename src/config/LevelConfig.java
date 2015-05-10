package config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class LevelConfig {
	private ArrayList<DataConfig> dataConfig;
	
	public LevelConfig() throws Exception{
		SAXReader reader = new SAXReader();
		Document doc = reader.read("config/levelCfg.xml");
		Element level = doc.getRootElement();

		this.dataConfig = new ArrayList<DataConfig>();
		
		@SuppressWarnings("unchecked")
		List<Element> datas = level.elements("gamedata");
		for (Element data : datas) {
			DataConfig dc = new DataConfig(data);
		
			this.dataConfig.add(dc);
		}
	}

	public ArrayList<DataConfig> getDataConfig() {
		return dataConfig;
	}
}
