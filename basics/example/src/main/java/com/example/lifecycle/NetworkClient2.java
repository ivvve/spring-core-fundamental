package com.example.lifecycle;

public class NetworkClient2 {
    private String url;

    public NetworkClient2() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    public void disConnect() {
        System.out.println("close + " + url);
    }

    // 초기화 메서드
    public void init() {
        connect();
        call("초기화 연결 메시지");
    }

    // 소멸 메서드
    public void close() {
        disConnect();
    }
}
