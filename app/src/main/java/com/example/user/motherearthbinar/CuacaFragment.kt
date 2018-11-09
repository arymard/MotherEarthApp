package com.example.user.motherearthbinar


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class CuacaFragment : Fragment() {
    private lateinit var spinner: Spinner
    private lateinit var suhu: TextView
    private lateinit var kota: TextView
    private lateinit var tanggal: TextView
    var myCuaca = listOf<CuacaModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spinner = view?.findViewById(R.id.spiner)
        suhu = view.findViewById(R.id.suhu)
        kota = view?.findViewById(R.id.text_kota)
        tanggal = view?.findViewById(R.id.tanggal)
        val spinnerItems = resources.getStringArray(R.array.android_dropdown_arrays)
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter
        requestApi()
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            fun tanggal(tang: String?): String {
                val oldSdf = SimpleDateFormat("yyyy-MM-dd")
                val newSdf = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
                val newDate = newSdf.format(oldSdf.parse(tang))
                return newDate
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var lokasi = spinner.selectedItem.toString()
                for (i in myCuaca.indices) {
                    if (myCuaca[i].lokasi.equals(lokasi)) {
                        val sh = (myCuaca[i].high - 32) / 9 * 5
                        suhu.text = sh.toString()
                        kota.text = myCuaca[i].lokasi
                        tanggal.text = tanggal(myCuaca[i].date).toString()
                    }
                }
            }

        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_cuaca_fragment, container, false)
    }

    fun requestApi() {
        val call = MainApp().services.fetchAllCuaca()
        call.enqueue(object : Callback<CuacaResponse> {
            override fun onResponse(call: Call<CuacaResponse>, response: Response<CuacaResponse>) {
                if (response.code() == 200) {
                    response.body()?.data?.let {
                        getData(it.toMutableList())
                    }
                }
            }

            override fun onFailure(call: Call<CuacaResponse>, t: Throwable) {

            }
        })
    }

    fun getData(data: List<CuacaModel>) {
        myCuaca = data
    }

}
