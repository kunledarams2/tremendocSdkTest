package com.e.tremendocSDK.View.UI.Fragment.Signup

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.InputType
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

import com.e.tremendocSDK.R
import com.e.tremendocSDK.View.Callback.FragmentChanger
import com.e.tremendocSDK.View.UI.Activity.Signup
import com.e.tremendocSDK.View.UI.Fragment.FragmentTitled
import com.e.tremendocSDK.View.UI.UUitil.IO


class Stepone : FragmentTitled(), View.OnClickListener {
    private var continueBtn: Button? = null
    private var usernameField: EditText? = null
    private var password: EditText? = null
    private var passwordconfirm: EditText? = null
    private var indicator: TextView? = null
    private var reviewbtn: ImageButton? = null
    private var visible = false
    private var fragmentChanger: FragmentChanger? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            //
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_stepone, container, false)
        fragmentChanger = activity as Signup?
        setView(view)
        //

        return view

    }

    private fun setView(view: View) {

        usernameField = view.findViewById(R.id.username)
        password = view.findViewById(R.id.password)
        passwordconfirm = view.findViewById(R.id.password_confirm)
        indicator = view.findViewById(R.id.indicator)
        reviewbtn = view.findViewById(R.id.reveal_btn)

        continueBtn = view.findViewById(R.id.next_btn)
        continueBtn!!.setOnClickListener(this)

        reviewbtn!!.setOnClickListener { view1 ->
            if (visible) {
                password!!.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                password!!.setSelection(password!!.length())

                visible = false
                reviewbtn!!.setImageResource(R.drawable.ic_eye_black)
            } else {
                password!!.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                password!!.setSelection(password!!.length())
                visible = true
                reviewbtn!!.setImageResource(R.drawable.ic_eye_green)
            }
        }

        passwordconfirm!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {

                val confirm = editable.toString()
                val passwordField = password!!.text.toString()

                if (!TextUtils.isEmpty(passwordField) && !TextUtils.isEmpty(confirm)) {
                    if (passwordField == confirm) {
                        indicator!!.setBackgroundResource(R.drawable.ic_check_green_small)
                    } else {
                        indicator!!.setBackgroundResource(R.drawable.ic_close_red_small)
                    }
                }

            }
        })

    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed() {

    }


    override fun onClick(view: View) {

        if (view === continueBtn) {
            Process()
        }
    }


    private fun Process() {


        val username = usernameField!!.text.toString()
        val password1 = password!!.text.toString()
        val password2 = passwordconfirm!!.text.toString()

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(context, "Username is required", Toast.LENGTH_LONG).show()
        } else if (TextUtils.isEmpty(password1)) {
            Toast.makeText(context, "Password is required", Toast.LENGTH_LONG).show()
        } else if (password1.length < 6) {
            Toast.makeText(context, "Password should be a minimum of 6 characters", Toast.LENGTH_LONG).show()
        } else if (password1 != password2) {
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
        }
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password1) && !TextUtils.isEmpty(password2)
                && password1 == password2 && password1.length > 6) {

            IO.setData(context!!, USERNAME, username)
            IO.setData(context!!, PASSWORD, password1)

            fragmentChanger!!.ChangeFragment(Steptwo.newInstance())
            val getIO = IO.getData(context!!, USERNAME)
            Toast.makeText(context, getIO, Toast.LENGTH_LONG).show()
        }
        //        Intent intent= new Intent(getContext(), Finddoctor.class);
        //        startActivity(intent);
    }

    companion object {

        val USERNAME = "sign_up_username"
        val PASSWORD = "sign_up_password"


        fun newInstance(): Stepone {
            val fragment = Stepone()
            fragment.title = Signup.STEP_ONE
            return fragment
        }
    }


}// Required empty public constructor
