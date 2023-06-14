package com.ubaya.adv160420067week4.view


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.ubaya.adv160420067week4.R
import com.ubaya.adv160420067week4.databinding.FragmentStudentDetailBinding
import com.ubaya.adv160420067week4.databinding.StudentListItemBinding
import com.ubaya.adv160420067week4.util.loadImage
import com.ubaya.adv160420067week4.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.student_list_item.*
import kotlinx.android.synthetic.main.student_list_item.view.*
import java.util.concurrent.TimeUnit


class StudentDetailFragment : Fragment(), StudentNotification {

    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding: FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_student_detail, container, false)
        dataBinding= DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater,R.layout.fragment_student_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var idStudent=""
        if(arguments!=null){
            idStudent=StudentDetailFragmentArgs.fromBundle(requireArguments()).id
        }
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(idStudent)
        observeViewModel()
        dataBinding.notification=this
    }
    fun observeViewModel() {
        viewModel.studentsLD.observe(viewLifecycleOwner, Observer{
            dataBinding.student=it
//            val txtID = view?.findViewById<TextInputEditText>(R.id.txtID)
//            val txtName = view?.findViewById<TextInputEditText>(R.id.txtName)
//            val txtDob= view?.findViewById<TextInputEditText>(R.id.txtDob)
//            val txtPhone=view?.findViewById<TextInputEditText>(R.id.txtPhone)
//            val image=view?.findViewById<ImageView>(R.id.imageView2)
//
//
//
//            txtID?.setText(viewModel.studentsLD.value?.id)
//            txtName?.setText(viewModel.studentsLD.value?.name)
//            txtDob?.setText(viewModel.studentsLD.value?.dob)
//            txtPhone?.setText(viewModel.studentsLD.value?.phone)
//            imageView2.loadImage(viewModel.studentsLD.value?.photoUrl,progressBar2)
        })
    }

    override fun onNotification(v: View) {
        Observable.timer(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("Messages", "five seconds")
                MainActivity.showNotification(v.tag.toString(),
                    "A new notification created",
                    R.drawable.ic_baseline_error_24)
            }

    }


}