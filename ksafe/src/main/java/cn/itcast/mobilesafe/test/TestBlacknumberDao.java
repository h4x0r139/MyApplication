package cn.itcast.mobilesafe.test;

import java.util.List;

import cn.itcast.mobilesafe.db.dao.BlackNumberDao;
import cn.itcast.mobilesafe.domain.BlackNumber;
import android.test.AndroidTestCase;

public class TestBlacknumberDao extends AndroidTestCase {

	public void testAdd() throws Exception{
		BlackNumberDao dao = new BlackNumberDao(getContext());
		long number =1351234567;
		for(int i=0;i<9;i++){
		   dao.add(number+i+"","张三"+i);
		}
	}
	
	public void testfindall() throws Exception{
		BlackNumberDao dao = new BlackNumberDao(getContext());
		List<BlackNumber> numbers = dao.getAllNumbers();
		System.out.println(numbers.size());
		assertEquals(9, numbers.size());
		
		
	}
	
	public void testDelete() throws Exception{
		BlackNumberDao dao = new BlackNumberDao(getContext());
		dao.delete("张三");
	}
	
	public void testupdate() throws Exception{
		BlackNumberDao dao = new BlackNumberDao(getContext());
		dao.update("1351234575", "1351231111");
	}
}
