package com.nosetrap.oncelib

interface RunOnce {
    /**
     * this is the method where to put the code that should be run only once throughout the
     * apps entire lifetime
     */
    fun run()
}