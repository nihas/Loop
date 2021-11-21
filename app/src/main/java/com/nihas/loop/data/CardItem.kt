package com.nihas.loop.data

data class CardItem(
    var title: String? = null,
    var subTitle: String? = null,
    var imageUrl: String? = null,
){
    companion object{
        fun getCardData(): ArrayList<CardItem> {
            var cardItem = arrayListOf<CardItem>()
            cardItem.add(CardItem("ABOUT US","We have a team of 270 talents, 6 offices around the world.","https://images.unsplash.com/photo-1606857521015-7f9fcf423740?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTB8fG9mZmljZXxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80"))
            cardItem.add(CardItem("SERVICES","We help brands and companies stand out in the digital age.","https://images.unsplash.com/photo-1526628953301-3e589a6a8b74?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjZ8fGJ1c2luZXNzJTIwc2VydmljZXN8ZW58MHx8MHx8&ixlib=rb-1.2.1&w=1000&q=80"))
            cardItem.add(CardItem("WORKS","Some highlights of work we've done for forward thinking clients.","https://images.unsplash.com/photo-1601972602237-8c79241e468b?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8bW9iaWxlJTIwYXBwfGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&w=1000&q=80"))
            cardItem.add(CardItem("LOOP.LABS","Let us show you the ropes — this is how we do things at LOOP.","https://images.unsplash.com/photo-1552664730-d307ca884978?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8bGVhcm5pbmclMjBhbmQlMjBkZXZlbG9wbWVudHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80"))
            cardItem.add(CardItem("CAREERS","Alright then. You’re on your way to future digital masterpieces.","https://images.unsplash.com/photo-1507679799987-c73779587ccf?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8Y2FyZWVyfGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&w=1000&q=80"))
            cardItem.add(CardItem("CONTACT","Become a Client / Inquiries / Careers / General Request","https://images.unsplash.com/uploads/1413222992504f1b734a6/1928e537?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTV8fGNvbnRhY3R8ZW58MHx8MHx8&ixlib=rb-1.2.1&w=1000&q=80"))
            return cardItem
        }
    }
}
