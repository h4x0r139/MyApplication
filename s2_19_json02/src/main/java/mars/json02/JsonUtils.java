package mars.json02;

import com.google.gson.Gson;


public class JsonUtils {
	
	public void parseUserFromJson(String jsonData){
		Gson gson = new Gson();
		User user = gson.fromJson(jsonData, User.class);
		System.out.println("name--->" + user.getName());
		System.out.println("age---->" + user.getAge());
		
	}
}
