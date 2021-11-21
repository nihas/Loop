package com.nihas.loop.data

data class AboutData(
    var number: String? = null,
    var message: String? = null,
    var quote: String? = null
){
    companion object{
        fun getAbout(): ArrayList<AboutData> {
            var aboutArr = arrayListOf<AboutData>()
            aboutArr.add(AboutData("01","Clients are friends. We wouldn’t be here without them.","TRUST US"))
            aboutArr.add(AboutData("02","We’re 100% independent and owner managed.","RELIABLE"))
            aboutArr.add(AboutData("03","There is no sales team, we just do great work.","IT'S TRUE"))
            aboutArr.add(AboutData("04","270 digital talents care about your project.","ALL-IN"))
            aboutArr.add(AboutData("05","Expect creativity, technology and strategic thinking.","BIG PICTURE"))
            aboutArr.add(AboutData("06","We know how to play digital for brand value.","IT WORKS"))
            aboutArr.add(AboutData("07","We're a consumer engagement one-stop shop.","JOKER"))
            aboutArr.add(AboutData("08","We've got an eye for great content.","HELL YES"))

            aboutArr.add(AboutData("09","Clients are friends. We wouldn’t be here without them.","TRUST US"))
            aboutArr.add(AboutData("10","We’re 100% independent and owner managed.","RELIABLE"))
            aboutArr.add(AboutData("11","There is no sales team, we just do great work.","IT'S TRUE"))
            aboutArr.add(AboutData("12","270 digital talents care about your project.","ALL-IN"))
            aboutArr.add(AboutData("13","Expect creativity, technology and strategic thinking.","BIG PICTURE"))
            aboutArr.add(AboutData("14","We know how to play digital for brand value.","IT WORKS"))
            aboutArr.add(AboutData("15","We're a consumer engagement one-stop shop.","JOKER"))
            aboutArr.add(AboutData("16","We've got an eye for great content.","HELL YES"))
            return aboutArr
        }
    }
}
