package com.aleksodem.avatarview

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.support.annotation.DimenRes
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.DimenRes
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.flicent.fieldpulseengage.R
import kotlinx.android.synthetic.main.component_avatar.view.*
import kotlin.math.roundToInt


@SuppressLint("ViewConstructor")
class Avatar(context: Activity, @DimenRes val dimenRes: Int) : View(context) {

    private val view = context.layoutInflater.inflate(R.layout.component_avatar, null, false)
    private val sizeAvatar = context.resources.getDimension(dimenRes).roundToInt()


    init {
        view.parentView.layoutParams = LinearLayout.LayoutParams(sizeAvatar, sizeAvatar)
        val layoutParams1 = FrameLayout.LayoutParams((sizeAvatar * 0.2).roundToInt(), (sizeAvatar * 0.2).roundToInt())
        layoutParams1.gravity = Gravity.END or Gravity.BOTTOM
        layoutParams1.setMargins(0,0, (sizeAvatar * 0.04).roundToInt(), (sizeAvatar * 0.04F).roundToInt())
        view.status.layoutParams = layoutParams1
    }

    fun setImage(drawable: Drawable?) {
        if (drawable != null) {
            view.image_profile.setImageDrawable(drawable)
            view.image_profile.visibility = VISIBLE
        } else {
            view.image_profile.visibility = GONE
        }
    }

    fun setLetter(letter: String, email: String) {
        val generator = ColorGenerator.MATERIAL
        val color = generator.getColor(email)

        val builder = TextDrawable.builder()
            .beginConfig()
            .fontSize((sizeAvatar * 0.3).roundToInt())
            .bold()
            .useFont(Typeface.SANS_SERIF)
            .toUpperCase()
            .withBorder(0)
            .endConfig()
            .round()

        val ic = builder.build(letter, color)

        view.image_letter.setImageDrawable(ic)
    }

    fun setStatus(status: StatusUser) {
        val myCircle = resources.getDrawable(R.drawable.status_avatar) as GradientDrawable
        myCircle.setStroke((sizeAvatar * 0.04).roundToInt(), Color.WHITE)
        myCircle.setColor(status.color)
        view.status.setBackgroundDrawable(myCircle)
        view.status.visibility = View.VISIBLE
    }

    enum class StatusUser(val color: Int) {
        ONLINE(Color.parseColor("#66cc55")),
        BUSY(Color.parseColor("#ee3277")),
        OFFLINE(Color.parseColor("#bbbcbf"));
    }


    fun getView() : View {
        return view
    }
}