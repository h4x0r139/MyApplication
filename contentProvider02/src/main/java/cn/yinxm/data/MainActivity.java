package cn.yinxm.data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    private static final String TAG = "yinxm";

    private TextView mTvShowBooks; // 显示书籍
    private TextView mTvShowUsers; // 显示用户

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvShowBooks = (TextView) findViewById(R.id.main_tv_show_books);
        mTvShowUsers = (TextView) findViewById(R.id.main_tv_show_users);
    }

    /**
     * 添加书籍的事件监听
     *
     * @param view 视图
     */
    public void addBooks(View view) {

        Uri bookUri = BookProvider.BOOK_CONTENT_URI;
        Log.d("yinxm", "uri="+bookUri+", path="+bookUri.getPath()+", getAuthority="+bookUri.getAuthority()+", getScheme="+bookUri.getScheme());
        ContentValues values = new ContentValues();
        values.put("_id", 3);
        values.put("name", "信仰上帝");
        getContentResolver().insert(bookUri, values);
    }

    /**
     * 显示书籍
     *
     * @param view 视图
     */
    public void showBooks(View view) {
        String content = "";
        Uri bookUri = BookProvider.BOOK_CONTENT_URI;
        Cursor bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "name"}, null, null, null);
        if (bookCursor != null) {
            while (bookCursor.moveToNext()) {
                Book book = new Book();
                book.bookId = bookCursor.getInt(0);
                book.bookName = bookCursor.getString(1);
                content += book.toString() + "\n";
                Log.e(TAG, "query book: " + book.toString());
                mTvShowBooks.setText(content);
            }
            bookCursor.close();
        }
    }

    /**
     * 显示用户
     *
     * @param view 视图
     */
    public void showUsers(View view) {
        String content = "";
        Uri userUri = BookProvider.USER_CONTENT_URI;
        Cursor userCursor = getContentResolver().query(userUri, new String[]{"_id", "name", "sex"}, null, null, null);
        if (userCursor != null) {
            while (userCursor.moveToNext()) {
                User user = new User();
                user.userId = userCursor.getInt(0);
                user.userName = userCursor.getString(1);
                user.isMale = userCursor.getInt(2) == 1;
                content += user.toString() + "\n";
                Log.e(TAG, "query user:" + user.toString());
                mTvShowUsers.setText(content);
            }
            userCursor.close();
        }
    }

    /**
     * 显示本地通讯录联系人
     *
     * @param view 视图
     */
    public void showContacts(View view) {
        String content = "";
        //得到ContentResolver对象
        ContentResolver contentResolver = getContentResolver();
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor != null) {
            Log.d(TAG, "count="+cursor.getCount());
            StringBuilder stringBuilder = new StringBuilder();
            while (cursor.moveToNext()) {

                //取得联系人名字
                int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
                String contact = cursor.getString(nameFieldColumnIndex);
                stringBuilder.append(contact).append(":");
                //取得电话号码
                String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                Cursor phone = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);
                while(phone != null && phone.moveToNext())
                {
                    String phoneNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    if (phoneNumber != null && !phoneNumber.trim().equals(""))
                    //格式化手机号
                    phoneNumber = phoneNumber.replace("-","");
                    phoneNumber = phoneNumber.replace(" ","");
                    stringBuilder.append(phoneNumber).append(", ");
                }

            }
            cursor.close();
            Log.d(TAG, stringBuilder.toString());
            mTvShowUsers.setText(stringBuilder.toString());

        }
    }
}
