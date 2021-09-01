package uz.androdev.zikr.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.androdev.shared.model.Zikr
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {

    private val _zikrList = MutableLiveData<List<Zikr>>()
    val zikrList: LiveData<List<Zikr>> get() = _zikrList

    private val _refreshing = MutableLiveData<Boolean>()
    val refreshing: LiveData<Boolean> get() = _refreshing

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadZikrList()
        }
    }

    private suspend fun loadZikrList() {
        val list = arrayListOf(
            Zikr(0, "Астағфируллоҳал ъазийм ва атубу илайҳ", "اَسْتَغْفِرُ اللهَ الْعَظِيْمَ و أتوبُ إليهِ", 100),
            Zikr(1, "Аллооҳумма солли ъалаа саййидинаа Муҳаммадив-ва ъалаа аалиҳи ва соҳбиҳии ва саллим", "اَللّهُمَّ صَلِّ عَلَىٰ سَيِّدِنَا مُحَمَّدٍ وَعَلَىٰ آلِهِ وَصَحْبِهِ وَسَلِّمْ", 100),
            Zikr(2, "Субҳааналлооҳи валҳамдулиллааҳи ва лаа илааҳа иллаллооҳу валлооҳу акбар", "سُبْحَانَ اللَّهِ وَالْحَمْدُ لِلَّهِ وَلاَ إِلَهَ إِلاَّ اللَّهُ وَاللَّهُ أَكْبَرُ وَلاَ حَوْلَ وَلاَ قُوَّةَ إِلاَّ بِاللَِِّ", 100),
            Zikr(3, "Субҳааналлооҳи ва биҳамдиҳии, субҳааналлооҳил ъазиийм", "سُبْحَانَ اللَّهِ وَبِحَمْدِهِ، سُبْحَانَ اللَّهِ العَظِيمِ", 100),
            Zikr(4, "Лаа ҳавла валаа қуввата иллаа биллааҳ", "لَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِٱللَّٰهِ", 100),
            Zikr(5, "Ҳасбияллооҳу ва ниъмал вакиийл", "حَسْبِيَ اللَّهُ وَنِعْمَ الْوَكِيل", 100)
        )

        _zikrList.postValue(list)
    }

    fun removeZikr(zikr: Zikr) {
        val list = zikrList.value ?: return
        val newList = arrayListOf<Zikr>()
        newList.addAll(list)

        if(newList.contains(zikr)){
            newList.remove(zikr)
            _zikrList.postValue(newList)
        }
    }

    fun refreshList() {
        if(refreshing.value == true) return
        viewModelScope.launch(Dispatchers.IO) {
            _refreshing.postValue(true)
            loadZikrList()
            _refreshing.postValue(false)
        }
    }
}