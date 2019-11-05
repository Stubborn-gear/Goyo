package com.htdata.goyo.login.activity;

import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.htdata.goyo.R;
import com.htdata.goyo.base.BaseActivity;
import com.htdata.goyo.main.ActMain;
import com.htdata.goyo.util.ToastUtils;
import com.htdata.goyo.util.UserUtil;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @作者：cb
 * @日期：2019-10-17 16:15
 * @描述：登录
 */
public class ActLogin extends BaseActivity {


    @BindView(R.id.login_type)
    TextView lType;
    @BindView(R.id.login_account)
    EditText loginAccount;
    @BindView(R.id.login_code)
    TextView loginCode;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_btn)
    TextView loginBtn;
    @BindView(R.id.login_register)
    TextView loginRegister;
    @BindView(R.id.login_forget_pwd)
    TextView loginForgetPwd;

    private boolean loginType = false ;// false 企业登录，true 个人用户登录


    @Override
    protected int getContentViewId() {
        return R.layout.act_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        UserUtil.setUserType(2);
    }




    @OnClick({R.id.login_type, R.id.login_code, R.id.login_btn, R.id.login_register, R.id.login_forget_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_type:
                if (!loginType){
                    loginType = true ;
                    lType.setText(setString(R.string.personal_login));
                    loginAccount.setHint(setString(R.string.account));
                    loginPassword.setHint(setString(R.string.password));
                    loginPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    loginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    setHidden(loginCode,true);
                }else {
                    loginType = false ;
                    lType.setText(setString(R.string.qy_login));
                    loginAccount.setHint(setString(R.string.input_phone));
                    loginPassword.setHint(setString(R.string.input_code));
                    loginPassword.setInputType(InputType.TYPE_CLASS_NUMBER);
                    loginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    setHidden(loginCode,false);
                }
                loginPassword.setText("");
                loginPassword.setSelection(loginPassword.getText().length());
                break;
            case R.id.login_code:
                ToastUtils.showToastShort(this,"获取验证码");
                break;
            case R.id.login_btn:
                toActivity(ActMain.class,true);
                break;
            case R.id.login_register:
                toActivity(ActRegister.class);
                break;
            case R.id.login_forget_pwd:
                ToastUtils.showToastShort(this,"忘记密码");
                break;
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
