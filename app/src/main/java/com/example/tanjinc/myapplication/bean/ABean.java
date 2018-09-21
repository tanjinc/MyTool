package com.example.tanjinc.myapplication.bean;

import com.example.tanjinc.myapplication.BaseConstant;
import com.google.gson.annotations.SerializedName;

public class ABean extends BaseBean {
    String name;
    /**
     * code : 1
     * info : success
     * results : {"id":"1","name":"hehe"}
     */

    private int code;
    private String info;
    private ResultsBean results;


    public ABean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getType() {
        return BaseConstant.TYPE_A;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * id : 1
         * name : hehe
         */

        private String id;
        @SerializedName("name")
        private String nameX;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNameX() {
            return nameX;
        }

        public void setNameX(String nameX) {
            this.nameX = nameX;
        }
    }
}
