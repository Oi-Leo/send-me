package com.example.sendme.core.profile.view

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.sendme.R

class ProfileFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

}