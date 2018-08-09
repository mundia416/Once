package com.nosetrap.oncelib

import android.content.Context
import com.nosetrap.storage.prefs.PreferenceHandler


/**
 * run a block of code once
 * to run different blocks of code once, use a different id on the object
 */
class Once(private val context: Context) {

    constructor(context: Context, id: Int) : this(context){
        this.id = id
    }

    companion object {

        private const val isFirstRun = "pref_is_first_run"

        /**
         * check if the code block in the specified id is executed
         */
        fun isExecuted(context: Context,id: Int): Boolean{
            val preferenceHandler = PreferenceHandler(context)
            val isFirstRun = preferenceHandler.get(isFirstRun+id,true)
            return !isFirstRun
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
    fun isExecuted(){
        Companion.isExecuted(context, id)
    }
    
    /**
     * code that is in this method is only ever run once in the app. it can be called from anywhere
     * in the app.
     * @return true if the code successfully executes and false if the code has already been executed before
     */
    fun execute(runOnceCode: RunOnce): Boolean {
        return if (preferenceHandler.get(isFirstRun+id, true)) {
            runOnceCode.run()
            preferenceHandler.edit(isFirstRun+id, false)
            true
        } else {
            false
        }
    }
}
