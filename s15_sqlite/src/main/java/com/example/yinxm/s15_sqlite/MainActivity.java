package com.example.yinxm.s15_sqlite;

        import android.content.ContentValues;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;

        import com.example.yinxm.s15_sqlite.db.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    private Button createButton;
    private Button insertButton;
    private Button updateButton;
    private Button updateRecordButton;
    private Button queryButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createButton = (Button)findViewById(R.id.createDatabase);
        updateButton = (Button)findViewById(R.id.updateDatabase);
        insertButton = (Button)findViewById(R.id.insert);
        updateRecordButton = (Button)findViewById(R.id.update);
        queryButton = (Button)findViewById(R.id.query);
        createButton.setOnClickListener(new CreateListener());
        updateButton.setOnClickListener(new UpdateListener());
        insertButton.setOnClickListener(new InsertListener());
        updateRecordButton.setOnClickListener(new UpdateRecordListener());
        queryButton.setOnClickListener(new QueryListener());
    }
    class CreateListener implements OnClickListener{
        @Override
        public void onClick(View v) {
            System.out.println("Create databsse");
            //创建一个DatabaseHelper对象
//            DatabaseHelper dbHelper = null;
            DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this,"test_mars_db");//数据库名字
            //只有调用了DatabaseHelper对象的getReadableDatabase()方法，或者是getWritableDatabase()方法之后，才会创建，或打开一个数据库
            SQLiteDatabase db = dbHelper.getReadableDatabase();
        }
    }
    class UpdateListener implements OnClickListener{

        @Override
        public void onClick(View v) {
            DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this,"test_mars_db",2);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
        }

    }
    class InsertListener implements OnClickListener{

        @Override
        public void onClick(View v) {
            //生成ContentValues对象
            ContentValues values = new ContentValues();
            //想该对象当中插入键值对，其中键是列名，值是希望插入到这一列的值，值必须和数据库当中的数据类型一致
            values.put("id", 1);
            values.put("name","zhangsan");
            DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this,"test_mars_db",2);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //调用insert方法，就可以将数据插入到数据库当中
            db.insert("user", null, values);
        }
    }
    //更新操作就相当于执行SQL语句当中的update语句
    //UPDATE table_name SET XXCOL=XXX WHERE XXCOL=XX...
    class UpdateRecordListener implements OnClickListener{

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            //得到一个可写的SQLiteDatabase对象
            DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this,"test_mars_db",2);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", "zhangsanfeng");
            //第一个参数是要更新的表名
            //第二个参数是一个ContentValeus对象
            //第三个参数是where子句
            db.update("user", values, "id=?", new String[]{"1"});
        }
    }
    class QueryListener implements OnClickListener{
        @Override
        public void onClick(View v) {
            System.out.println("aaa------------------");
            Log.d("my", "myFirstDebugMsg");

            DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this,"test_mars_db",2);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Log.d("my", "path = " + db.getPath());

            Cursor cursor = db.rawQuery("select name from sqlite_master where type='table' order by name", null);
            while(cursor.moveToNext()){
                //遍历出表名
                String name = cursor.getString(0);
                Log.i("my", name);
            }


            cursor = db.query("user", new String[]{"id","name"}, "id=?", new String[]{"1"}, null, null, null);
            while(cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex("name"));
                System.out.println("query--->" + name);
            }
            Log.d("TemppjActivity", "cont.getDatabasePath('test_mars_db') = " + getApplicationContext().getDatabasePath("test_mars_db"));
        }
    }

}
