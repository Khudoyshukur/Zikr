package uz.androdev.zikr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import uz.androdev.zikr.databinding.ActivityMainBinding
import uz.androdev.zikr.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.gray)

        initUI()
    }

    private fun initUI(){
        binding?.refresh?.setOnClickListener {
            (supportFragmentManager.findFragmentById(R.id.homeFragment) as HomeFragment).onRefresh()
        }
    }
}