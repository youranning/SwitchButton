package bwie.com.switchbutton;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.kyleduo.switchbutton.SwitchButton;


import org.greenrobot.eventbus.EventBus;

import bwie.com.switchbutton.constants.Constants;
import bwie.com.switchbutton.events.MainActivityEvent;

public class MainActivity extends Activity {

    private SwitchButton switchButton;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        view = getWindow().getDecorView();

        switchButton = (SwitchButton) view.findViewById(R.id.switch_btn);

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // isChecked true false
                System.out.println("isChecked = " + isChecked);
//
                boolean mode = PreferencesUtils.getValueByKey(getApplicationContext(), Constants.isNightMode, isChecked);

                setMode(isChecked);
                EventBus.getDefault().post(new MainActivityEvent(isChecked));

                setBackground(isChecked);


            }
        });

    }

    private void setBackground(boolean white) {

        if (white) {

            view.setBackgroundColor(Color.WHITE);
        } else {
            //夜间
            view.setBackgroundColor(getResources().getColor(R.color.color_window));
        }
    }

    private void setMode(boolean mode) {
        PreferencesUtils.addConfigInfo(this, Constants.isNightMode, mode);

    }
}
