package com.acronym.app.livedata

class EventObject(key:Int, vararg value:Any) {
    var key:Int?=null
    var value:Array<Any>
    init{
        this.key = key
        this.value = value as Array<Any>
    }
}