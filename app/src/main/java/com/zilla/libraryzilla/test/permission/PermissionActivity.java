package com.zilla.libraryzilla.test.permission;

import android.Manifest;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.snowdream.android.util.Log;
import com.zilla.libraryzilla.R;
import com.zilla.libraryzilla.ZillaApplication;
import com.zilla.libraryzilla.common.BaseFragment;

import zilla.libjerry.permission.MPermission;
import zilla.libjerry.permission.PermissionFail;
import zilla.libjerry.permission.PermissionOK;

public class PermissionActivity extends AppCompatActivity implements BaseFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        MPermission.with(this)
                .setPermission(Manifest.permission.READ_EXTERNAL_STORAGE
                ,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .requestPermission();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content,new TestFragmen())
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MPermission.onRequestPermissionsResult(this,requestCode,permissions,grantResults);
    }

    @PermissionOK
    private void valdateSuccess(){
        Log.i("权限通过啦");

    }

    @PermissionFail
    public void validateFail(){
        Log.i("没有获取权限");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZillaApplication.getRefWatcher(this).watch(this);
    }
}
