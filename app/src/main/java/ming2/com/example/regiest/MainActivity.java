package ming2.com.example.regiest;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.Formatter;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private EditText password;
    private CheckBox checkBox;
    private Button login;
    private Context mContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到相应的控件
        editText= (EditText) findViewById(R.id.et_text);
        password= (EditText) findViewById(R.id.et_password);
        checkBox= (CheckBox) findViewById(R.id.check);
        login= (Button) findViewById(R.id.login);
        mContent=this;
        //设置点击事件
        login.setOnClickListener(this);
        //进入自动补全信息
        Map<String,String> map=UserInfoUtil.userGetInfo_forAndroid(mContent);
        if(map!=null) {
            editText.setText(map.get("username"));
            password.setText(map.get("password"));
            checkBox.setChecked(true);
        }

    }

    public void login(){
        //点击获取用户名和密码
        String username=editText.getText().toString().trim();
        String userpassword=password.getText().toString().trim();
        boolean isRember=checkBox.isChecked();
        //判断密码是否为空，不为空请求服务器
        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(userpassword)){
            Toast.makeText(mContent,"用户名和密码不可为空",Toast.LENGTH_SHORT).show();
            return;
        }
        //判断是否记住密码，若是，保存到本地
        if(isRember){
            if(!Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)){
                Toast.makeText(mContent,"sd卡已被移除",Toast.LENGTH_SHORT).show();
                return;
            }
            File file=Environment.getExternalStorageDirectory();
            long userableSpace=file.getUsableSpace();
            //String userableSpace_str= Formatter.formaterFileSize();
            //判断是否内存不够
            if(userableSpace<1024*1024*200){
                Toast.makeText(mContent,"内存不够",Toast.LENGTH_SHORT).show();
            }

            //判断是否保存
            boolean result=UserInfoUtil.saveUserInfo_forAndroid(mContent,username,userpassword);
            if(result){
                Toast.makeText(mContent,"保存成功",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(mContent,"保存失败",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(mContent,"无需再保存",Toast.LENGTH_SHORT).show();
        }
        //回显密码，用户名

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                login();
                break;
            default:
                break;
        }
    }
}