package com.xy.doll.address;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/3.
 */

public class DataHelper {
    private static List<AddressBean> addressList;

    public static List<AddressBean> getAddress(Context context) {
        if (addressList == null) {
            InputStreamReader inputStreamReader;
            try {
                inputStreamReader = new InputStreamReader(context.getAssets().open(
                        "address.json"), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(
                        inputStreamReader);
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStreamReader.close();
                bufferedReader.close();
                Gson gson = new Gson();
                Type type = new TypeToken<List<AddressBean>>() {}.getType();
                addressList = gson.fromJson(stringBuilder.toString(), type);
                return addressList;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        } else {
            return addressList;
        }
    }
}
