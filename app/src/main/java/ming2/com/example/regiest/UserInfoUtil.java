package ming2.com.example.regiest;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 云 on 2017/4/12.
 */

public class UserInfoUtil {

    //保存数据
    public static boolean saveUserInfo(Context context, String name, String password) {
        //String path = "/mnt/sdcard";
        String path= Environment.getExternalStorageDirectory().getPath();
        File file = new File(path, "userInfo.txt");
        try {
            String info = name + "##" + password;  //封装数据
            FileOutputStream fileOutputStream = new FileOutputStream(file); //创建写入流
            fileOutputStream.write(info.getBytes());  //写入
            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    //获得数据
    public static Map<String, String> userGetInfo(Context context) {
        //String path = "/mnt/sdcard";
        //不把数据写死，灵活应用
        String path=Environment.getExternalStorageDirectory().getPath();
        //Log.d(path,"this is sdDirctory");
        File file = new File(path, "userInfo.txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String data[] = bufferedReader.readLine().split("##");
            Map<String, String> map = new HashMap();
            map.put("username", data[0]);
            map.put("password", data[1]);
            fileInputStream.close();
            bufferedReader.close();
            return map;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static boolean saveUserInfo_forAndroid(Context context, String name, String password) {
        //String path = "/mnt/sdcard";
//        String path= Environment.getExternalStorageDirectory().getPath();
//        File file = new File(path, "userInfo.txt");
        try {
            String info = name + "##" + password;  //封装数据
            //FileOutputStream fileOutputStream = new FileOutputStream(file); //创建写入流
            FileOutputStream fileOutputStream=context.openFileOutput("userInfo.txt",context.MODE_PRIVATE);
            fileOutputStream.write(info.getBytes());  //写入
            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static Map<String, String> userGetInfo_forAndroid(Context context) {
        //String path = "/mnt/sdcard";
        //不把数据写死，灵活应用
        //String path=Environment.getExternalStorageDirectory().getPath();
        //Log.d(path,"this is sdDirctory");
       // File file = new File(path, "userInfo.txt");
        try {
           // FileInputStream fileInputStream = new FileInputStream(file);
            FileInputStream fileInputStream=context.openFileInput("userInfo_txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String data[] = bufferedReader.readLine().split("##");
            Map<String, String> map = new HashMap();
            map.put("username", data[0]);
            map.put("password", data[1]);
            fileInputStream.close();
            bufferedReader.close();
            return map;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}