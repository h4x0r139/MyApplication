package com.example.yinxm.s06_radio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private RadioGroup sexRadioGroup;
    private RadioButton maleRadio;
    private RadioButton femaleRadio;

    private RadioGroup nameRadioGroup;
    private RadioButton name1Radio;
    private RadioButton name2Radio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.customRb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomRadioButtonActivity.class));
            }
        });

        nameRadioGroup = (RadioGroup) findViewById(R.id.radioGroupName);
        name1Radio = (RadioButton)findViewById(R.id.name1Id);
        name2Radio = (RadioButton)findViewById(R.id.name2Id);
        OnRadioCheckedChangeListener onNameRadioCheckedChangeListener = new OnRadioCheckedChangeListener();
        nameRadioGroup.setOnCheckedChangeListener(onNameRadioCheckedChangeListener);

        sexRadioGroup = (RadioGroup) findViewById(R.id.radioGroupSex);
        maleRadio = (RadioButton) findViewById(R.id.maleId);
        femaleRadio = (RadioButton) findViewById(R.id.femaleId);

        OnRadioCheckedChangeListener radioCheckedChangeListener = new OnRadioCheckedChangeListener();
        sexRadioGroup.setOnCheckedChangeListener(radioCheckedChangeListener);
    }

    class OnRadioCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == maleRadio.getId()) {
                System.out.println("maile is checked");
            }
            System.out.println(group.getId() + " " + group.getCheckedRadioButtonId() + ", checkedId=" + checkedId);
            RadioButton radioButton = (RadioButton)findViewById(group.getCheckedRadioButtonId());
            System.out.println(""+radioButton.getText());

            if (group.getId() == nameRadioGroup.getId()) {
                if (checkedId == name1Radio.getId()) {
                    femaleRadio.setChecked(true);
                }
                if (checkedId == name2Radio.getId()) {
                    maleRadio.setChecked(true);
                }
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
