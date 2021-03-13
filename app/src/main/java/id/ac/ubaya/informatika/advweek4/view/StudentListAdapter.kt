package id.ac.ubaya.informatika.advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.advweek4.R
import id.ac.ubaya.informatika.advweek4.model.Student
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentListAdapter(val studentList:ArrayList<Student>):RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(){
    class StudentViewHolder(val view:View):RecyclerView.ViewHolder(view)

    //wajib ada utk update list jika ada perubahan, jadi ga usah destroy-create ulang
    fun updateStudentList(newStudentList:List<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged() //utk render ulang dgn data teraktual pada recyclerView
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        //memasang layout pada setiap item dalam recyclerView
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.student_list_item, parent, false)
        return StudentViewHolder(v)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        //ngeload data yang sesuai dari arrayList student dan dipasang/render ke dalam layoutnya
        holder.view.txtId.text = studentList[position].id
        holder.view.txtName.text = studentList[position].name

        holder.view.btDetail.setOnClickListener{
            val action = StudentListFragmentDirections.actionStudentDetail()
            Navigation.findNavController(it).navigate(action)
        }
    }

}