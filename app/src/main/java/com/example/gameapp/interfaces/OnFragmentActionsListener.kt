package com.example.gameapp.interfaces

/**
 * Different actions when calling/closing fragments in activities.
 *
 */
interface OnFragmentActionsListener {

    /**
     * Executes an action based on current fragment id
     *
     * @param id
     */
    fun onClickFragmentButton( detail: String, id: Int, data: Unit? )
    fun onCloseFragment()
}