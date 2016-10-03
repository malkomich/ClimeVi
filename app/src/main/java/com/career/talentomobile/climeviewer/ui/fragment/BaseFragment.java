package com.career.talentomobile.climeviewer.ui.fragment;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.career.talentomobile.climeviewer.ui.view.BaseView;

/**
 * Created by malkomich on 03/10/2016.
 */

public class BaseFragment extends Fragment implements BaseView {

    @Override
    public void makeToast(String msg) {
        Toast.makeText(getActivity(), msg ,Toast.LENGTH_SHORT).show();
    }
}
