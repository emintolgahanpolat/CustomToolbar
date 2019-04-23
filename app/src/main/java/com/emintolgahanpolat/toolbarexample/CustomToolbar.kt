package com.emintolgahanpolat.toolbarexample


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import kotlinx.android.synthetic.main.toolbar.view.*
import android.text.Editable
import android.text.TextWatcher
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.RecyclerView
import android.view.animation.AccelerateInterpolator
import android.opengl.ETC1.getHeight
import android.support.v4.view.ViewCompat.animate
import android.R.attr.translationY
import android.widget.FrameLayout




class CustomToolbar : FrameLayout, View.OnClickListener {


    private var mContext: Context? = null
    private lateinit var root: View

    private lateinit var actionbarView: FrameLayout
    private lateinit var linearLayoutRight: LinearLayout
    private lateinit var linearLayoutLeft: LinearLayout

    private lateinit var toolbarSearchView: FrameLayout
    private lateinit var toolbarLargeTitleView: FrameLayout


    private lateinit var etSearch: EditText
    private lateinit var ibClearText: ImageButton
    private lateinit var tvCancel: TextView

    private lateinit var toolbar: LinearLayout
    private lateinit var txtTitle: TextView
    private lateinit var txtLargeTitle: TextView
    private lateinit var titleIcon: ImageView
    private lateinit var titleLargeIcon: ImageView

    private lateinit var separator: View


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

    private fun init() {
        val rootView = View.inflate(mContext, R.layout.toolbar, this)
        toolbar = rootView.findViewById(R.id.toolbar)
        txtTitle = rootView.findViewById(R.id.titleTxt) as TextView
        txtLargeTitle = rootView.findViewById(R.id.titleLargeTxt) as TextView
        titleIcon = rootView.findViewById(R.id.titleIcon) as ImageView
        titleLargeIcon= rootView.findViewById(R.id.titleLargeIcon) as ImageView

        actionbarView = rootView.findViewById(R.id.actionbar) as FrameLayout
        linearLayoutLeft = rootView.findViewById(R.id.linerLayoutLeft) as LinearLayout
        linearLayoutRight = rootView.findViewById(R.id.linerLayoutRight) as LinearLayout

        toolbarLargeTitleView = rootView.findViewById(R.id.toolbarLargeTitleView) as FrameLayout

        toolbarSearchView = rootView.findViewById(R.id.toolbarSearchView) as FrameLayout
        etSearch = rootView.findViewById(R.id.etSearch) as EditText
        etSearch.isCursorVisible = false
        ibClearText = rootView.findViewById(R.id.ibClearText) as ImageButton
        ibClearText.visibility = View.GONE
        tvCancel = rootView.findViewById(R.id.tvCancel) as TextView


        separator = rootView.findViewById(R.id.separator) as View


        initListener()

    }
    private fun initAttrs(attrs: AttributeSet) {

        val attributes = mContext!!.obtainStyledAttributes(attrs, R.styleable.CustomActionBar, 0, 0)

        val title = attributes.getString(R.styleable.CustomActionBar_title)
        val background = attributes.getColor(R.styleable.CustomActionBar_toolbarBackground, Color.parseColor("#f8f8f8"))
        val titleColor = attributes.getColor(R.styleable.CustomActionBar_titleColor, Color.parseColor("#000000"))
        val titleIcon = attributes.getResourceId(R.styleable.CustomActionBar_titleIcon, -1)
        val size = attributes.getInt(R.styleable.CustomActionBar_titleSize, 18)

        val separator = attributes.getColor(R.styleable.CustomActionBar_separator, Color.parseColor("#c0c0c0"))

        setTitleIcon(titleIcon)

        setTitle(title)
        setTitleSize(size)
        setTitleColor(titleColor)
        setBackground(background)
        setSeparator(separator)

        toolbarState(toolbarStates.SEARCH_PASSIVE)
        attributes.recycle()

    }

    private fun setTitleIcon(icon: Int) {
        if (icon != -1) {
            titleIcon.visibility = View.VISIBLE
            titleIcon.setImageResource(icon)

            titleLargeIcon.visibility = View.VISIBLE
            titleLargeIcon.setImageResource(icon)
        } else {
            titleIcon.visibility = View.GONE
            titleLargeIcon.visibility = View.GONE
        }
    }




