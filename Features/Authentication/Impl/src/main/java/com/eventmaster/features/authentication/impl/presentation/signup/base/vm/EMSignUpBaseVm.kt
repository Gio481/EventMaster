package com.eventmaster.features.authentication.impl.presentation.signup.base.vm

import com.eventmaster.core.presentation.base.vm.EMScreenBaseVm
import com.eventmaster.core.presentation.base.vm.stateWithValue
import com.eventmaster.core.presentation.components.textfield.base.model.EMTextFieldValidatedState
import com.eventmaster.core.presentation.components.textfield.base.model.EMTextFieldValidatedState.Default

abstract class EMSignUpBaseVm : EMScreenBaseVm() {

    val textFieldValidatedState = stateWithValue<EMTextFieldValidatedState>(Default)
    val fieldIsAlreadyValidated = stateWithValue(false)

    abstract fun nextStep()

    fun changeTextFieldValidatedState(state: EMTextFieldValidatedState) {
        textFieldValidatedState.statePost(state)
    }

    fun setFieldIsAlreadyValidatedValue(value: Boolean) {
        fieldIsAlreadyValidated.statePost(value)
    }
}