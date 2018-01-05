package com.jdkgroup.interviewdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.jdkgroup.baseclass.BaseActivity;
import com.jdkgroup.customviews.contact.Contact;
import com.jdkgroup.customviews.contact.RxContacts;
import com.jdkgroup.interviewdemo.R;
import com.jdkgroup.interviewdemo.adapter.ContactAdapter;
import com.jdkgroup.utils.AppUtils;
import com.jdkgroup.utils.Validator;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SignUpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
    }

    private boolean Validation(String username, String email, String password, String confirmpassword, String mobile) {
        if (Validator.isEmpty(username)) {
            AppUtils.showToast(getActivity(), getString(R.string.msg_empty_username));
            return false;
        } else if (Validator.isRegexValidator(username, Validator.patternName) == false) {
            AppUtils.showToast(getActivity(), getString(R.string.msg_valid_username));
            return false;
        } else if (Validator.isEmpty(email)) {
            AppUtils.showToast(getActivity(), getString(R.string.msg_empty_email));
            return false;
        } else if (Validator.isRegexValidator(email, Validator.patternEmail) == false) {
            AppUtils.showToast(getActivity(), getString(R.string.msg_valid_email));
            return false;
        } else if (Validator.isEmpty(password)) {
            AppUtils.showToast(getActivity(), getString(R.string.msg_empty_password));
            return false;
        } else if (Validator.isRegexValidator(password, Validator.patternPassword) == false) {
            AppUtils.showToast(getActivity(), getString(R.string.msg_valid_password));
            return false;
        } else if (Validator.isEmpty(confirmpassword)) {
            AppUtils.showToast(getActivity(), getString(R.string.msg_empty_confirm_password));
            return false;
        } else if (Validator.isRegexValidator(confirmpassword, Validator.patternPassword) == false) {
            AppUtils.showToast(getActivity(), getString(R.string.msg_empty_valid_confirm_password));
            return false;
        } else if (Validator.isEqual(password, confirmpassword) == false) {
            AppUtils.showToast(getActivity(), getString(R.string.msg_match_password));
            return false;
        } else if (Validator.isEmpty(mobile)) {
            AppUtils.showToast(getActivity(), getString(R.string.msg_empty_mobile));
            return false;
        } else if (Validator.isRegexValidator(mobile, Validator.patternMobile) == false) {
            AppUtils.showToast(getActivity(), getString(R.string.msg_valid_mobile));
            return false;
        }
        return true;
    }

}
