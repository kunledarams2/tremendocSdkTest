package com.e.tremendocSDK.View.UI.Activity

//import android.support.v7.app.AppCompatActivity;

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

import com.e.tremendocSDK.Api.API
import com.e.tremendocSDK.Api.StringCall
import com.e.tremendocSDK.Api.URLS
import com.e.tremendocSDK.R
import com.e.tremendocSDK.Service.Model.SDK_User
import com.e.tremendocSDK.View.Callback.FragmentChanger
import com.e.tremendocSDK.View.Callback.ModelSaver
import com.e.tremendocSDK.View.UI.Fragment.FragmentTitled
import com.e.tremendocSDK.View.UI.Fragment.Signup.Stepone
import com.e.tremendocSDK.View.UI.Fragment.Signup.Steptwo
import com.e.tremendocSDK.View.UI.UUitil.Checker
import com.e.tremendocSDK.View.UI.UUitil.DeviceName
import com.e.tremendocSDK.View.UI.UUitil.IO
import com.e.tremendocSDK.View.UI.UUitil.ToastUtili

import org.json.JSONException
import org.json.JSONObject

import java.util.HashMap

class Signup : AppCompatActivity(), FragmentChanger, ModelSaver<SDK_User> {
    private var sdk_user = SDK_User()
    private var call: StringCall? = null
    private var dialog: ProgressDialog? = null
    private var checker: Checker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        changeView(STEP_ONE)
        dialog = ProgressDialog(this)
        checker = Checker(this)
    }

    override fun ChangeFragment(fragment: FragmentTitled) {
        changeView(fragment)
    }

    private fun changeView(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.signupFrame, fragment)
        fragmentTransaction.commit()
    }

    private fun changeView(fragmentName: String) {
        val fragment: Fragment
        title = fragmentName
        when (fragmentName) {
            STEP_ONE -> fragment = Stepone.newInstance()
            STEP_TWO -> fragment = Steptwo.newInstance()
            else -> fragment = Stepone.newInstance()
        }
        changeView(fragment)

    }

    override fun onSave(sdk_user: SDK_User) {
        this.sdk_user = sdk_user
        if (checker!!.isOnline) {
            Signup()
        }

    }


    private fun Signup() {

        dialog = ProgressDialog.show(this, "Sign Up", "Please Wait....", false, false)

        call = StringCall(this)
        val loadData = sdk_user.toMap()
        loadData["operatingSystem"] = "ANDROID"
        loadData["uuid"] = IO.getData(this, API.MY_UUID)
        loadData["brand"] = DeviceName.getDeviceName()
        loadData["sdkType"] = "chat"
        loadData["provider"] = "1"

        call!!.post(URLS.SDK_CREATE_USER, loadData, { response ->
            try {
                dialog!!.dismiss()
                val obj = JSONObject(response)
                if (obj.has("code") && obj.getInt("code") == 0) {


                    val intent = Intent(this, Finddoctor::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()

                } else {
                    dialog!!.dismiss()
                    val dec = obj.getString("description")
                    Toast.makeText(this, dec, Toast.LENGTH_LONG).show()
                }

            } catch (e: JSONException) {
                ToastUtili.showModal(this, e.message)

            }


        }, { error ->
            dialog!!.dismiss()

            if (error.networkResponse == null) {
                ToastUtili.showModal(this, "Please check your internet connection")
            }
        })
    }

    companion object {

        val STEP_ONE = "step_one"
        val STEP_TWO = "step_two"
    }


}
