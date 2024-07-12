package com.marvel.core.platform

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marvel.core.extensions.Event

abstract class BaseViewModel: ViewModel() {

    private val _baseEvent = MutableLiveData<Event<BaseViewEvent>>()
    val baseEvent: LiveData<Event<BaseViewEvent>> = _baseEvent
    fun sendEvent(event: BaseViewEvent) {
        _baseEvent.postValue(Event(event))
    }
}