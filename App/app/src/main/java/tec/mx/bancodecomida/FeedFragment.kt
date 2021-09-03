package tec.mx.bancodecomida

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import tec.mx.bancodecomida.databinding.FragmentFeedBinding

//Using binding library in order to avoid using getElementById
private var _binding: FragmentFeedBinding? = null
private val binding get() = _binding!!

//fragment_feed, is the id of the fragment, it's located in xml file
class FeedFragment : Fragment(R.layout.fragment_feed) {

    //Used to recieve argument from the fragments LogIn SignIn
    private val args: FeedFragmentArgs by navArgs()

    //This is the correct syntax if we want to use binding library
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ) : View? {
        //Create the object binding, it's similar to R. ...., whenever you want
        //to call a View in your xml file, just use binding.View
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        val view = binding.root

        //Listener to the button, in order to see the changes and verify the data has
        // been updated
        binding.button.setOnClickListener{
            if(arguments != null) {
                binding.textViewUsername.text = args.name
                binding.textViewEmail.text = args.email
                binding.textViewPassword.text = args.password
            }else{
                Toast.makeText(activity, "Please long press the key", Toast.LENGTH_LONG ).show()
            }
        }



        return view
    }

    // Method of the binding library
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}