    private fun initListener() {
        etSearch.setOnClickListener(this)
        tvCancel.setOnClickListener(this)
        ibClearText.setOnClickListener(this)

        titleTxt.setOnClickListener(this)

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.length > 0) {
                    //searchState(true)
                    toolbarState(toolbarStates.SEARCH_ACTIVE)
                    ibClearText.visibility = View.VISIBLE
                }
            }
        })
    }

    private lateinit var nestedScrollView: NestedScrollView
    @SuppressLint("NewApi")
    fun setListView(recyclerView: RecyclerView) {

        recyclerView.setOnScrollListener(object : HidingScrollListener() {
            override fun onHide() {
                toolbarState(toolbarStates.TITLE)
                //toolbarSearchView.animate().alpha(0F)
                toolbar.animate().translationY(-(toolbarSearchView.height.toFloat()+toolbarLargeTitleView.height.toFloat()))
                actionbarView.animate().translationY((toolbarSearchView.height.toFloat()+toolbarLargeTitleView.height.toFloat()))

            }

            override fun onShow() {
                toolbarState(toolbarStates.SEARCH_PASSIVE)
                toolbarSearchView.animate().alpha(1F)
                toolbar.animate().translationY(0F)
                actionbarView.animate().translationY(0F)

            }
        })
    }

    private enum class toolbarStates {
        LARGE_TITLE,
        TITLE,
        SEARCH_ACTIVE,
        SEARCH_PASSIVE

    }

    private fun toolbarState(state: toolbarStates) {
        when (state) {
            toolbarStates.LARGE_TITLE -> {

/*
                actionbar.visibility = View.VISIBLE
                toolbarLargeTitleView.visibility = View.VISIBLE
                toolbarSearchView.visibility = View.GONE
*/

                titleIcon.visibility = View.GONE
                titleTxt.visibility = View.GONE

                titleLargeIcon.visibility = View.VISIBLE
                titleLargeTxt.visibility = View.VISIBLE
            }
            toolbarStates.TITLE -> {
/*
                actionbar.visibility = View.VISIBLE
                toolbarLargeTitleView.visibility = View.GONE
                toolbarSearchView.visibility = View.GONE
                */
                titleIcon.visibility = View.VISIBLE
                titleTxt.visibility = View.VISIBLE

                titleLargeIcon.visibility = View.GONE
                titleLargeTxt.visibility = View.GONE
            }


            toolbarStates.SEARCH_ACTIVE -> {

                actionbar.visibility = View.GONE
                toolbarLargeTitleView.visibility = View.GONE
                toolbarSearchView.visibility = View.VISIBLE

                //toolbar.animate().translationY(-(actionbarView.height.toFloat()+toolbarLargeTitleView.height.toFloat()))
                etSearch.isCursorVisible = true
                tvCancel.visibility = View.VISIBLE
                tvCancel.isEnabled = true
            }
            toolbarStates.SEARCH_PASSIVE -> {
                toolbarState(toolbarStates.LARGE_TITLE)

                actionbar.visibility = View.VISIBLE
                toolbarLargeTitleView.visibility = View.VISIBLE
                toolbarSearchView.visibility = View.VISIBLE


                //  toolbar.animate().translationY(0F)
                hideKeyboard(context as Activity)
                tvCancel.visibility = View.GONE
                tvCancel.isEnabled = false
                etSearch.text = null
                etSearch.isCursorVisible = false
                ibClearText.visibility = View.GONE
            }
        }
    }


    fun setSeparator(color: Int) {
        separator.setBackgroundColor(color)
    }


    fun toolbarSearchViewHeight(i: Float) {



        val translationY = -(i * ((toolbarSearchView.height)+(toolbarLargeTitleView.height)))
        toolbarSearchView.setTranslationY(translationY)

        val translationY2 = -(i *  ((toolbarSearchView.height)+(toolbarLargeTitleView.height)))
        toolbar.setTranslationY(translationY2)


        val translationY3 = (i * ((toolbarSearchView.height)+(toolbarLargeTitleView.height)))
        actionbarView.setTranslationY(translationY3)


    }


    fun setBackground(id: Int) {
        toolbar.setBackgroundColor(id)
    }

    fun setTitleColor(id: Int) {
        txtTitle.setTextColor(id)
        txtLargeTitle.setTextColor(id)
    }

    fun setTitle(title: String) {
        if (title != null) {
            txtTitle.text = title
            txtLargeTitle.text = title
        }
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
        linearLayoutRight.addView(btn)
        return btn
    }

    fun createLeftButton(icon: Int): ImageButton {
        val btn = ImageButton(context)
        btn.setImageResource(icon)
        val scale = context.resources.displayMetrics.density
        val pixels = (8 * scale + 0.5f).toInt()
        btn.setPadding(pixels, pixels, pixels, pixels)
        btn.setBackgroundColor(Color.TRANSPARENT)
        linearLayoutLeft.addView(btn)
        return btn
    }



    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.ibClearText -> {
                etSearch.text = null
                ibClearText.visibility = View.GONE
            }
            R.id.tvCancel -> {
                //searchState(false)
                toolbarState(toolbarStates.SEARCH_PASSIVE)
            }
            R.id.etSearch -> {
                toolbarState(toolbarStates.SEARCH_ACTIVE)
                // searchState(true)
            }
            R.id.titleTxt->{
                nestedScrollView.smoothScrollTo(0,0)
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
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}