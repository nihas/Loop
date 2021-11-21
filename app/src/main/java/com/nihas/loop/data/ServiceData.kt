package com.nihas.loop.data

data class ServiceData(
    var number: String? = null,
    var message: String? = null
){
    companion object{
        fun getServices(): ArrayList<ServiceData> {
            var aboutArr = arrayListOf<ServiceData>()
            aboutArr.add(ServiceData("01","Strategy and Creativity"))
            aboutArr.add(ServiceData("02","Design and User Experience"))
            aboutArr.add(ServiceData("03","Development and Technology"))
            aboutArr.add(ServiceData("04","Business Models nd e-commerce"))
            aboutArr.add(ServiceData("05","Apps and Mobile"))
            aboutArr.add(ServiceData("06","Social Media"))
            aboutArr.add(ServiceData("07","Realtime Marketing"))
            aboutArr.add(ServiceData("08","Performance and Media"))
            aboutArr.add(ServiceData("08","Content and Production"))
            aboutArr.add(ServiceData("08","Analytics"))

            aboutArr.add(ServiceData("01","Strategy and Creativity"))
            aboutArr.add(ServiceData("02","Design and User Experience"))
            aboutArr.add(ServiceData("03","Development and Technology"))
            aboutArr.add(ServiceData("04","Business Models nd e-commerce"))
            aboutArr.add(ServiceData("05","Apps and Mobile"))
            aboutArr.add(ServiceData("06","Social Media"))
            aboutArr.add(ServiceData("07","Realtime Marketing"))
            aboutArr.add(ServiceData("08","Performance and Media"))
            aboutArr.add(ServiceData("08","Content and Production"))
            aboutArr.add(ServiceData("08","Analytics"))
            return aboutArr
        }
    }
}
