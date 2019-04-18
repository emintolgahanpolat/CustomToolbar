package com.emintolgahanpolat.toolbarexample


import android.annotation.SuppressLint
import android.app.Activity
import android.app.Service
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import kotlinx.android.synthetic.main.toolbar.view.*
import android.content.Context.INPUT_METHOD_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.text.Editable
import android.text.TextWatcher


class ActionBar : FrameLayout, View.OnClickListener {


    private var mContext: Context? = null
    private lateinit var root: View
    private lateinit var linearLayoutRight: LinearLayout
    private lateinit var linearLayoutLeft: LinearLayout
    private lateinit var toolbarSearchView: FrameLayout
    private lateinit var etSearch: EditText
    private lateinit var tvCancel: TextView
    private lateinit var toolbar: LinearLayout
    private lateinit var txtTitle: TextView


    constructor(context: Context) : super(context) {

        this.mContext = context
        init()
    }

    private val title: String? = null

    constructor (context: Context, attrs: AttributeSet) : super(context, attrs) {

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
        val background = attributes.getColor(R.styleable.CustomActionBar_toolbarBackground, Color.parseColor("#f8f8f8"))
        val titleColor = attributes.getColor(R.styleable.CustomActionBar_titleColor, Color.parseColor("#000000"))
        val size = attributes.getInt(R.styleable.CustomActionBar_titleSize, 18)

        if (title != null) {
            setTitle(title)
        }
        setTitleSize(size)
        setTitleColor(titleColor)
        setBackground(background)
        attributes.recycle()

    }


    private fun init() {
        val rootView = View.inflate(mContext, R.layout.toolbar, this)
        toolbar = rootView.findViewById(R.id.toolbar)
        txtTitle = rootView.findViewById(R.id.titleTxt) as TextView

        linearLayoutLeft = rootView.findViewById(R.id.linerLayoutLeft) as LinearLayout
        linearLayoutRight = rootView.findViewById(R.id.linerLayoutRight) as LinearLayout

        toolbarSearchView = rootView.findViewById(R.id.toolbarSearchView) as FrameLayout
        etSearch = rootView.findViewById(R.id.etSearch) as EditText
        etSearch.isCursorVisible=false
        tvCancel = rootView.findViewById(R.id.tvCancel) as TextView
        setTitleSize(18)


        initListener()

    }

    private fun initListener() {
        etSearch.setOnClickListener(this)
        tvCancel.setOnClickListener(this)

        etSearch.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (count>0){
                    tvCancel.setTextColor(Color.parseColor("#397ef5"))
                    tvCancel.text="Search"
                }else{
                    tvCancel.setTextColor(Color.parseColor("#989898"))
                    tvCancel.text="Cancel"
                }
            }

        })

    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tvCancel -> {
                tvCancel.visibility = View.GONE
                tvCancel.isEnabled = false
                etSearch.text = null
            etSearch.isCursorVisible=false

                hideKeyboard(context as Activity)
            }
            R.id.etSearch -> {
                etSearch.isCursorVisible=true
                tvCancel.visibility = View.VISIBLE
                tvCancel.isEnabled = true
            }
        }

    }

    @SuppressLint("ServiceCast")
    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm!!.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showHideSearchBar(show: Boolean) {
        if (show) {
            toolbarSearchView.visibility = View.VISIBLE
        } else {

            toolbarSearchView.visibility = View.GONE
        }
    }

    fun setBackground(id: Int) {
        toolbar.setBackgroundColor(id)
    }

    fun setTitleColor(id: Int) {
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