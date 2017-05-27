package com.android.qiu.qiu;



        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;

public class UserinfoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private Button button;
    private TextView textView;

    private String userId="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        initView();
        initData();

    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.image_avatar);
        button = (Button) findViewById(R.id.button_chat);
        textView = (TextView) findViewById(R.id.text_name);

        button.setOnClickListener(this);
    }

    private void initData() {
        // 得到数据 并初始化
        //userId=?

    }

    @Override
    public void onClick(View v) {


      //  Intent intent = new Intent(this, SettingActivity.class);
        // 传入对方的 Id 即可
        //  intent.putExtra(LCIMConstants.PEER_ID, userId);
       // startActivity(intent);
    }
}
