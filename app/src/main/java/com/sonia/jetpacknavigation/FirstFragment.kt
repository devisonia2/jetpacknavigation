package com.sonia.jetpacknavigation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonia.jetpacknavigation.databinding.FragmentFirstBinding
import java.util.regex.Pattern
import kotlin.random.Random
import kotlin.random.nextInt

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentFirstBinding
    private var bundle=Bundle()
    private var intent=Intent(Intent.ACTION_SENDTO)
    var mainActivity:MainActivity?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View ?{
        binding = FragmentFirstBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.btnSend?.setOnClickListener {
            if (binding.etEnterEmail?.text?.toString()?.trim().isNullOrEmpty()){
                binding.etEnterEmail?.error="Enter Email"
            }else if(Pattern.matches(Patterns.EMAIL_ADDRESS.toString(),binding?.etEnterEmail?.text.toString().trim())==false){
                binding?.etEnterEmail?.error = "Enter Valid Email"
            }else{
                val otp= Random.nextInt(1000..9999)
                bundle.putString("OTP", otp.toString())
                mainActivity?.navController?.navigate(R.id.secondFragment,bundle)

                intent.data= Uri.parse("mailto:")
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(binding?.etEnterEmail?.text.toString()))
                intent.putExtra("subject","OTP is:-${otp.toString().trim()}")
                startActivity(intent)
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}
