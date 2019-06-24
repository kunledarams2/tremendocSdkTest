package com.e.tremendocsdk.View.UI.Fragment;

import android.support.v4.app.Fragment;

public abstract class TitledFragment extends Fragment {

   private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
