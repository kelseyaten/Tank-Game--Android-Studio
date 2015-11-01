package com.example.localadmin.g11_ajh265_kex496.util;

import java.io.Serializable;

public class ResultWrapper<T> implements Serializable {
    private T result;

    public ResultWrapper() {
    }

    public ResultWrapper(T result) {
        this.result = result;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
