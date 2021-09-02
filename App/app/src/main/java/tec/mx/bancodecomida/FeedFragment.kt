package tec.mx.bancodecomida

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import tec.mx.bancodecomida.databinding.FragmentFeedBinding

private var _binding: FragmentFeedBinding? = null
private val binding get() = _binding!!

class FeedFragment : Fragment(R.layout.fragment_feed) {

    private val args: FeedFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ) : View? {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.button.setOnClickListener{
            binding.textViewUsername.text = args.name
            binding.textViewEmail.text = args.email
            binding.textViewPassword.text = args.password
        }



        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}