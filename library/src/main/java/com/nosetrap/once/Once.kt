package com.nosetrap.once

import android.content.Context
import com.nosetrap.storage.prefs.PreferenceHandler


class Once(private val context: Context,private val preferenceHandler: PreferenceHandler) {

    private val isFirstRun = "pref_is_first_run"
        /**
         * code that is in this method is only ever run once in the app. it can be called from anywhere
         * in the app.
         * @return true if the code successfully executes and false if the code has already been executed before
         */
        fun execute(runOnceCode: RunOnce): Boolean {
           if(preferenceHandler.get(isFirstRun,true)){
               runOnceCode.run()
               preferenceHandler.edit(isFirstRun,false)
               return true
           }else{
               return false
           }

    }
}
