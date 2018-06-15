package com.xy.doll.address;

/**
 * Created by jxy on 2018/6/13.
 */

public class AddressTool {

  /*  private static AddressTool instance;

    public static AddressTool getInstance() {
        if (instance == null) {
            instance = new AddressTool();
        }
        return instance;
    }

    public void initJsonData() {
        ArrayList<JsonAreaBean> options1Items = new ArrayList<>();
        ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

        String json = getJson();
        ArrayList<JsonAreaBean> data = getData(json);
        options1Items.addAll(data); //所有省份数据的集合

        for (int i = 0; i < data.size(); i++) {
            ArrayList<String> cityList = new ArrayList<>();
            ArrayList<ArrayList<String>> areaList = new ArrayList<>();
            for (int j = 0; j < data.get(i).getCityList().size(); j++) {
                cityList.add(data.get(i).getCityList().get(j).getName());
                ArrayList<String> areaNameList = new ArrayList<>();
                for (int k = 0; k < data.get(i).getCityList().get(j).getArea().size(); k++) {
                    String areaName = data.get(i).getCityList().get(j).getArea().get(k);
                    areaNameList.add(areaName);
                }
                areaList.add(areaNameList);
            }
            options2Items.add(cityList);
            options3Items.add(areaList);
        }
        LogUtil.e("options1Items = " + Arrays.toString(options1Items.toArray()));
        LogUtil.e("options2Items = " + Arrays.toString(options2Items.toArray()));
        LogUtil.e("options3Items = " + Arrays.toString(options3Items.toArray()));
    }

    public ArrayList<JsonAreaBean> getData(String json) {
        ArrayList<JsonAreaBean> jsonBean = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(json);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonAreaBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonAreaBean.class);
                jsonBean.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonBean;
    }

    *//**
     * 从本地获取json
     *
     * @return
     *//*
    public static String getJson() {
        File file = new File(Contact.getVideoDir(), "address.txt");
        StringBuilder sb = new StringBuilder();
        if (file.exists()) {
            LogUtil.e("File已存在");
        } else {
            try {
                String fileName = Contact.getVideoDir() + "address.txt";
                LogUtil.e("fileName = " + fileName);
                FileInputStream fis = new FileInputStream(fileName);
                byte[] temp = new byte[1024];
                int lines = 0;
                while ((lines = fis.read(temp)) > 0) {
                    sb.append(new String(temp, 0, lines));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            LogUtil.e("File fileName" + sb.toString());
        }
        return sb.toString();
    }

    *//**
     * 向本地写入json
     *
     * @param json
     *//*
    private void write(String json) {
        String filePath = Contact.getVideoDir() + "address.txt/";
        File file = new File(filePath);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(json.getBytes());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
