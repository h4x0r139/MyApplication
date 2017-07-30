package com.yinxm.bt;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import cn.yinxm.lib.utils.log.LogUtil;


/**
 * 读取通讯录
 */
public class ReadPhoneBookActivity extends Activity {
    RecyclerView rv_bindList;
    BindDeviceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_phone_book);

        rv_bindList = (RecyclerView) findViewById(R.id.rv_bindList);
        initData();
    }

    private void initData() {
        LogUtil.d("initData");
        rv_bindList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BindDeviceAdapter(this);
        rv_bindList.setAdapter(adapter);
        rv_bindList.setItemAnimator(new DefaultItemAnimator());
        //初始化自定义item点击事件
        adapter.setItemClickListener(new BindDeviceAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                BluetoothDevice device = adapter.getItem(position);
                LogUtil.d("click item="+position+", device="+device);
            }
        });
        readPhoneBook();
    }

    public void readPhoneBook() {
        LogUtil.d("readPhoneBook");
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> deviceSet = bluetoothAdapter.getBondedDevices();
        LogUtil.d("deviceSet="+ deviceSet);
        if (deviceSet != null) {
            LogUtil.d("devices="+ Arrays.toString(deviceSet.toArray()));
            adapter.setDevices(new ArrayList<BluetoothDevice>(deviceSet));
            adapter.notifyDataSetChanged();
        }
    }

    private static class BindDeviceAdapter extends RecyclerView.Adapter<BindDeviceAdapter.MyViewHolder> {
        Context mContext;
        List<BluetoothDevice> mDevices = new ArrayList();
        OnRecyclerItemClickListener itemClickListener;
        public BindDeviceAdapter(Context context) {
            this.mContext = context;
        }

        public void setDevices(List<BluetoothDevice> devices) {
            if (devices != null && devices.size() > 0) {
                this.mDevices.clear();
                this.mDevices.addAll(devices);
            }
        }

        public void setItemClickListener(OnRecyclerItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public BluetoothDevice getItem(int position) {
            if (mDevices != null && position < mDevices.size()
                    && mDevices.get(position) != null) {
                return mDevices.get(position);
            }else  {
                return null;
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LogUtil.d("onCreateViewHolder");
//            View layoutView = View.inflate(mContext,R.layout.item_bind_devices, null);//LinearLayout时有问题，会出现item的宽度填充不满
//            View layoutView = LayoutInflater.from(mContext).inflate(R.layout.item_bind_devices, null);//LinearLayout时有问题，会出现item的宽度填充不满
            View layoutView = LayoutInflater.from(mContext).inflate(R.layout.item_bind_devices, parent , false);//ok
            MyViewHolder myViewHolder = new MyViewHolder(layoutView);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            LogUtil.d("onBindViewHolder position="+position+", holder="+holder);
            if (itemClickListener != null && holder.rv_item_layout != null) {
                holder.rv_item_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClickListener.onItemClick(holder.rv_item_layout, position);
                    }
                });
            }
            if (mDevices != null && position < mDevices.size()
                    && mDevices.get(position) != null
                    && holder != null) {
                String str = mDevices.get(position).getAddress()+"     " + mDevices.get(position).getName();
                holder.tv.setText(str);
            }
        }

        @Override
        public int getItemCount() {
            return mDevices.size();
        }

        protected static class MyViewHolder extends RecyclerView.ViewHolder{
            private View rv_item_layout;
            private TextView tv;
            public MyViewHolder(View itemView) {
                super(itemView);
                rv_item_layout = itemView.findViewById(R.id.rv_item_layout);
                tv = (TextView) itemView.findViewById(R.id.tv_item_device);
            }
        }

        /**
         * 点击回调
         */
        public interface OnRecyclerItemClickListener {
            /**
             * item view 回调方法
             *
             * @param view     被点击的view
             * @param position 点击索引
             */
            void onItemClick(View view, int position);
        }
    }
}
