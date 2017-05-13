package com.ohgj.engine.Net;

public interface HTTPListener {

    void onFinish(String data);
    void onProgress(float percentage);
    void onFail(String error);

}
