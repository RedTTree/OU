package com.example.z_shenou;

import com.example.z_shenou.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment extends Fragment {
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//  ÿ��Fragment�ﶼֻ��һ���򵥵�view������ʾ����
       
        return inflater.inflate(R.layout.p0, container, false);
    }
}
