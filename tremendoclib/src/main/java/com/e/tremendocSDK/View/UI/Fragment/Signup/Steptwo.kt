package com.e.tremendocSDK.View.UI.Fragment.Signup

import android.app.DatePickerDialog
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import com.e.tremendocSDK.R
import com.e.tremendocSDK.Service.Model.SDK_User
import com.e.tremendocSDK.View.Callback.ModelSaver
import com.e.tremendocSDK.View.UI.Activity.Signup
import com.e.tremendocSDK.View.UI.Fragment.FragmentTitled
import com.e.tremendocSDK.View.UI.UUitil.IO

import java.util.ArrayList
import java.util.Calendar


/**
 *
 */
class Steptwo : FragmentTitled(), View.OnClickListener {

    private var firstname: EditText? = null
    private var lastname: EditText? = null
    private var phone: EditText? = null
    private var email: EditText? = null
    private var dob: EditText? = null
    private var gender: AutoCompleteTextView? = null
    private var dobDialogOpen = false
    private var signUp: Button? = null
    private var modelSaver: ModelSaver<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_steptwo, container, false)
        setView(view)
        modelSaver = activity as Signup?
        return view
    }

    private fun setView(view: View) {

        firstname = view.findViewById(R.id.firstname)
        lastname = view.findViewById(R.id.lastname)
        phone = view.findViewById(R.id.phone)
        email = view.findViewById(R.id.email)

        dob = view.findViewById(R.id.dob)
        dob!!.showSoftInputOnFocus = false
        dob!!.setOnFocusChangeListener { v, b ->
            if (b && !dobDialogOpen) {
                openDatepicker()
            }
        }

        dob!!.setOnClickListener { view1 ->
            if (!dobDialogOpen) {
                openDatepicker()
            }

        }


        gender = view.findViewById(R.id.gender)
        gender!!.showSoftInputOnFocus = false
        val genders = ArrayList<String>()
        genders.add("Male")
        genders.add("Female")

        val adapter = ArrayAdapter(activity!!, android.R.layout.simple_list_item_1, genders)
        gender!!.setAdapter(adapter)

        gender!!.setOnFocusChangeListener { v, b ->
            if (b) {
                gender!!.showDropDown()
            }
        }
        gender!!.setOnClickListener { view1 -> gender!!.showDropDown() }


        signUp = view.findViewById(R.id.signup_btn)
        signUp!!.setOnClickListener(this)
    }


    fun onButtonPressed(uri: Uri) {

    }


    private fun openDatepicker() {
        val now = Calendar.getInstance()

        val dialog = DatePickerDialog(context!!, android.R.style.Theme_Holo_Dialog, { datePicker, year, month, day ->
            val strDay = if (day.toString().length == 1) "0$day" else day.toString() + ""
            val month = month + 1
            val strMonth = if (month.toString().length == 1) "0$month" else month.toString() + ""
            val date = "$year-$strMonth-$strDay"
            dob!!.setText(date)
        }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE))

        dialog.window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        dialog.setOnCancelListener { d -> dobDialogOpen = false }
        dialog.setOnDismissListener { d -> dobDialogOpen = false }
        dialog.show()
        dobDialogOpen = true
    }

    override fun onClick(view: View) {
        if (view === signUp) {
            processSignup()
        }
    }

    private fun processSignup() {

        val fname = firstname!!.text.toString()
        val lname = lastname!!.text.toString()
        val memail = email!!.text.toString()
        val mphone = phone!!.text.toString()
        val mdob = dob!!.text.toString()
        val mgender = gender!!.text.toString()


        if (TextUtils.isEmpty(fname)) {
            toastMessage("First Name is required")
        } else if (TextUtils.isEmpty(lname)) {
            toastMessage("Last Name is required")
        } else if (TextUtils.isEmpty(mphone)) {
            toastMessage("Please enter your  phone number ")
        } else if (mphone.length < 11 || mphone.length > 11 || !Patterns.PHONE.matcher(mphone).matches()) {
            toastMessage("Please enter a valid phone number")
        } else if (TextUtils.isEmpty(memail)) {
            toastMessage("Please enter your  email address")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(memail).matches()) {
            toastMessage("Invalid Email")
        }

        if (!TextUtils.isEmpty(fname) && !TextUtils.isEmpty(lname)
                && !TextUtils.isEmpty(mphone) && !TextUtils.isEmpty(memail)
                && !TextUtils.isEmpty(mdob) && Patterns.PHONE.matcher(mphone).matches() && Patterns.EMAIL_ADDRESS.matcher(memail).matches()) {

            IO.setData(context!!, FIRSTNAME, fname)
            IO.setData(context!!, LASTNAME, lname)
            IO.setData(context!!, PHONE, mphone)
            IO.setData(context!!, EMAIL, memail)
            IO.setData(context!!, DOB, mdob)


            val sdkUser = SDK_User()
            sdkUser.firstname = IO.getData(context!!, Steptwo.FIRSTNAME)
            sdkUser.username = IO.getData(context!!, Stepone.USERNAME)
            sdkUser.password = IO.getData(context!!, Stepone.PASSWORD)
            sdkUser.email = memail
            sdkUser.phone = mphone
            sdkUser.lastname = lname
            sdkUser.dob = mdob
            sdkUser.gender = mgender.substring(0, 1)

//            modelSaver!!.onSave()

        }

    }

    private fun toastMessage(Msg: String) {
        Toast.makeText(context, Msg, Toast.LENGTH_LONG).show()
    }

    companion object {

        private val FIRSTNAME = "signup_firstname"
        private val LASTNAME = "signup_lastname"
        private val PHONE = "signup_phone"
        private val EMAIL = "signup_email"
        private val DOB = "signup_dob"
        private val GENDER = "signup_gender"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         *
         * @return A new instance of fragment Steptwo.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(): Steptwo {
            val fragment = Steptwo()
            fragment.title = Signup.STEP_TWO
            return fragment
        }
    }


}// Required empty public constructor
