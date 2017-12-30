package com.jdkgroup.view;

import com.jdkgroup.baseclass.BaseView;
import com.jdkgroup.model.callapi.ModelCallApi;

public interface CallAPIView extends BaseView {
    void callCategory(ModelCallApi response);
}

