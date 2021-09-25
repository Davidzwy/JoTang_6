package com.example.jotang;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class File {
    private static SharedPreferences sp;

    public static void putUser(Context ctx, List<User> users) {
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(users);
        editor.putString("user",json);
        editor.commit();
    }

    public static List<User> getUser(Context ctx) {
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", MODE_PRIVATE);
        }
        Gson gson = new Gson();
        String json = sp.getString("user", null);
        Type type = new TypeToken<List<User>>() {        }.getType();
        List<User> arrayList = gson.fromJson(json, type);
        return arrayList;
    }
}
