package id.ac.ubaya.informatika.advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.advweek4.R
import id.ac.ubaya.informatika.advweek4.databinding.StudentListItemBinding
import id.ac.ubaya.informatika.advweek4.model.Student
import id.ac.ubaya.informatika.advweek4.util.loadImage
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentListAdapter(val studentList:ArrayList<Student>):RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>()
, ButtonDetailClickListener{
    class StudentViewHolder(val view:StudentListItemBinding):RecyclerView.ViewHolder(view.root)

    //wajib ada utk update list jika ada perubahan, jadi ga usah destroy-create ulang
    fun updateStudentList(newStudentList:List<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged() //utk render ulang dgn data teraktual pada recyclerView
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        //memasang layout pada setiap item dalam recyclerView
        val inflater = LayoutInflater.from(parent.context)
//        val v = inflater.inflate(R.layout.student_list_item, parent, false)
        val v = DataBindingUtil.inflate<StudentListItemBinding>(inflater, R.layout.student_list_item, parent, false)
        return StudentViewHolder(v)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        //ngeload data yang sesuai dari arrayList student dan dipasang/render ke dalam layoutnya
//        holder.view.txtId.text = studentList[position].id
//        holder.view.txtName.text = studentList[position].name
//        holder.view.imageView.loadImage(studentList[position].photoUrl, holder.view.progressBar)
//
//        holder.view.btDetail.setOnClickListener{
//            val action = StudentListFragmentDirections.actionStudentDetail(studentList[position].id)
//            Navigation.findNavController(it).navigate(action)
//        }

        holder.view.student = studentList[position]
        holder.view.listener = this
        //'this' means refer to the StudentListAdapter class which also implements ButtonDetailClickListener

    }

    override fun onButtonDetailClick(v: View) {
        val action = StudentListFragmentDirections.actionStudentDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }

}