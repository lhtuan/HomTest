package lht.com.hometest.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import lht.com.hometest.R
import lht.com.hometest.utils.NetworkUtil
import lht.com.hometest.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.getKeywordObs().observe(this, Observer {keywords ->
            if(keywords == null){
                //failed to fetch keyword
                showError(R.string.error)
            }else{
                keywords_view.setKeywords(keywords)
            }
        })

        if(NetworkUtil.isNetWorkConnected(this)){
            viewModel.loadKeywords()
        }else{
            //No network error
            showError(R.string.error)
        }
    }

    private fun showError(msg: Int){
        AlertDialog.Builder(this)
            .setMessage(msg)
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }
}
