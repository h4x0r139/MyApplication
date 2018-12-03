package cn.yinxm.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class WifiPlayerContentProvider extends ContentProvider {
    private WifiDbHelper dbHelper;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private Context context;



    public WifiPlayerContentProvider() {
    }

    static {
        uriMatcher.addURI(WifiDbConstant.WIFI_PLAYER_AUTHORITY,
                WifiDbConstant.PUB_CONFIG_ACTION,
                WifiDbConstant.PUB_CONFIG_CODE);
    }


    @Override
    public boolean onCreate() {
        context = getContext();
        dbHelper = new WifiDbHelper(context);

        return true;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private String getTableName(Uri uri) {
        String tableName = null;
        switch (uriMatcher.match(uri)) {
            case WifiDbConstant.PUB_CONFIG_CODE:
                tableName = WifiDbConstant.DEVICE_CONFIG_TABLE_NAME;
                break;
            default:
                break;
        }
        return tableName;
    }
}
