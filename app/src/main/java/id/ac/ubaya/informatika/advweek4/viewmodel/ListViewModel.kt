package id.ac.ubaya.informatika.advweek4.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ubaya.informatika.advweek4.model.Student

class ListViewModel:ViewModel() {
    //studentS -> jamak, nampung banyak object -> List<Student>
    //LD = LiveData biar programmer tau ini variable utk LiveData, bukan var biasa
    val studentsLD = MutableLiveData<List<Student>>()
    //menyimpan kondisi apakah recyclerView berhasil load/ada error?
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingDoneLD = MutableLiveData<Boolean>()


    //loading data dari in/ex source atau dummy source
    fun refresh(){
        //mockaroo.com utk generate dummy data!
        val student1 =
            Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100.jpg/cc0000/ffffff")

        val student2 =
            Student("13312","Rich","1994/12/14","3925444073","http://dummyimage.com/75x100.jpg/5fa2dd/ffffff")

        val student3 =
            Student("11204","Dinny","1994/10/07","6827808747","http://dummyimage.com/75x100.jpg/5fa2dd/ffffff1")
        val studentList:ArrayList<Student> = arrayListOf<Student>(student1, student2, student3)
        studentsLD.value = studentList
        loadingErrorLD.value = false
        loadingDoneLD.value = false

    }
}