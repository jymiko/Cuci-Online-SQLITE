package com.project.cucionline;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManagement {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE=0;
    private static final String PREF_NAME="LoginUser";
    private static final String IS_LOGIN="IsLoggedIn";
    public static final String KEY_EMAIL="email";
    public static final String KEY_NAME="name";


    public SessionManagement(Context context){
        this.context=context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor=sharedPreferences.edit();
    }

    public void createLoginSession(String email,String pass){
        editor.putBoolean(IS_LOGIN,true);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_NAME,pass);
        editor.commit();
    }

    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent intent = new Intent(context,HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public HashMap<String ,String> getUserDetails(){
        HashMap<String,String>user = new HashMap<String ,String>();
        user.put(KEY_EMAIL,sharedPreferences.getString(KEY_EMAIL,null));
        user.put(KEY_NAME,sharedPreferences.getString(KEY_NAME,null));
        return user;
    }

    public void logoutUSer(){
        editor.clear();
        editor.commit();

        Intent intent = new Intent(context,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGIN,true);
    }
}
