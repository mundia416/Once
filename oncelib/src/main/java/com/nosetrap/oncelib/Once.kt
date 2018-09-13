package com.nosetrap.oncelib

import android.content.Context
import com.nosetrap.storage.prefs.PreferenceHandler


/**
 * run a block of code once
 * to run different blocks of code once, use a different id on the object
 */
class Once(private val context: Context) {

    /**
     * constructor
     */
    constructor(context: Context, id: Int) : this(context){
        this.id = id
    }

    companion object {

        /**
         * preference for when the app is first installed
         */
        private const val isFirstRun = "pref_is_first_run"

        /**
         * preference for when the app is updated,the version code is stored when the code is executed
         */
        private const val isUpdateRun = "pref_updated_version_code_run"


        /**
         * check if the code block in the specified id is executed
         */
        fun isExecuted(context: Context, id: Int): Boolean {
            val preferenceHandler = PreferenceHandler(context)
            val isFirstRun = preferenceHandler.get(isFirstRun + id, true)
            return !isFirstRun
        }

        fun isUpdateExecuted(context: Context, id: Int): Boolean {
            val preferenceHandler = PreferenceHandler(context)

            val currentAppVersionCode = context.packageManager.getPackageInfo(context.packageName, 0).versionCode
            val savedAppVersionCode = preferenceHandler.get(isUpdateRun + id, 0)

            return (savedAppVersionCode == currentAppVersionCode)
        }
    }



    /**
     * is used to set the id of the block of code that should be executed once
     */
    var id: Int = 0

    private val preferenceHandler = PreferenceHandler(context)

    /**
     * check if the code block in this once object has already been executed
     */
    fun isExecuted(): Boolean {
       return Companion.isExecuted(context, id)
    }

    /**
     *
     */
    fun isUpdateExecuted(): Boolean {
       return Companion.isUpdateExecuted(context, id)
    }

    /**
     * code that is in this method is only ever run once in the app. it can be called from anywhere
     * in the app.
     * @return true if the code successfully executes and false if the code has already been executed before
     */
    fun execute(runOnceCode: RunOnce): Boolean {
        return if (!isExecuted()) {
            runOnceCode.run()
            preferenceHandler.edit(isFirstRun+id, false)
            true
        } else {
            false
        }
    }

    /**
     * code that is in this method is run once everytime the app is updated. it can be called from anywhere
     * in the app.
     * @return true if the code successfully executes and false if the code has already been executed before
     */
    fun executeOnUpdated(runOnceCode: RunOnce): Boolean{
        val currentAppVersionCode =  context.packageManager.getPackageInfo(context.packageName,0).versionCode

        return if (!isUpdateExecuted()) {
            runOnceCode.run()
            preferenceHandler.edit(isUpdateRun+id, currentAppVersionCode)
            true
        } else {
            false
        }
    }
}
