package com.xjp.androiddialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Android 对话框集合
 */
public class MainActivity extends ActionBarActivity {

    private ListView listView;

    private View parentView;

    private boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parentView = LayoutInflater.from(this).inflate(R.layout.activity_main, null);

        listView = (ListView) findViewById(R.id.list);
        String items[] = {"多按钮对话框", "单选对话框", "多选对话框", "列表对话框", "添加布局对话框",
                "简单自定义对话框1", "简单自定义对话框2", "高级自定义对话框", "PopupWindow高级对话框"};
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        dialog1();
                        break;
                    case 1:
                        dialog2();
                        break;
                    case 2:
                        dialog3();
                        break;
                    case 3:
                        dialog4();
                        break;
                    case 4:
                        dialog5();
                        break;
                    case 5:
                        dialog6();
                        break;
                    case 6:
                        dialog7();
                        break;
                    case 7:
                        dialog8();
                        break;
                    case 8:
                        dialog9();
                        break;
                }
            }
        });
    }


    /**
     * 多按钮对话框
     */
    private void dialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确定对话框");
        builder.setMessage("测试对话框");
        builder.setPositiveButton("好评", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("差评", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNeutralButton("点赞", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setIcon(R.drawable.ic_launcher);
        builder.show();
    }

    /**
     * 单选对话框
     */
    private void dialog2() {
        String items[] = {"item1", "item2", "item3", "item4", "item5"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        int position = 0;//默认单选的位置

        builder.setTitle("单选对话框").setSingleChoiceItems(items, position, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO 实现自己的需求
            }
        }).show();
    }

    /**
     * 多选对话框
     */
    private void dialog3() {
        //默认多选的状态
        boolean b[] = {false, false, true, false, false};
        String items[] = {"item1", "item2", "item3", "item4", "item5"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("多选对话框").setMultiChoiceItems(items, b, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                //TODO 实现自己的需求
            }
        }).show();
    }

    /**
     * 列表对话框
     */
    private void dialog4() {
        String items[] = {"item1", "item2", "item3", "item4", "item5", "item6", "item7", "item8"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("列表对话框").setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO 实现自己的需求
            }
        }).show();
    }

    /**
     * 添加自定义布局对话框
     */
    private void dialog5() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_launcher).setTitle("添加布局对话框").
                setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setMessage("这个内容根据自己需求，可要可不要");
        View view = LayoutInflater.from(this).inflate(R.layout.items, null);
        AlertDialog dialog = builder.create();
        dialog.setView(view);
        dialog.show();
    }

    /**
     * 简单自定义对话框1
     */
    private void dialog6() {
        Dialog dialog = new Dialog(this);
        dialog.setTitle("自定义对话框1");
        dialog.setContentView(R.layout.items);
        //设置点击对话框内容之外对话框消失
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }


    /**
     * 简单自定义对话框2
     */
    private void dialog7() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        dialog.getWindow().setContentView(R.layout.dialog);
        TextView title = (TextView) dialog.getWindow().findViewById(R.id.title);
        title.setText("简单自定义对话框2");
        dialog.getWindow().findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    /**
     * 完全自定义对话框，包括对话框的位置，大小，主题，动画等。
     */
    private void dialog8() {
        //实例化对话框并且设置对话框的主题
        final Dialog dialog = new Dialog(this, R.style.SelectDialog);
        //设置点击对话框以外部分对话框消失
        dialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = dialog.getWindow();
        //设置对话框的位置
        dialogWindow.setGravity(Gravity.TOP);
        //得到对话框属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //设置对话框起点X轴
        lp.x = 0;
        //设置对话框起点Y轴
        lp.y = getStatusBarHeight() + getActionBarHeight();
        //设置对话框大小
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);

        //设置自定义对话框的布局
        dialog.setContentView(R.layout.dialog);
        dialogWindow.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * 得到手机状态栏高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        Window dialogWindow = getWindow();
        Rect frame = new Rect();
        dialogWindow.getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }

    /**
     * 得到actionbar的高度
     *
     * @return
     */
    private int getActionBarHeight() {
        return getSupportActionBar().getHeight();
    }


    private void dialog9() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog, null);
        //实例化pw对话框并且设置布局，大小，是否能获得焦点，第三个参数为true可以获得焦点，一般设置为true即可
        final PopupWindow pw = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        //对话框是否获得焦点，这是为true时，点击pw以外的地方不响应，否则点击响应。
//        pw.setFocusable(false);
        //原本是点击pw之外窗口消失，但是实验发现无效，只有设置了setBackgroundDrawable时才有效。
//        pw.setOutsideTouchable(false);
        //设置pw对话框的动画效果
        pw.setAnimationStyle(R.style.Animation1);
        //这个很重要，设置pw对话框背景为全透明，只有设置这个，点击pw对话框以外内容时，对话框消失，并且对话框能响应back还回键。
        pw.setBackgroundDrawable(new ColorDrawable(0x00000000));
        //设置对话框的位置偏移量
        int x = 0;
        int y = getStatusBarHeight() + getActionBarHeight();
        //相对于父控件显示对话框
        pw.showAtLocation(parentView, Gravity.TOP, x, y);

//        showAsDropDown(View anchor)：相对某个控件的位置（正左下方），无偏移

//        showAsDropDown(View anchor, int xoff, int yoff)：相对某个控件的位置，有偏移

//        showAtLocation(View parent, int gravity, int x, int y)：相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移

        //pw对话框设置半透明背景。原理：pw显示时，改变整个窗口的透明度为0.7，当pw消失时，透明度为1
        final WindowManager.LayoutParams params = MainActivity.this.getWindow().getAttributes();
        params.alpha = 0.7f;
        MainActivity.this.getWindow().setAttributes(params);

        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isExit = true;
                pw.dismiss();
                params.alpha = 1f;
                MainActivity.this.getWindow().setAttributes(params);
            }
        });

        //pw对话框消失监听事件
        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1f;
                MainActivity.this.getWindow().setAttributes(params);
            }
        });

    }

}
