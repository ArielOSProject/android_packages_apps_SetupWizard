/*
 * Copyright (C) 2016 The CyanogenMod Project
 * Copyright (C) 2017 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lineageos.setupwizard;

import android.os.Bundle;
import android.widget.TextView;

public class ArielOSWelcomeActivity extends BaseSetupWizardActivity {

    public static final String TAG =
            ArielOSWelcomeActivity.class.getSimpleName().substring(0, 22);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setNextText(R.string.next);

        TextView summaryView = (TextView) findViewById(android.R.id.summary);
        summaryView.setText(R.string.arielos_summary_text);
    }

    @Override
    protected int getTransition() {
        return TRANSITION_ID_SLIDE;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.arielos_welcome;
    }

    @Override
    protected int getTitleResId() {
        return R.string.arielos_welcome;
    }

    @Override
    protected int getIconResId() {
        return R.drawable.ariel_logo;
    }

}
