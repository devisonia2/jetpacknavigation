package com.sonia.jetpacknavigation

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonia.jetpacknavigation.databinding.CongratsgifBinding
import com.sonia.jetpacknavigation.databinding.FragmentThirdBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ThirdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThirdFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var mainActivity:MainActivity?=null
    private var binding: FragmentThirdBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity=activity as MainActivity
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentThirdBinding.inflate(layoutInflater)
        return binding?.root
       // return inflater.inflate(R.layout.fragment_third, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnSave?.setOnClickListener{
            if (binding?.etNewPass?.text?.toString()?.trim().isNullOrEmpty()){
                binding?.etNewPass?.error="Please Enter Password"
            }else if (binding?.etConfPass?.text?.toString()?.trim().isNullOrEmpty()){
                binding?.etConfPass?.error="Please Enter Password"
            }else if (binding?.etNewPass?.text?.toString()!=binding?.etConfPass?.text?.toString()){
                binding?.etConfPass?.error="Enter Similar Password"
            }else if (binding?.etNewPass?.text?.toString()==binding?.etConfPass?.text?.toString()) {
                val dialog = Dialog(mainActivity!!)
                val dialogBinding = CongratsgifBinding.inflate(layoutInflater)
                dialog.setContentView(dialogBinding.root)
                dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                dialog.show()
                mainActivity?.navController?.popBackStack()
            }
        }
        binding?.btnCancel?.setOnClickListener {
            mainActivity?.navController?.popBackStack()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ThirdFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ThirdFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}