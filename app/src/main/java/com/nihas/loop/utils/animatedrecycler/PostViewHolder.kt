package com.nihas.loop.utils.animatedrecycler

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import com.nihas.loop.databinding.AboutusItemBinding
import com.nihas.loop.databinding.DetailsItemBinding
import com.nihas.loop.databinding.ServicesItemBinding
import com.nihas.loop.data.AboutData
import com.nihas.loop.data.CardItem
import com.nihas.loop.data.ServiceData

class PostViewHolder(var view: View) : AnimatedItemHolder(view) {
    private var animator: AnimatorSet? = null

    fun bind(post: Any) {
        when(post){
            is CardItem -> {
                val binding = DetailsItemBinding.bind(view)
                binding.apply {
                    titleText.text = post.title
                    subTitleText.text = post.subTitle
                }
            }
            is AboutData -> {
                val binding = AboutusItemBinding.bind(view)
                binding.apply {
                    numberText.text = post.number
                    sentenceText.text = post.message
                    quoteText.text = post.quote
                }
            }
            is ServiceData -> {
                val binding = ServicesItemBinding.bind(view)
                binding.apply {
                    serviceText.text = post.message
                }
            }
        }
    }

//    private fun getAvatarUrl(post: CardItem) =
//        "https://api.adorable.io/avatars/200/${post.title}.png"

    override fun onEnterFromTop() {
        animator?.cancel()
        animateTranslation(
            startTranslationValue = -ANIMATED_TRANSLATION_AMOUNT,
            finalTranslationValue = 0f,
            startAlphaValue = itemView.alpha,
            finalAlphaValue = 1f
        )
    }

    override fun onExitToTop() {
        animator?.cancel()
        animateTranslation(
            startTranslationValue = 0f,
            finalTranslationValue = -ANIMATED_TRANSLATION_AMOUNT,
            startAlphaValue = itemView.alpha,
            finalAlphaValue = 0f
        )
    }

    override fun onEnterFromBottom() {
        animator?.cancel()
        animateTranslation(
            startTranslationValue = ANIMATED_TRANSLATION_AMOUNT,
            finalTranslationValue = 0f,
            startAlphaValue = itemView.alpha,
            finalAlphaValue = 1f
        )
    }

    override fun onExitToBottom() {
        animator?.cancel()
        animateTranslation(
            startTranslationValue = 0f,
            finalTranslationValue = ANIMATED_TRANSLATION_AMOUNT,
            startAlphaValue = itemView.alpha,
            finalAlphaValue = 0f
        )
    }

    override fun onExitToLeft(swipeDirection: String) {
        animator?.cancel()
        animateLeftTranslation(
            startTranslationValue = 0f,
            finalTranslationValue = if(swipeDirection == "RIGHT") -ANIMATED_TRANSLATION_AMOUNT else ANIMATED_TRANSLATION_AMOUNT,
            startAlphaValue = itemView.alpha,
            finalAlphaValue = 0f
        )
    }

    override fun onEnterFromRight(swipeDirection: String) {
        animator?.cancel()
        animateLeftTranslation(
            startTranslationValue = if(swipeDirection == "RIGHT") ANIMATED_TRANSLATION_AMOUNT else -ANIMATED_TRANSLATION_AMOUNT,
            finalTranslationValue = 0f,
            startAlphaValue = itemView.alpha,
            finalAlphaValue = 1f
        )
    }

    private fun animateTranslation(
        startTranslationValue: Float,
        finalTranslationValue: Float,
        startAlphaValue: Float,
        finalAlphaValue: Float
    ) {
        val translationAnimator =
            ObjectAnimator
                .ofFloat(itemView, "translationY", startTranslationValue, finalTranslationValue)

        val alphaAnimator =
            ObjectAnimator
                .ofFloat(itemView, "alpha", startAlphaValue, finalAlphaValue)

        animator = AnimatorSet().apply {
            playTogether(translationAnimator, alphaAnimator)
            duration = ANIMATION_DURATION
            start()
        }
    }


    private fun animateLeftTranslation(
        startTranslationValue: Float,
        finalTranslationValue: Float,
        startAlphaValue: Float,
        finalAlphaValue: Float
    ) {
        val translationAnimator =
            ObjectAnimator
                .ofFloat(itemView, "translationX", startTranslationValue, finalTranslationValue)

        val alphaAnimator =
            ObjectAnimator
                .ofFloat(itemView, "alpha", startAlphaValue, finalAlphaValue)

        animator = AnimatorSet().apply {
            playTogether(translationAnimator, alphaAnimator)
            duration = ANIMATION_EXIT_DURATION
            start()
        }
    }

    private companion object {
        const val ANIMATION_DURATION = 150L
        const val ANIMATION_EXIT_DURATION = 150L
        const val ANIMATED_TRANSLATION_AMOUNT = 200f
    }
}