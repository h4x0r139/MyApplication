package mars.json01;

import com.google.gson.stream.JsonReader;

import java.io.StringReader;

public class JsonUtils {
	public void parseJson(String jsonData){
		try{
			//如果需要解析JSON数据，首要要生成一个JsonReader对象
			JsonReader reader = new JsonReader(new StringReader(jsonData));
			reader.beginArray();
			while(reader.hasNext()){
				reader.beginObject();
				while(reader.hasNext()){
					String tagName = reader.nextName();
					if(tagName.equals("name")){
						System.out.println("name--->" + reader.nextString());
					}
					else if(tagName.equals("age")){
						System.out.println("age--->" + reader.nextInt());
					}
				}
				reader.endObject();
			}
			reader.endArray();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
