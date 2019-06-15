package com.nosetrap.oncelib

import android.content.Context

/**
 * run a block of code once
 * to run different blocks of code once, use a different id on the object
 */
class Once(private val context: Context) {

    /**
     * preference for when the app is first installed
     */
    private val isFirstRun = "pref_is_first_run"

    /**
     * preference for when the app is updated,the version code is stored when the code is executed
     */
    private val isUpdateRun = "pref_updated_version_code_run"

    /**
     * constructor
     */
    constructor(context: Context, id: Int) : this(context){
        this.id = id
    }

    /**
     * is used to set the id of the block of code that should be executed once
     */
    var id: Int = 0

    private val pref = context.getSharedPreferences("default_shared_preferences_name",Context.MODE_PRIVATE)
    private val editor = pref.edit()

    /**
     * check if the code block in the specified id is executed
     */
    fun isExecuted(id: Int): Boolean {
        val isFirstRun = pref.getBoolean(isFirstRun + id, true)
        return !isFirstRun
    }

    fun isUpdateExecuted(id: Int): Boolean {
        val currentAppVersionCode = context.packageManager.getPackageInfo(context.packageName, 0).versionCode
        val savedAppVersionCode = pref.getInt(isUpdateRun + id, 0)

        return (savedAppVersionCode == currentAppVersionCode)
    }

    /**
     * check if the code block in this once object has already been executed
     */
    fun isExecuted(): Boolean {
       return isExecuted(id)
    }

    /**
     *
     */
    fun isUpdateExecuted(): Boolean {
       return isUpdateExecuted(id)
    }

    /**
     * code that is in this method is only ever run once in the app. it can be called from anywhere
     * in the app.
     * @return true if the code successfully executes and false if the code has already been executed before
     * @param runOnceCode this is the method where to put the code that should be run only once throughout the
     * apps entire lifetime
    */
    fun execute(runOnceCode: () -> Unit): Boolean {
        return if (!isExecuted()) {
            runOnceCode.invoke()
            editor.putBoolean(isFirstRun+id, false)
            true
        } else {
            false
        }
    }

    /**
     * code that is in this method is run once everytime the app is updated. it can be called from anywhere
     * in the app.
     * @return true if the code successfully executes and false if the code has already been executed before
     *  @param runOnceCode this is the method where to put the code that should be run only once throughout the
     * apps entire lifetime
     */
    fun executeOnUpdated(runOnceCode: () -> Unit): Boolean{
        val currentAppVersionCode =  context.packageManager.getPackageInfo(context.packageName,0).versionCode

        return if (!isUpdateExecuted()) {
            runOnceCode.invoke()
            editor.putInt(isUpdateRun+id, currentAppVersionCode)
            true
        } else {
            false
        }
    }
}
