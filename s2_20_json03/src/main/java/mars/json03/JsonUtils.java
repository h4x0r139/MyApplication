package mars.json03;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class JsonUtils {
	
	public void parseUserFromJson(String jsonData){
		Type listType = new TypeToken<LinkedList<User>>(){}.getType();
		Gson gson = new Gson();
		LinkedList<User> users = gson.fromJson(jsonData, listType);
		for (Iterator iterator = users.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			System.out.println("name--->" + user.getName());
			System.out.println("age---->" + user.getAge());
		}
		
	}
}
