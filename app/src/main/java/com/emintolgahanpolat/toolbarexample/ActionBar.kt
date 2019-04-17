package com.emintolgahanpolat.toolbarexample


import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView

class ActionBar : FrameLayout {

    private var mContext: Context? = null
    private lateinit var root: View
    private lateinit var linearLayoutRight: LinearLayout
    private lateinit var linearLayoutLeft:LinearLayout
    private lateinit var toolbar:LinearLayout
    private lateinit var txtTitle: TextView


    constructor(context: Context):  super(context) {

        this.mContext = context
        init()
    }

    private val title: String? = null
    constructor (context: Context, attrs: AttributeSet) :super(context, attrs){

        this.mContext = context
        init()
        initAttrs(attrs)
    }

    constructor (context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {

        this.mContext = context
        init()
        initAttrs(attrs)
    }


    private fun initAttrs(attrs: AttributeSet) {

        val attributes = mContext!!.obtainStyledAttributes(attrs, R.styleable.CustomActionBar, 0, 0)

        val title = attributes.getString(R.styleable.CustomActionBar_title)
        val color = attributes.getResourceId(R.styleable.CustomActionBar_toolbarBackground,R.color.colorPrimaryDark)
        val titleColor = attributes.getColor(R.styleable.CustomActionBar_titleColor,Color.parseColor("#ffffff"))
        val size = attributes.getInt(R.styleable.CustomActionBar_titleSize, 18)

        if (title != null) {
            setTitle(title)
        }
        setTitleSize(size)
        setTitleColor(titleColor)
        setBackground(color)
        attributes.recycle()

    }




    private fun init() {
        val rootView = View.inflate(mContext, R.layout.toolbar, this)
        toolbar=rootView.findViewById(R.id.toolbar)
        txtTitle = rootView.findViewById(R.id.titleTxt) as TextView

        linearLayoutLeft = rootView.findViewById(R.id.linerLayoutLeft) as LinearLayout
        linearLayoutRight = rootView.findViewById(R.id.linerLayoutRight) as LinearLayout
        setTitleSize(18)


    }

    private fun setBackground(id:Int){
        toolbar.setBackgroundResource(id)
    }

    fun setTitleColor(id:Int) {
        txtTitle.setTextColor(id)
    }

    fun setTitle(title: String) {
        txtTitle.text = title
    }


    fun setTitleSize(size: Int) {
        txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, size.toFloat())
    }


    fun setTitleFont(path: String) {
        val tf = Typeface.createFromAsset(mContext!!.assets, path)
        txtTitle.typeface = tf
    }


    fun createRightButton(icon: Int): ImageButton {
        val btn = ImageButton(context)
        btn.setImageResource(icon)
        val scale = context.resources.displayMetrics.density
        val pixels = (8 * scale + 0.5f).toInt()
        btn.setPadding(pixels, pixels, pixels, pixels)
        btn.setBackgroundColor(Color.TRANSPARENT)
        linearLayoutRight!!.addView(btn)
        return btn
    }

    fun createLeftButton(icon: Int): ImageButton {
        val btn = ImageButton(context)
        btn.setImageResource(icon)
        val scale = context.resources.displayMetrics.density
        val pixels = (8 * scale + 0.5f).toInt()
        btn.setPadding(pixels, pixels, pixels, pixels)
        btn.setBackgroundColor(Color.TRANSPARENT)
        linearLayoutLeft!!.addView(btn)
        return btn
    }


}