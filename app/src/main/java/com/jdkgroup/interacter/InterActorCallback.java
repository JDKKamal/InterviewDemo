package com.jdkgroup.interacter;

public interface InterActorCallback<T> {

  void onStart();

  void onResponse(T response);

  void onFinish();

  void onError(String message);
}
