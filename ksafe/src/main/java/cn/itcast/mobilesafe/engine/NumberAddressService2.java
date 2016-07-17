package cn.itcast.mobilesafe.engine;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.itcast.mobilesafe.db.dao.AddressDao;

public class NumberAddressService2 {
	/**
	 * 
	 * @param number
	 *            要查询的电话号码
	 * @return 电话号码的归属地
	 */
	public static String getAddress(String number) {
		String pattern = "^1[3458]\\d{9}$";
		String address = number;
		if (number.matches(pattern)) { // 手机号码

			SQLiteDatabase db = AddressDao.getAddressDB("/sdcard/address.db");
			if (db.isOpen()) {
				Cursor cursor = db.rawQuery(
						"select city from info where mobileprefix=?",
						new String[] { number.substring(0, 7) });
				if (cursor.moveToNext()) {
					address = cursor.getString(0);
				}
				cursor.close();
				db.close();

			}

		} else { // 固定电话
			int len = number.length();
			SQLiteDatabase db ;
			switch (len) {
			case 4: //模拟器
				address = "模拟器";
				break;
			case 7: //本地号码
				address = "本地号码";
				break;
			case 8: //本地号码
				address = "本地号码";
				break;
			case 10: //3位区号 + 7位号码
				//select city from info where area='010' limit 1
				db = AddressDao.getAddressDB("/sdcard/address.db");
				if (db.isOpen()) {
					Cursor cursor = db.rawQuery(
							"select city from info where area=? limit 1",
							new String[] { number.substring(0, 3) });
					if (cursor.moveToNext()) {
						address = cursor.getString(0);
					}
					cursor.close();
					db.close();

				}
				
				break;
			case 11: //3位区号 + 8位号码 4位区号+7位号码
				//select city from info where area='010' limit 1
				SQLiteDatabase db2 = AddressDao.getAddressDB("/sdcard/address.db");
				if (db2.isOpen()) {
					Cursor cursor = db2.rawQuery(
							"select city from info where area=? limit 1",
							new String[] { number.substring(0, 3) });
					if (cursor.moveToNext()) {
						address = cursor.getString(0);
					}
					Cursor cursor2 = db2.rawQuery(
							"select city from info where area=? limit 1",
							new String[] { number.substring(0, 4) });
					if (cursor2.moveToNext()) {
						address = cursor2.getString(0);
					}
					
					cursor2.close();
					cursor.close();
					db2.close();

				}
				
				break;
			case 12: //4位区号 +8位号码
				//select city from info where area='010' limit 1
				db = AddressDao.getAddressDB("/sdcard/address.db");
				if (db.isOpen()) {
					Cursor cursor = db.rawQuery(
							"select city from info where area=? limit 1",
							new String[] { number.substring(0, 4) });
					if (cursor.moveToNext()) {
						address = cursor.getString(0);
					}
					cursor.close();
					db.close();

				}
				
				break;
			}
		}
		return address;
	}
}




