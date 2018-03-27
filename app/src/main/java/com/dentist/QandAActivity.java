package com.dentist;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.entity.NetEnv;
import com.interfaces.Iview;
import com.interfaces.back_info;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by 佘松 on 2018/3/26.
 */

public class QandAActivity extends Activity implements View.OnClickListener,back_info {

        private TextView tvQuestion;
        private TextView tvAnswer;

        private static int index=0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
//            setContentView(R.layout.fragment_qanda);
            init();
        }

    private void init() {
        tvQuestion=(TextView)findViewById(R.id.question);
        tvAnswer=(TextView)findViewById(R.id.answer);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next:
                String url= NetEnv.QandA_SERVER+"?num="+index;
        }
    }

    @Override
    public void send_back(String s) {
        try {
            JSONObject jsonObject=new JSONObject(s);
            String question=jsonObject.getString("question");
            String answer=jsonObject.getString("answer");
            tvQuestion.setText(question);
            tvAnswer.setText(answer);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void error_back(String e) {

    }

    @Override
    public void send_back(Bitmap bitmap) {

    }
}
