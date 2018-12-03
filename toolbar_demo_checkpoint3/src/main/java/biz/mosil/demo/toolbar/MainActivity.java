package biz.mosil.demo.toolbar;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
  private RelativeLayout mainlayoutContent;
  private Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

//    toolbar = (Toolbar) findViewById(R.id.toolbar);
    //纯Java实现ToolBar
    mainlayoutContent = (RelativeLayout) findViewById(R.id.mainlayoutContent);
    toolbar = new Toolbar(this);
    toolbar.setBackgroundColor(getResources().getColor(R.color.accent_material_dark));//获取系统定义的颜色
//    addContentView(toolbar, new Toolbar.LayoutParams(android.widget.Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT));
    mainlayoutContent.addView(toolbar,new Toolbar.LayoutParams(android.widget.Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT));


    // App Logo
//    toolbar.setLogo(R.drawable.ic_launcher);
    toolbar.setLogo(R.drawable.ic_drawer);
    // Title
    toolbar.setTitle("My Title");
    // Sub Title
    toolbar.setSubtitle("Sub title");

    setSupportActionBar(toolbar);

    // Navigation Icon 要設定在 setSupoortActionBar 才有作用
    // 否則會出現 back bottom
    toolbar.setNavigationIcon(R.drawable.ab_android);
    // Menu item click 的監聽事件一樣要設定在 setSupportActionBar 才有作用
    toolbar.setOnMenuItemClickListener(onMenuItemClick);
  }

  private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
      String msg = "";
      switch (menuItem.getItemId()) {
        case R.id.action_edit:
          msg += "Click edit";
          toolbar.setLogo(R.drawable.ic_launcher);
          break;
        case R.id.action_share:
          msg += "Click share";
          toolbar.setNavigationIcon(R.drawable.ic_drawer);
          break;
        case R.id.action_settings:
          msg += "Click setting";
          break;
      }

      if(!msg.equals("")) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
      }
      return true;
    }
  };


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // 為了讓 Toolbar 的 Menu 有作用，這邊的程式不可以拿掉
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }
}
