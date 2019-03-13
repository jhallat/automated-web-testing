package com.jhallat.automated.testing.listener;

import com.jhallat.automated.testing.domain.TestProject;

public interface NewTestProjectListener {

    default void onCancel() {
        //Do nothing. Not required
    };
    void onCreate(TestProject testProject);

}
