package com.example.user.motherearthbinar

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_berita.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeritaFragment : Fragment() {

    lateinit var myadapter: AdapterBerita
    var mymodel =  ArrayList<BeritaModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_berita, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            myadapter = AdapterBerita(mymodel,it)
        }

        initRecycleView()
        requestServices()
    }

    private fun initRecycleView() {
        with(berita_rcv) {
            adapter = myadapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun setData1() {
        // data dummy test rc berita

//        var judulku: List<String> = listOf(
//            "Korban gempa Palu mencapai 1.000.000 jiwa",
//            "Bantuan untuk korban gempa Donggala belum terdistribusi secara merata",
//            "Bantuan untuk korban gempa Donggala belum terdistribusi secara merat",
//            "Bantuan untuk korban gempa Donggala belum terdistribusi secara merat",
//            "Bantuan untuk korban gempa Donggala belum terdistribusi secara merat"
//        );
//
//        val paraku : List<String> = listOf(
//            "perekonomian di pulau Sulawesi mati akibat kejadian gempa yang melanda Kota Palu dini hari ",
//            "Hampir setengah perekonomian di pulau Sulawesi mati akibat kejadian gempa yang melanda Kota Palu dini hari",
//            "Hampir setengah perekonomian di pulau Sulawesi mati akibat kejadian gempa yang melanda Kota Palu dini hari",
//            "Hampir setengah perekonomian di pulau Sulawesi mati akibat kejadian gempa yang melanda Kota Palu dini hari",
//            "Hampir setengah perekonomian di pulau Sulawesi mati akibat kejadian gempa yang melanda Kota Palu dini hari"
//        )
//
//        for(i in judulku.indices) {
//            var dummy = BeritaModel().apply {
//                judul = judulku[i]
//                berita = paraku[i]
//            }
//            mymodel.add(i,dummy)
//        }
//
//
//
//        myadapter.update(mymodel)
    }

        private fun requestServices() {
            val call = MainApp().services.fetchAllBerita()
            call.enqueue(object : Callback<BeritaResponse> {
                override fun onResponse(call: Call<BeritaResponse>, response: Response<BeritaResponse>) {
                    if (response.code() == 200) {
                        response.body()?.data?.let {
                            getData(it as MutableList<BeritaModel>)
                            with(judulhl){
                                text = mymodel[0].source
                            }
                            with(parahl){
                                text = mymodel[0].content
                            }
                            with(imgberita){
                                Picasso.get().load(mymodel[0].gambar).into(imgberita)
                            }
                        }
                    }
                }
                override fun onFailure(call: Call<BeritaResponse>, t: Throwable) {
                }
            })
        }




    private fun getData(data: MutableList<BeritaModel>) {
        progress_bar1.visibility = View.GONE
        mymodel.clear()
        mymodel.addAll(data)
        myadapter.notifyDataSetChanged()
    }



}

