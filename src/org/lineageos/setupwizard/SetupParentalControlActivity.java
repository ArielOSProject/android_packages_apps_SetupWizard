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

import android.database.ContentObserver;
import android.os.Bundle;
import lineageos.providers.LineageSettings;

import android.os.Handler;
import android.widget.TextView;

import java.util.logging.Logger;

public class SetupParentalControlActivity extends BaseSetupWizardActivity {

    public static final String TAG =
            SetupParentalControlActivity.class.getSimpleName().substring(0, 22);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setNextAllowed(false);
        setBackAllowed(true);
        hideNextButton();

        TextView initSms = findViewById(R.id.init_sms);
        initSms.setText(getString(R.string.parental_control_sms_format,
                LineageSettings.Secure.getInt(getContentResolver(),
                        LineageSettings.Secure.ARIEL_MASTER_CODE, 0)));

        // register content observer to move on once parental control is done

    }

    private final ContentObserver mArielSettingObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean selfChange, android.net.Uri uri, int userId) {
            int arielSystemStatus = LineageSettings.Secure.getInt(getContentResolver(),
                    LineageSettings.Secure.ARIEL_SETUP_COMPLETE, 0);
            Logger.t(TAG).d("Ariel system status changed: " + arielSystemStatus);
//            // update current device configuration
//            DeviceConfiguration configuration = mArielDatabase.getDeviceConfiguration(ArielUtilities.getUniquePseudoID());
//            configuration.setArielSystemStatus(arielSystemStatus);
//            mArielDatabase.createConfiguration(configuration);
//            /**
//             * Make sure we update master device about our configuration
//             */
//            long messageId = mArielMessaging.prepareConfigurationMessage(ArielUtilities.getUniquePseudoID(),
//                    ArielConstants.ACTION.DEVICE_CONFIG_UPDATE, false);
//            Bundle data = new Bundle();
//            data.putLong(ConfigurationCommandExecutor.INTENT_EXTRA_MESSAGE_ID, messageId);
//            Intent configurationIntent =
//                    ConfigurationCommandExecutor.getIntentToBroadcast
//                            (Actions.ConfigurationActions.ACTION_CONFIGURATION_COMMAND_EXECUTE, data);
//            //mContext.sendBroadcastAsUser(locationIntent, android.os.Process.myUserHandle());
//            ConfigurationCommandExecutor executor = new ConfigurationCommandExecutor();
//            executor.executeCommand(configurationIntent);

            checkArielSystemStatus(arielSystemStatus);
            Toast.makeText(GuardianApplication.this, "Ariel System status changed: " + arielSystemStatus, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected int getTransition() {
        return TRANSITION_ID_SLIDE;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.setup_parental_control;
    }

    @Override
    protected int getTitleResId() {
        return R.string.parental_control;
    }

    @Override
    protected int getIconResId() {
        return R.drawable.ariel_logo;
    }

}
