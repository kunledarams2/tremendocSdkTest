package com.e.tremendocSDK.View.UI.Fragment;

import androidx.fragment.app.Fragment;

public abstract class FragmentTitled extends Fragment {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
