package com.example.user.motherearthbinar

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.gempa_fr.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GempaFragment : Fragment() {

    var myGempa : ArrayList<GempaModel> = arrayListOf()
    lateinit var myadapter : AdapterGempa


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.gempa_fr, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            myadapter = AdapterGempa(myGempa,it)
        }

        initRecycleView()
        requestServices()
        setData(status)
    }

    private fun initRecycleView() {
        with(gempa_rcv) {
            adapter = myadapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

//    private fun setData() {
//        // data dummy test warna list gempa
//        var dataGempa = mutableListOf<GempaModel>()
//
//        val lokasiku: List<String> = listOf(
//            "Sulawesi , Indonesia",
//            "Lampung , Indonesia",
//            "Yogyakarta , Indonesia",
//            "Yogyakarta , Indonesia",
//            "Padang , Indonesia"
//        );
//
//        var dummyStatus = mutableListOf<String>(
//            "waspada",
//            "aman",
//            "aman",
//            "bahaya",
//            "waspada"
//        )
//
//
//
//        for (i in lokasiku.indices) {
//            var dummy = GempaModel().apply {
//                lokasi = lokasiku[i]
//                status = dummyStatus[i]
//            }
//            myGempa.add(i,dummy)
//        }
//
//        myadapter.update(myGempa)
//    }


    //dummy status
    val status = listOf<String>(
        "aman",
        "bahaya",
        "waspada",
        "bahaya",
        "waspada",
        "aman",
        "aman",
        "aman",
        "waspada",
        "waspada",
        "waspada",
        "bahaya",
        "aman",
        "aman",
        "aman",
        "bahaya",
        "aman",
        "aman",
        "waspada",
        "aman",
        "bahaya")


    private fun requestServices() {
        val call = MainApp().services.fetchAllGempa()
        call.enqueue(object : Callback<GempaResponse> {
            override fun onResponse(call: Call<GempaResponse>, response: Response<GempaResponse>) {
                if (response.code() == 200) {
                    response.body()?.data?.let {
                        getData(it.toMutableList())
                    }
                }
            }
            override fun onFailure(call: Call<GempaResponse>, t: Throwable) {
            }
        })
    }

    private fun getData(data: MutableList<GempaModel>) {
        progress_bar.visibility = View.GONE
        myGempa.clear()
        myGempa.addAll(data)
        myadapter.notifyDataSetChanged()
    }

    private fun setData(status: List<String>){
        myadapter.update(status)
    }

}
