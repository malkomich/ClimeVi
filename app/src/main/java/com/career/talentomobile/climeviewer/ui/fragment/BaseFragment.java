package com.career.talentomobile.climeviewer.ui.fragment;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.career.talentomobile.climeviewer.ui.view.BaseView;

/**
 * Fragment implementation of a basic view which print messages.
 */
public class BaseFragment extends Fragment implements BaseView {

    @Override
    public void makeToast(int messageResource) {
        Toast.makeText(getContext(), messageResource, Toast.LENGTH_SHORT).show();
    }
}
