package cn.itcast.mobilesafe.engine;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.itcast.mobilesafe.db.dao.AddressDao;

public class NumberAddressService {

	/**
	 * 
	 * @param number
	 *            Ҫ��ѯ�ĵ绰����
	 * @return �绰����Ĺ�����
	 */
	public static String getAddress(String number) {
		String pattern = "^1[3458]\\d{5}$";
		String address = number;
		if (number.matches(pattern)) { // �ֻ�����

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

		} else { // �̶��绰
			int len = number.length();
			
			switch (len) {
			
			case 4: //3λ���� + 8λ���� 4λ����+7λ����
				//select city from info where area='010' limit 1
				SQLiteDatabase db2 = AddressDao.getAddressDB("/sdcard/address.db");
				if (db2.isOpen()) {
					
					Cursor cursor2 = db2.rawQuery(
							"select city from info where area=? limit 1",
							new String[] { number.substring(0, 4) });
					if (cursor2.moveToNext()) {
						address = cursor2.getString(0);
					}
					
					cursor2.close();
					
					db2.close();

				}
				
				break;
			case 3: //4λ���� +8λ����
				//select city from info where area='010' limit 1
				SQLiteDatabase db = AddressDao.getAddressDB("/sdcard/address.db");
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
			
				
				
			}
			
		}
		return address;
	}
}















