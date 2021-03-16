package id.ac.ubaya.informatika.advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ac.ubaya.informatika.advweek4.model.Student

class ListViewModel(application: Application):AndroidViewModel(application){
    //studentS -> jamak, nampung banyak object -> List<Student>
    //LD = LiveData biar programmer tau ini variable utk LiveData, bukan var biasa
    val studentsLD = MutableLiveData<List<Student>>()
    //menyimpan kondisi apakah recyclerView berhasil load/ada error?
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue:RequestQueue ?= null

    //loading data dari in/ex source atau dummy source
    fun refresh(){
        loadingLD.value = true
        loadingErrorLD.value = false

        //newRequest butuh context, jadi viewModel harus ambil context
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php"

        val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    val sType = object : TypeToken<List<Student>>() { }.type
                    val result = Gson().fromJson<List<Student>>(response, sType)
                    studentsLD.value = result

                    loadingLD.value = false
                    Log.d("showvolley", response.toString())
                    Log.d("showSTUDENTSLD", studentsLD.value.toString())

                },
                {
                    Log.d("showvolley", it.toString())
                    loadingErrorLD.value = true
                    loadingLD.value = false
                })

        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }

    //dipanggil saat observer / fragment list lagi onStop/onDestroy
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}